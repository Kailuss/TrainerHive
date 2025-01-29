package oton.trainerhive.gui.tablemodels;

import java.io.File;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import oton.trainerhive.dto.Usuario;

public class AlumnosTableModel extends AbstractTableModel {

    private final ArrayList<Usuario> listUsuari;
    String userHome = System.getProperty("user.home");
    File cacheDir = new File(userHome, "AppData/Local/TrainerHive/cache");

    public AlumnosTableModel(ArrayList<Usuario> listUsuari) {
	this.listUsuari = listUsuari;
    }

    @Override
    public int getRowCount() {
	return listUsuari.size();
    }

    @Override
    public int getColumnCount() {
	return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	Usuario user = listUsuari.get(rowIndex);
	String imagePath = new File(cacheDir + "/" + user.getFotoFilename()).toURI().toString();
	// Usa HTML para mostrar el nombre y el correo en líneas separadas
	return "<html><table><tr>"
		+ "<td><img src='" + imagePath + "' width='40' height='40'></td>" // Columna con la imagen
		+ "<td style='padding-left:8px;'><b>" + user.getNom() + "</b><br>" + user.getEmail() + "</td>" // Columna con nombre y correo
		+ "</tr></table></html>";
    }

    @Override
    public String getColumnName(int column) {
	return "Alumnos"; // Nombre de la columna
    }

    public Usuario getUserAt(int index) {
	// Verifica que el índice esté dentro de los límites de la lista
	if (index >= 0 && index < listUsuari.size()) {
	    return listUsuari.get(index);
	}
	return null;
    }

}
