package oton.trainerhive.dto;

/**
 * Clase que representa un ejercicio en el sistema.
 * Contiene información sobre el nombre, descripción y foto demostrativa del ejercicio.
 * 
 * @author Alfonso Otón
 * @version 1.2
 * @since 2025
 */
public class Exercise {

    /** Identificador único del ejercicio */
    private int Id;
    
    /** Nombre del ejercicio */
    private String Name;
    
    /** Descripción detallada del ejercicio */
    private String Description;
    
    /** Foto demostrativa del ejercicio en formato binario */
    private Byte Photo;

    /**
     * Obtiene el ID del ejercicio.
     * 
     * @return ID del ejercicio
     */
    public int getId() {
        return Id;
    }

    /**
     * Establece el ID del ejercicio.
     * 
     * @param Id Nuevo ID para el ejercicio
     */
    public void setId(int Id) {
        this.Id = Id;
    }

    /**
     * Obtiene el nombre del ejercicio.
     * 
     * @return Nombre del ejercicio
     */
    public String getName() {
        return Name;
    }

    /**
     * Establece el nombre del ejercicio.
     * 
     * @param ExerciseName Nuevo nombre para el ejercicio
     */
    public void setName(String ExerciseName) {
        this.Name = ExerciseName;
    }

    /**
     * Obtiene la descripción del ejercicio.
     * 
     * @return Descripción del ejercicio
     */
    public String getDescription() {
        return Description;
    }

    /**
     * Establece la descripción del ejercicio.
     * 
     * @param Description Nueva descripción para el ejercicio
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     * Obtiene la foto demostrativa del ejercicio.
     * 
     * @return Foto del ejercicio en formato binario
     */
    public Byte getPhoto() {
        return Photo;
    }

    /**
     * Establece la foto demostrativa del ejercicio.
     * 
     * @param Photo Nueva foto para el ejercicio en formato binario
     */
    public void setPhoto(Byte Photo) {
        this.Photo = Photo;
    }

    /**
     * Representación en String del ejercicio (usada para mostrar en componentes UI como ComboBox).
     * 
     * @return La descripción del ejercicio
     */
    @Override
    public String toString() {
        return this.getDescription();  // Esto es lo que se mostrará en el ComboBox
    }
}