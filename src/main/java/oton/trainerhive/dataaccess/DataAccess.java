package oton.trainerhive.dataaccess;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import at.favre.lib.crypto.bcrypt.BCrypt;
import java.io.InputStream;

import oton.trainerhive.dto.Exercise;
import oton.trainerhive.dto.Workout;
import oton.trainerhive.dto.User;
import oton.trainerhive.gui.util.UserAvatarGenerator;

/**
 * Clase que proporciona acceso a los datos en la base de datos.
 * Contiene métodos para realizar operaciones CRUD sobre usuarios,
 * entrenamientos y ejercicios.
 * 
 * @author Alfonso Otón
 * @version 1.2
 * @since 2025
 */
public class DataAccess {

    /** Nombre del archivo de propiedades de configuración */
    private static final String PROPERTIES_FILE = "application.properties";
    
    /** Propiedades cargadas desde el archivo de configuración */
    private static final Properties properties = new Properties();

    // Bloque estático para cargar las propiedades al iniciar la clase
    static {
        try (InputStream input = DataAccess.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE);) {
            if (input == null) {
                throw new RuntimeException("No se encontró el archivo de propiedades en el classpath");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar el archivo de propiedades", e);
        }
    }

    /**
     * Establece una conexión con la base de datos.
     * 
     * @return Conexión a la base de datos
     * @throws SQLException Si ocurre un error al establecer la conexión
     */
    private static Connection getConnection() throws SQLException {
        String url = properties.getProperty("spring.datasource.url");
        String user = properties.getProperty("spring.datasource.username");
        String password = properties.getProperty("spring.datasource.password");

        return DriverManager.getConnection(url, user, password);
    }

    /**
     * Verifica si una contraseña coincide con su hash almacenado.
     * 
     * @param password Contraseña en texto plano a verificar
     * @param hash Hash almacenado de la contraseña
     * @return true si la contraseña coincide con el hash, false en caso contrario
     */
    public static boolean decryptPassword(String password, String hash) {
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hash);
        return result.verified;
    }

    /**
     * Obtiene todos los usuarios registrados en el sistema.
     * 
     * @return Lista de usuarios
     * @throws SQLException Si ocurre un error al acceder a la base de datos
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
     * Obtiene un instructor específico por su correo electrónico.
     * 
     * @param userMail Correo electrónico del instructor a buscar
     * @return Lista con el instructor encontrado (vacía si no existe)
     * @throws SQLException Si ocurre un error al acceder a la base de datos
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

    /**
     * Obtiene todos los usuarios asignados a un instructor específico.
     * 
     * @param idInstructor ID del instructor
     * @return Lista de usuarios asignados al instructor
     */
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

    /**
     * Obtiene todos los entrenamientos de un usuario específico.
     * 
     * @param user Usuario del que se quieren obtener los entrenamientos
     * @return Lista de entrenamientos del usuario
     */
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

    /**
     * Obtiene todos los ejercicios asociados a un entrenamiento específico.
     * 
     * @param workout Entrenamiento del que se quieren obtener los ejercicios
     * @return Lista de ejercicios del entrenamiento
     */
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
                exerciseList.add(exercise);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener los ejercicios del entrenamiento de la base de datos", e);
        }
        return exerciseList;
    }

    /**
     * Obtiene todos los ejercicios disponibles en el sistema.
     * 
     * @return Lista completa de ejercicios
     */
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
                exerciseList.add(exercise);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener el listado de ejercicios de la base de datos", e);
        }
        return exerciseList;
    }

    /**
     * Inserta un nuevo entrenamiento y sus ejercicios asociados en la base de datos.
     * 
     * @param workout Entrenamiento a insertar
     * @param exerciseList Lista de ejercicios asociados al entrenamiento
     */
    public static void insertWorkout(Workout workout, ArrayList<Exercise> exerciseList) {
        // The following should be done in a SQL transaction
        int newWorkoutId = insertToWorkoutTable(workout);
        insertExercisesPerWorkout(newWorkoutId, exerciseList);
    }

    /**
     * Inserta un entrenamiento en la tabla Workouts.
     * 
     * @param w Entrenamiento a insertar
     * @return ID del entrenamiento insertado
     */
    private static int insertToWorkoutTable(Workout w) {
        String sql = "INSERT INTO dbo.Workouts (ForDate, UserId, Comments)"
                + " VALUES (?,?,?)";
        try (Connection conn = getConnection(); PreparedStatement insertStatement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);) {
            insertStatement.setString(1, w.getForDate());
            insertStatement.setInt(2, w.getUserId());
            insertStatement.setString(3, w.getComments());

            int affectedRows = insertStatement.executeUpdate();

            if (affectedRows > 0) {
                ResultSet resultSet = insertStatement.getGeneratedKeys();

                if (resultSet.next()) {
                    int lastInsertedId = resultSet.getInt(1);
                    return lastInsertedId;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar el entrenamiento en la base de datos", e);
        }
        return 0;
    }

    /**
     * Inserta todos los ejercicios asociados a un entrenamiento.
     * 
     * @param wId ID del entrenamiento
     * @param exerciseList Lista de ejercicios a asociar
     * @return Número de ejercicios insertados
     */
    private static int insertExercisesPerWorkout(int wId, ArrayList<Exercise> exerciseList) {
        for (Exercise exercise : exerciseList) {
            int rowsAffected = insertExerciciPerWorkout(wId, exercise);
            if (rowsAffected != 1) {
                return 0;
            }
        }
        return exerciseList.size();
    }

    /**
     * Inserta un ejercicio asociado a un entrenamiento.
     * 
     * @param workoutId ID del entrenamiento
     * @param exercise Ejercicio a asociar
     * @return Número de filas afectadas (1 si éxito, 0 si fallo)
     */
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

    /**
     * Elimina un entrenamiento y todos sus ejercicios asociados.
     * 
     * @param workoutId ID del entrenamiento a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     */
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