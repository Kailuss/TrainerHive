package oton.trainerhive.dto;

/**
 *
 * @author Kailuss
 */
public class User {
    private int Id;
    private String Name;
    private String Email;
    private String PasswordHash;
    private byte[] Photo;
    private String PhotoFilename;
    private boolean Instructor;
    private int AssignedInstructor;

    public int getId() {
	return Id;
    }

    public void setId(int Id) {
	this.Id = Id;
    }

    public String getName() {
	return Name;
    }

    public void setName(String Name) {
	this.Name = Name;
    }

    public String getEmail() {
	return Email;
    }

    public void setEmail(String Email) {
	this.Email = Email;
    }

    public String getPasswordHash() {
	return PasswordHash;
    }

    public void setPasswordHash(String PasswordHash) {
	this.PasswordHash = PasswordHash;
    }

    public byte[] getPhoto() {
	return Photo;
    }

    public void setPhoto(byte[] Photo) {
	this.Photo = Photo;
    }
    
    public String getPhotoFilename() {
	return PhotoFilename;
    }

    public void setPhotoFilename(String PhotoFileName) {
	this.PhotoFilename = PhotoFileName;
    }

    public boolean isInstructor() {
	return Instructor;
    }

    public void setInstructor(boolean Instructor) {
	this.Instructor = Instructor;
    }

    public int getAssignedInstructor() {
	return AssignedInstructor;
    }

    public void setAssignedInstructor(int AssignedInstructor) {
	this.AssignedInstructor = AssignedInstructor;
    }
}
