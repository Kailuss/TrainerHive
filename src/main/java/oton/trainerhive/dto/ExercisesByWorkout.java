package oton.trainerhive.dto;

/**
 *
 * @author Alfonso Otón
 */
public class ExercisesByWorkout {

    private int Id;
    private int WorkoutId;
    private int ExerciseId;

    public int getId() {
	return Id;
    }

    public void setId(int Id) {
	this.Id = Id;
    }

    public int getWorkoutId() {
	return WorkoutId;
    }

    public void setWorkoutId(int WorkoutId) {
	this.WorkoutId = WorkoutId;
    }

    public int getExerciseId() {
	return ExerciseId;
    }

    public void setExerciseId(int ExerciseId) {
	this.ExerciseId = ExerciseId;
    }
}
