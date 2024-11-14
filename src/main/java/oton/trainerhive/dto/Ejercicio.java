package oton.trainerhive.dto;

/**
 *
 * @author Kailuss
 */
public class Ejercicio {

    private int Id;
    private String NomExercici;
    private String Descripcio;
    private Byte DemoFoto;

    public int getId() {
	return Id;
    }

    public void setId(int Id) {
	this.Id = Id;
    }

    public String getNomExercici() {
	return NomExercici;
    }

    public void setNomExercici(String NomExercici) {
	this.NomExercici = NomExercici;
    }

    public String getDescripcio() {
	return Descripcio;
    }

    public void setDescripcio(String Descripcio) {
	this.Descripcio = Descripcio;
    }

    public Byte getDemoFoto() {
	return DemoFoto;
    }

    public void setDemoFoto(Byte DemoFoto) {
	this.DemoFoto = DemoFoto;
    }

    public Object getNombre() {
	throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String toString() {
	return this.getDescripcio();  // Esto es lo que se mostrará en el ComboBox
    }
}
