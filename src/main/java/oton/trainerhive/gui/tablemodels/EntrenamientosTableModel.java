package oton.trainerhive.gui.tablemodels;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import oton.trainerhive.dto.Entrenamiento;

public class EntrenamientosTableModel extends AbstractTableModel {

    private final ArrayList<Entrenamiento> listEntrenamientos;

    public EntrenamientosTableModel(ArrayList<Entrenamiento> listEntrenamientos) {
	this.listEntrenamientos = listEntrenamientos;
    }

    @Override
    public int getRowCount() {
	return listEntrenamientos.size();
    }

    @Override
    public int getColumnCount() {
	return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	Entrenamiento entrenamiento = listEntrenamientos.get(rowIndex);
	return switch (columnIndex) {
	    case 0 ->
		"<html><b>" + entrenamiento.getComments() + "</b></html>";
	    case 1 ->
		entrenamiento.getForDate();
	    default ->
		null;
	};
    }

    @Override
    public String getColumnName(int column) {
	return switch (column) {
	    case 0 ->
		"Entrenamiento";
	    case 1 ->
		"Fecha";
	    default ->
		"";
	};
    }

    public Entrenamiento getEntrenamientoAt(int index) {
	// Verifica si el índice está dentro de los límites de la lista
	if (index >= 0 && index < listEntrenamientos.size()) {
	    return listEntrenamientos.get(index);
	}
	return null;
    }

}
