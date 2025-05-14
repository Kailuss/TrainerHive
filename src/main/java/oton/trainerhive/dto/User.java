package oton.trainerhive.dto;

/**
 * Clase que representa a un usuario del sistema.
 * Contiene toda la información relacionada con los usuarios,
 * incluyendo sus datos personales, credenciales y roles.
 * 
 * @author Alfonso Otón
 * @version 1.2
 * @since 2025
 */
public class User {
    
    /** Identificador único del usuario */
    private int Id;
    
    /** Nombre completo del usuario */
    private String Name;
    
    /** Correo electrónico del usuario (usado como identificador de login) */
    private String Email;
    
    /** Hash de la contraseña del usuario */
    private String PasswordHash;
    
    /** Avatar o foto de perfil en formato binario */
    private byte[] Photo;
    
    /** Nombre del archivo de la foto de perfil */
    private String PhotoFilename;
    
    /** Indica si el usuario tiene rol de instructor */
    private boolean Instructor;
    
    /** ID del instructor asignado (para usuarios no instructores) */
    private int AssignedInstructor;

    /**
     * Obtiene el ID del usuario.
     * 
     * @return Identificador único del usuario
     */
    public int getId() {
        return Id;
    }

    /**
     * Establece el ID del usuario.
     * 
     * @param Id Nuevo identificador único
     */
    public void setId(int Id) {
        this.Id = Id;
    }

    /**
     * Obtiene el nombre completo del usuario.
     * 
     * @return Nombre del usuario
     */
    public String getName() {
        return Name;
    }

    /**
     * Establece el nombre completo del usuario.
     * 
     * @param Name Nuevo nombre para el usuario
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * Obtiene el email del usuario.
     * 
     * @return Dirección de correo electrónico
     */
    public String getEmail() {
        return Email;
    }

    /**
     * Establece el email del usuario.
     * 
     * @param Email Nueva dirección de correo electrónico
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }

    /**
     * Obtiene el hash de la contraseña del usuario.
     * 
     * @return Hash de la contraseña en formato seguro
     */
    public String getPasswordHash() {
        return PasswordHash;
    }

    /**
     * Establece el hash de la contraseña del usuario.
     * 
     * @param PasswordHash Nuevo hash de contraseña
     */
    public void setPasswordHash(String PasswordHash) {
        this.PasswordHash = PasswordHash;
    }

    /**
     * Obtiene la foto de perfil del usuario.
     * 
     * @return Bytes de la imagen de perfil
     */
    public byte[] getPhoto() {
        return Photo;
    }

    /**
     * Establece la foto de perfil del usuario.
     * 
     * @param Photo Bytes de la nueva imagen de perfil
     */
    public void setPhoto(byte[] Photo) {
        this.Photo = Photo;
    }
    
    /**
     * Obtiene el nombre del archivo de la foto de perfil.
     * 
     * @return Nombre del archivo de la imagen
     */
    public String getPhotoFilename() {
        return PhotoFilename;
    }

    /**
     * Establece el nombre del archivo de la foto de perfil.
     * 
     * @param PhotoFileName Nuevo nombre de archivo para la imagen
     */
    public void setPhotoFilename(String PhotoFileName) {
        this.PhotoFilename = PhotoFileName;
    }

    /**
     * Comprueba si el usuario tiene rol de instructor.
     * 
     * @return true si es instructor, false en caso contrario
     */
    public boolean isInstructor() {
        return Instructor;
    }

    /**
     * Establece el rol de instructor del usuario.
     * 
     * @param Instructor true para asignar rol de instructor
     */
    public void setInstructor(boolean Instructor) {
        this.Instructor = Instructor;
    }

    /**
     * Obtiene el ID del instructor asignado al usuario.
     * 
     * @return Identificador del instructor asignado (0 si no tiene)
     */
    public int getAssignedInstructor() {
        return AssignedInstructor;
    }

    /**
     * Establece el instructor asignado al usuario.
     * 
     * @param AssignedInstructor ID del nuevo instructor asignado
     */
    public void setAssignedInstructor(int AssignedInstructor) {
        this.AssignedInstructor = AssignedInstructor;
    }
}