package oton.trainerhive.dataaccess;

import at.favre.lib.crypto.bcrypt.BCrypt;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import oton.trainerhive.dto.Ejercicio;
import oton.trainerhive.dto.Entrenamiento;
import oton.trainerhive.dto.Usuario;

/**
 *
 * @author Kailuss
 */
public class DataAccess {

    private static final String PROPERTIES_FILE = "src/main/resources/properties/application.properties";
    private static Properties properties = new Properties();

    static {
	try (FileInputStream input = new FileInputStream(PROPERTIES_FILE)) {
	    properties.load(input);
	} catch (IOException e) {
	    e.printStackTrace();
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
     */
    public static ArrayList<Usuario> getUsuaris() throws SQLException {
	ArrayList<Usuario> usuaris = new ArrayList<>();
	String sql = "SELECT * FROM Usuaris";
	try (Connection connection = getConnection(); PreparedStatement selectStatement = connection.prepareStatement(sql)) {
	    ResultSet resultSet = selectStatement.executeQuery();
	    while (resultSet.next()) {
		Usuario user = new Usuario();
		user.setId(resultSet.getInt("Id"));
		user.setNom(resultSet.getString("Nom"));
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
    public static ArrayList<Usuario> getInstructor(String userMail) throws SQLException {
	ArrayList<Usuario> usuaris = new ArrayList<>();
	String sql = "SELECT * FROM Usuaris WHERE Instructor = 1 AND Email='" + userMail + "'";
	try (Connection connection = getConnection(); PreparedStatement selectStatement = connection.prepareStatement(sql)) {
	    ResultSet resultSet = selectStatement.executeQuery();
	    while (resultSet.next()) {
		Usuario user = new Usuario();
		user.setId(resultSet.getInt("Id"));
		user.setNom(resultSet.getString("Nom"));
		user.setEmail(resultSet.getString("Email"));
		user.setPasswordHash(resultSet.getString("PasswordHash"));
		//user.setFoto(resultSet.getBytes("Foto"));
		usuaris.add(user);
	    }
	    selectStatement.close();
	    connection.close();
	}
	return usuaris;
    }

    public static ArrayList<Usuario> getAllUsersByInstructor(int idInstructor) {
	ArrayList<Usuario> usuaris = new ArrayList<>();
	String sql = "SELECT * FROM Usuaris WHERE AssignedInstructor=?";
	try (Connection connection = getConnection(); PreparedStatement selectStatement = connection.prepareStatement(sql);) {
	    selectStatement.setInt(1, idInstructor);
	    ResultSet resultSet = selectStatement.executeQuery();

	    while (resultSet.next()) {
		Usuario user = new Usuario();
		user.setId(resultSet.getInt("Id"));
		user.setNom(resultSet.getString("Nom"));
		user.setEmail(resultSet.getString("Email"));
		user.setPasswordHash(resultSet.getString("PasswordHash"));
		user.setInstructor(resultSet.getBoolean("Instructor"));
		usuaris.add(user);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return usuaris;
    }

    public static ArrayList<Entrenamiento> getWorkoutsPerUser(Usuario user) {
	ArrayList<Entrenamiento> workouts = new ArrayList<>();
	String sql = "SELECT * FROM Workouts"
		+ " WHERE Workouts.UserId=?"
		+ " ORDER BY Workouts.ForDate";
	try (Connection connection = getConnection(); PreparedStatement selectStatement = connection.prepareStatement(sql);) {
	    selectStatement.setInt(1, user.getId());
	    ResultSet resultSet = selectStatement.executeQuery();

	    while (resultSet.next()) {
		Entrenamiento workout = new Entrenamiento();
		workout.setId(resultSet.getInt("Id"));
		workout.setForDate(resultSet.getString("ForDate"));
		workout.setUserId(resultSet.getInt("UserId"));
		workout.setComments(resultSet.getString("Comments"));
		workouts.add(workout);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return workouts;
    }

    public static ArrayList<Ejercicio> getExercicisPerWorkout(Entrenamiento entrenamiento) {
	ArrayList<Ejercicio> ejercicios = new ArrayList<>();
	String sql = "SELECT ExercicisWorkouts.IdExercici,"
		+ " Exercicis.NomExercici, Exercicis.Descripcio, Exercicis.DemoFoto"
		+ " FROM ExercicisWorkouts INNER JOIN Exercicis ON ExercicisWorkouts.IdExercici=Exercicis.Id"
		+ " WHERE ExercicisWorkouts.IdWorkout=?";
	try (Connection connection = getConnection(); PreparedStatement selectStatement = connection.prepareStatement(sql);) {
	    selectStatement.setInt(1, entrenamiento.getId());
	    ResultSet resultSet = selectStatement.executeQuery();

	    while (resultSet.next()) {
		Ejercicio ejercicio = new Ejercicio();
		ejercicio.setId(resultSet.getInt("IdExercici"));
		ejercicio.setNomExercici(resultSet.getString("NomExercici"));
		ejercicio.setDescripcio(resultSet.getString("Descripcio"));
		//ejercicio.setDemoFoto(resultSet.getString("DemoFoto"));
		ejercicios.add(ejercicio);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return ejercicios;
    }

    public static ArrayList<Ejercicio> getAllExercicis() {
	ArrayList<Ejercicio> ejercicios = new ArrayList<>();
	String sql = "SELECT Id, Exercicis.NomExercici, Exercicis.Descripcio, Exercicis.DemoFoto"
		+ " FROM Exercicis";
	try (Connection connection = getConnection(); PreparedStatement selectStatement = connection.prepareStatement(sql);) {

	    ResultSet resultSet = selectStatement.executeQuery();

	    while (resultSet.next()) {
		Ejercicio ejercicio = new Ejercicio();
		ejercicio.setId(resultSet.getInt("Id"));
		ejercicio.setNomExercici(resultSet.getString("NomExercici"));
		ejercicio.setDescripcio(resultSet.getString("Descripcio"));
		//exercici.setDemoFoto(resultSet.getString("DemoFoto"));

		ejercicios.add(ejercicio);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return ejercicios;
    }

    public static void insertWorkout(Entrenamiento w, ArrayList<Ejercicio> ejercicios) {
	// The following should be done in a SQL transaction
	int newWorkoutId = insertToWorkoutTable(w);
	insertExercisesPerWorkout(newWorkoutId, ejercicios);
    }

    private static int insertToWorkoutTable(Entrenamiento w) {
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
	    e.printStackTrace();
	}
	return 0;
    }

    private static int insertExercisesPerWorkout(int wId, ArrayList<Ejercicio> ejercicios) {
	for (Ejercicio e : ejercicios) {
	    int rowsAffected = insertExerciciPerWorkout(wId, e);
	    if (rowsAffected != 1) {
		return 0;
	    }
	}
	return ejercicios.size();
    }

    private static int insertExerciciPerWorkout(int wId, Ejercicio e) {
	String sql = "INSERT INTO dbo.ExercicisWorkouts (IdWorkout, IdExercici)"
		+ " VALUES (?,?)";
	try (Connection conn = getConnection(); PreparedStatement insertStatement = conn.prepareStatement(sql)) {
	    insertStatement.setInt(1, wId);
	    insertStatement.setInt(2, e.getId());
	    int rowsAffected = insertStatement.executeUpdate();
	    return rowsAffected;
	} catch (SQLException ex) {
	    ex.printStackTrace();
	}
	return 0;
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
		    ex.printStackTrace();
		}
	    }
	    e.printStackTrace();
	    return false;
	} finally {
	    if (conn != null) {
		try {
		    conn.setAutoCommit(true); // Restaurar el autocommit
		    conn.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
    }
}
