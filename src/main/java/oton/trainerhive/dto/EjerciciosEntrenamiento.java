package oton.trainerhive.dto;

/**
 *
 * @author Alfonso Otón
 */
public class EjerciciosEntrenamiento {

    private int Id;
    private int IdWorkout;
    private int IdExercici;

    public int getId() {
	return Id;
    }

    public void setId(int Id) {
	this.Id = Id;
    }

    public int getIdWorkout() {
	return IdWorkout;
    }

    public void setIdWorkout(int IdWorkout) {
	this.IdWorkout = IdWorkout;
    }

    public int getIdExercici() {
	return IdExercici;
    }

    public void setIdExercici(int IdExercici) {
	this.IdExercici = IdExercici;
    }
}
