package oton.trainerhive.dto;

/**
 * Clase que representa la relación entre ejercicios y entrenamientos.
 * Contiene los identificadores necesarios para asociar un ejercicio específico
 * con un entrenamiento concreto en el sistema.
 * 
 * @author Alfonso Otón
 * @version 1.2
 * @since 2025
 */
public class ExercisesByWorkout {

    /** Identificador único de la relación ejercicio-entrenamiento */
    private int Id;
    
    /** Identificador del entrenamiento asociado */
    private int WorkoutId;
    
    /** Identificador del ejercicio asociado */
    private int ExerciseId;

    /**
     * Obtiene el ID de la relación ejercicio-entrenamiento.
     * 
     * @return Identificador único de la relación
     */
    public int getId() {
        return Id;
    }

    /**
     * Establece el ID de la relación ejercicio-entrenamiento.
     * 
     * @param Id Nuevo identificador único para la relación
     */
    public void setId(int Id) {
        this.Id = Id;
    }

    /**
     * Obtiene el ID del entrenamiento asociado.
     * 
     * @return Identificador del entrenamiento
     */
    public int getWorkoutId() {
        return WorkoutId;
    }

    /**
     * Establece el ID del entrenamiento asociado.
     * 
     * @param WorkoutId Nuevo identificador de entrenamiento
     */
    public void setWorkoutId(int WorkoutId) {
        this.WorkoutId = WorkoutId;
    }

    /**
     * Obtiene el ID del ejercicio asociado.
     * 
     * @return Identificador del ejercicio
     */
    public int getExerciseId() {
        return ExerciseId;
    }

    /**
     * Establece el ID del ejercicio asociado.
     * 
     * @param ExerciseId Nuevo identificador de ejercicio
     */
    public void setExerciseId(int ExerciseId) {
        this.ExerciseId = ExerciseId;
    }
}