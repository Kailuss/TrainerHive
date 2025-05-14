package oton.trainerhive.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que representa un entrenamiento en el sistema.
 * Contiene información sobre la fecha, comentarios y usuario asociado al entrenamiento.
 * Proporciona métodos para formatear la fecha de manera legible.
 * 
 * @author Alfonso Otón
 * @version 1.2
 * @since 2025
 */
public class Workout {

    /** Identificador único del entrenamiento */
    private int Id;
    
    /** Fecha del entrenamiento en formato yyyy-MM-dd */
    private String ForDate;
    
    /** Objeto Date para manipulación de fechas */
    Date WorkoutDate = new Date();
    
    /** Comentarios o notas sobre el entrenamiento */
    private String Comments;
    
    /** ID del usuario asociado al entrenamiento */
    private int UserId;

    /**
     * Obtiene el ID del entrenamiento.
     * 
     * @return Identificador único del entrenamiento
     */
    public int getId() {
        return Id;
    }

    /**
     * Establece el ID del entrenamiento.
     * 
     * @param Id Nuevo identificador único
     */
    public void setId(int Id) {
        this.Id = Id;
    }

    /**
     * Obtiene la fecha del entrenamiento formateada en español.
     * Formato de salida: "DíaSemana, dd MMM yyyy" (ej: "Lunes, 05 Jun 2023")
     * 
     * @return Fecha formateada como String
     */
    public String getForDateFormatted() {
        SimpleDateFormat formatIn = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatOut = new SimpleDateFormat("EEEE',' dd MMM yyyy", new Locale("es", "ES"));

        try {
            WorkoutDate = formatIn.parse(this.ForDate);
        } catch (ParseException ex) {
            Logger.getLogger(Workout.class.getName()).log(Level.SEVERE, null, ex);
        }
        return formatOut.format(WorkoutDate);
    }
    
    /**
     * Obtiene la fecha del entrenamiento en formato yyyy-MM-dd.
     * 
     * @return Fecha en formato String (yyyy-MM-dd)
     */
    public String getForDate() {
        return ForDate;
    }

    /**
     * Establece la fecha del entrenamiento.
     * 
     * @param ForDate Fecha en formato yyyy-MM-dd
     */
    public void setForDate(String ForDate) {
        this.ForDate = ForDate;
    }

    /**
     * Obtiene los comentarios del entrenamiento.
     * 
     * @return Comentarios asociados al entrenamiento
     */
    public String getComments() {
        return Comments;
    }

    /**
     * Establece los comentarios del entrenamiento.
     * 
     * @param Comments Nuevos comentarios para el entrenamiento
     */
    public void setComments(String Comments) {
        this.Comments = Comments;
    }

    /**
     * Obtiene el ID del usuario asociado al entrenamiento.
     * 
     * @return Identificador del usuario
     */
    public int getUserId() {
        return UserId;
    }

    /**
     * Establece el usuario asociado al entrenamiento.
     * 
     * @param UserId ID del usuario
     */
    public void setUserId(int UserId) {
        this.UserId = UserId;
    }
}