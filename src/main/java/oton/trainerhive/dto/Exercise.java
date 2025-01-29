package oton.trainerhive.dto;

/**
 *
 * @author Kailuss
 */
public class Exercise {

    private int Id;
    private String Name;
    private String Description;
    private Byte Photo;

    public int getId() {
	return Id;
    }

    public void setId(int Id) {
	this.Id = Id;
    }

    public String getName() {
	return Name;
    }

    public void setName(String ExerciseName) {
	this.Name = ExerciseName;
    }

    public String getDescription() {
	return Description;
    }

    public void setDescription(String Description) {
	this.Description = Description;
    }

    public Byte getPhoto() {
	return Photo;
    }

    public void setPhoto(Byte Photo) {
	this.Photo = Photo;
    }

    @Override
    public String toString() {
	return this.getDescription();  // Esto es lo que se mostrará en el ComboBox
    }
}
