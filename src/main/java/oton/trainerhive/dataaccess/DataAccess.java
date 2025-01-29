package oton.trainerhive.dataaccess;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import at.favre.lib.crypto.bcrypt.BCrypt;

import oton.trainerhive.dto.Exercise;
import oton.trainerhive.dto.Workout;
import oton.trainerhive.dto.User;
import oton.trainerhive.gui.util.UserAvatarGenerator;

/**
 *
 * @author Kailuss
 */
public class DataAccess {

    private static final String PROPERTIES_FILE = "src/main/resources/properties/application.properties";
    private static final Properties properties = new Properties();

    static {
	try (FileInputStream input = new FileInputStream(PROPERTIES_FILE)) {
	    properties.load(input);
	} catch (IOException e) {
	     throw new RuntimeException("Error al cargar el archivo de propiedades", e);
	}
    }

    // Conecta a la base de datos
    private static Connection getConnection() throws SQLException {
	String url = properties.getProperty("spring.datasource.url");
	String user = properties.getProperty("spring.datasource.username");
	String password = properties.getProperty("spring.datasource.password");

	return DriverManager.getConnection(url, user, password);
    }

    // Decodifica el hash de la contraseña
    /**
     *
     * @param password
     * @param hash
     * @return
     */
    public static boolean decryptPassword(String password, String hash) {
	BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hash);
	return result.verified;
    }

    /**
     *
     * @return @throws SQLException
     * @throws java.sql.SQLException
     */
    public static ArrayList<User> getUsuaris() throws SQLException {
	ArrayList<User> usuaris = new ArrayList<>();
	String sql = "SELECT * FROM Usuaris";
	try (Connection connection = getConnection(); PreparedStatement selectStatement = connection.prepareStatement(sql)) {
	    ResultSet resultSet = selectStatement.executeQuery();
	    while (resultSet.next()) {
		User user = new User();
		user.setId(resultSet.getInt("Id"));
		user.setName(resultSet.getString("Nom"));
		user.setEmail(resultSet.getString("Email"));
		user.setPasswordHash(resultSet.getString("PasswordHash"));
		//user.setFoto(resultSet.getBytes("Foto"));
		user.setInstructor(resultSet.getBoolean("Instructor"));
		user.setAssignedInstructor(resultSet.getInt("AssignedInstructor"));
		usuaris.add(user);
	    }
	    selectStatement.close();
	    connection.close();
	}
	return usuaris;
    }

    /**
     *
     * @param userMail
     * @return
     * @throws SQLException
     */
    public static ArrayList<User> getInstructor(String userMail) throws SQLException {
	ArrayList<User> usuaris = new ArrayList<>();
	String sql = "SELECT * FROM Usuaris WHERE Instructor = 1 AND Email='" + userMail + "'";
	try (Connection connection = getConnection(); PreparedStatement selectStatement = connection.prepareStatement(sql)) {
	    ResultSet resultSet = selectStatement.executeQuery();
	    while (resultSet.next()) {
		User user = new User();
		user.setId(resultSet.getInt("Id"));
		user.setName(resultSet.getString("Nom"));
		user.setEmail(resultSet.getString("Email"));
		user.setPasswordHash(resultSet.getString("PasswordHash"));
		user.setPhoto(resultSet.getBytes("Foto"));
		user.setPhotoFilename(resultSet.getString("FotoFilename"));
		usuaris.add(user);
		UserAvatarGenerator.createUserAvatar(user, 40, true);
	    }
	    selectStatement.close();
	    connection.close();
	}
	return usuaris;
    }

    public static ArrayList<User> getAllUsersByInstructor(int idInstructor) {
	ArrayList<User> userList = new ArrayList<>();
	String sql = "SELECT * FROM Usuaris WHERE AssignedInstructor=?";
	try (Connection connection = getConnection(); PreparedStatement selectStatement = connection.prepareStatement(sql);) {
	    selectStatement.setInt(1, idInstructor);
	    ResultSet resultSet = selectStatement.executeQuery();

	    while (resultSet.next()) {
		User user = new User();
		user.setId(resultSet.getInt("Id"));
		user.setName(resultSet.getString("Nom"));
		user.setEmail(resultSet.getString("Email"));
		user.setPasswordHash(resultSet.getString("PasswordHash"));
		user.setPhoto(resultSet.getBytes("Foto"));
		user.setPhotoFilename(resultSet.getString("FotoFilename"));
		user.setInstructor(resultSet.getBoolean("Instructor"));
		userList.add(user);
		UserAvatarGenerator.createUserAvatar(user, 40, true);
	    }
	} catch (SQLException e) {
	     throw new RuntimeException("Error al obtener los usuarios asignados al instructor de la base de datos", e);
	}
	return userList;
    }

    public static ArrayList<Workout> getWorkoutsPerUser(User user) {
	ArrayList<Workout> workoutList = new ArrayList<>();
	String sql = "SELECT * FROM Workouts"
		+ " WHERE Workouts.UserId=?"
		+ " ORDER BY Workouts.ForDate";
	try (Connection connection = getConnection(); PreparedStatement selectStatement = connection.prepareStatement(sql);) {
	    selectStatement.setInt(1, user.getId());
	    ResultSet resultSet = selectStatement.executeQuery();

	    while (resultSet.next()) {
		Workout workout = new Workout();
		workout.setId(resultSet.getInt("Id"));
		workout.setForDate(resultSet.getString("ForDate"));
		workout.setUserId(resultSet.getInt("UserId"));
		workout.setComments(resultSet.getString("Comments"));
		workoutList.add(workout);
	    }
	} catch (SQLException e) {
	     throw new RuntimeException("Error al obtener los entrenamientos del usuario de la base de datos", e);
	}
	return workoutList;
    }

    public static ArrayList<Exercise> getExercicisPerWorkout(Workout workout) {
	ArrayList<Exercise> exerciseList = new ArrayList<>();
	String sql = "SELECT ExercicisWorkouts.IdExercici,"
		+ " Exercicis.NomExercici, Exercicis.Descripcio, Exercicis.DemoFoto"
		+ " FROM ExercicisWorkouts INNER JOIN Exercicis ON ExercicisWorkouts.IdExercici=Exercicis.Id"
		+ " WHERE ExercicisWorkouts.IdWorkout=?";
	try (Connection connection = getConnection(); PreparedStatement selectStatement = connection.prepareStatement(sql);) {
	    selectStatement.setInt(1, workout.getId());
	    ResultSet resultSet = selectStatement.executeQuery();

	    while (resultSet.next()) {
		Exercise exercise = new Exercise();
		exercise.setId(resultSet.getInt("IdExercici"));
		exercise.setName(resultSet.getString("NomExercici"));
		exercise.setDescription(resultSet.getString("Descripcio"));
		//ejercicio.setDemoFoto(resultSet.getString("DemoFoto"));
		exerciseList.add(exercise);
	    }
	} catch (SQLException e) {
	     throw new RuntimeException("Error al obtener los ejercicios del entrenamiento de la base de datos", e);
	}
	return exerciseList;
    }

    public static ArrayList<Exercise> getAllExercicis() {
	ArrayList<Exercise> exerciseList = new ArrayList<>();
	String sql = "SELECT Id, Exercicis.NomExercici, Exercicis.Descripcio, Exercicis.DemoFoto"
		+ " FROM Exercicis";
	try (Connection connection = getConnection(); PreparedStatement selectStatement = connection.prepareStatement(sql);) {

	    ResultSet resultSet = selectStatement.executeQuery();

	    while (resultSet.next()) {
		Exercise exercise = new Exercise();
		exercise.setId(resultSet.getInt("Id"));
		exercise.setName(resultSet.getString("NomExercici"));
		exercise.setDescription(resultSet.getString("Descripcio"));
		//exercici.setDemoFoto(resultSet.getString("DemoFoto"));

		exerciseList.add(exercise);
	    }
	} catch (SQLException e) {
	    throw new RuntimeException("Error al obtener el listado de ejercicios de la base de datos", e);
	}
	return exerciseList;
    }

    public static void insertWorkout(Workout workout, ArrayList<Exercise> exerciseList) {
	// The following should be done in a SQL transaction
	int newWorkoutId = insertToWorkoutTable(workout);
	insertExercisesPerWorkout(newWorkoutId, exerciseList);
    }

    private static int insertToWorkoutTable(Workout w) {
	String sql = "INSERT INTO dbo.Workouts (ForDate, UserId, Comments)"
		+ " VALUES (?,?,?)";
	try (Connection conn = getConnection(); PreparedStatement insertStatement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);) {
	    insertStatement.setString(1, w.getForDate());
	    insertStatement.setInt(2, w.getUserId());
	    insertStatement.setString(3, w.getComments());

	    int affectedRows = insertStatement.executeUpdate();

	    if (affectedRows > 0) {
		// Retrieve the generated keys (identity value)
		ResultSet resultSet = insertStatement.getGeneratedKeys();

		// Check if a key was generated
		if (resultSet.next()) {
		    // Get the last inserted identity value
		    int lastInsertedId = resultSet.getInt(1);
		    return lastInsertedId;
		}
	    }
	} catch (SQLException e) {
	     throw new RuntimeException("Error al insertar el entrenamiento en la base de datos", e);
	}
	return 0;
    }

    private static int insertExercisesPerWorkout(int wId, ArrayList<Exercise> exerciseList) {
	for (Exercise exercise : exerciseList) {
	    int rowsAffected = insertExerciciPerWorkout(wId, exercise);
	    if (rowsAffected != 1) {
		return 0;
	    }
	}
	return exerciseList.size();
    }

    private static int insertExerciciPerWorkout(int workoutId, Exercise exercise) {
	String sql = "INSERT INTO dbo.ExercicisWorkouts (IdWorkout, IdExercici)"
		+ " VALUES (?,?)";
	try (Connection conn = getConnection(); PreparedStatement insertStatement = conn.prepareStatement(sql)) {
	    insertStatement.setInt(1, workoutId);
	    insertStatement.setInt(2, exercise.getId());
	    int rowsAffected = insertStatement.executeUpdate();
	    return rowsAffected;
	} catch (SQLException e) {
	     throw new RuntimeException("Error al asociar ejercicios al entrenamiento en la base de datos", e);
	}
    }

    public static boolean deleteWorkout(int workoutId) {
	String deleteExercisesSQL = "DELETE FROM dbo.ExercicisWorkouts WHERE IdWorkout = ?";
	String deleteWorkoutSQL = "DELETE FROM dbo.Workouts WHERE Id = ?";

	Connection conn = null;
	try {
	    conn = getConnection();
	    conn.setAutoCommit(false); // Inicio de la transacción

	    // Primero borramos los ejercicios asociados
	    try (PreparedStatement deleteExercisesStmt = conn.prepareStatement(deleteExercisesSQL)) {
		deleteExercisesStmt.setInt(1, workoutId);
		deleteExercisesStmt.executeUpdate();
	    }

	    // Luego borramos el workout
	    try (PreparedStatement deleteWorkoutStmt = conn.prepareStatement(deleteWorkoutSQL)) {
		deleteWorkoutStmt.setInt(1, workoutId);
		int affectedRows = deleteWorkoutStmt.executeUpdate();

		if (affectedRows > 0) {
		    conn.commit(); // Confirmar la transacción
		    return true;
		} else {
		    conn.rollback(); // Revertir si no se encontró el workout
		    return false;
		}
	    }

	} catch (SQLException e) {
	    if (conn != null) {
		try {
		    conn.rollback(); // Revertir en caso de error
		} catch (SQLException ex) {
		     throw new RuntimeException("Error al revertir la transacción de eliminación del entrenamiento", ex);
		}
	    }
	     throw new RuntimeException("Error al eliminar el entrenamiento y sus ejercicios asociados", e);
	} finally {
	    if (conn != null) {
		try {
		    conn.setAutoCommit(true); // Restaurar el autocommit
		    conn.close();
		} catch (SQLException e) {
		     throw new RuntimeException("Error al cerrar la conexión después de eliminar el entrenamiento", e);
		}
	    }
	}
    }
}
