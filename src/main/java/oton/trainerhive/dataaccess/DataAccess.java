package oton.trainerhive.dataaccess;

import at.favre.lib.crypto.bcrypt.BCrypt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oton.trainerhive.dto.Ejercicio;
import oton.trainerhive.dto.Entrenamiento;
import oton.trainerhive.dto.Usuario;

/**
 *
 * @author Kailuss
 */
public class DataAccess {

    // Conecta a la base de datos
    private static Connection getConnection() throws SQLException {
	Connection connection = null;
	String connectionString = "jdbc:sqlserver://localhost;database=simulapdb;user=sa;password=Pwd1234.;encrypt=false;";
	connection = DriverManager.getConnection(connectionString);
	return connection;
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
     * @return
     * @throws SQLException
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
     * @param userName
     * @return
     * @throws SQLException
     */
    public static ArrayList<Usuario> getInstructor(String userName) throws SQLException {
	ArrayList<Usuario> usuaris = new ArrayList<>();
	String sql = "SELECT * FROM Usuaris WHERE Instructor = 1 AND Nom='" + userName + "'";
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
}
