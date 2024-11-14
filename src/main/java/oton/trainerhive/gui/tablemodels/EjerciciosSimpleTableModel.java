package oton.trainerhive.gui.tablemodels;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import oton.trainerhive.dto.Ejercicio;

public class EjerciciosSimpleTableModel extends AbstractTableModel {

    private final ArrayList<Ejercicio> listEjercicios;

    public EjerciciosSimpleTableModel(ArrayList<Ejercicio> listEjercicios) {
	this.listEjercicios = listEjercicios;
    }

    @Override
    public int getRowCount() {
	return listEjercicios.size();
    }

    @Override
    public int getColumnCount() {
	return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	Ejercicio ejercicio = listEjercicios.get(rowIndex);
	return ejercicio;
    }

    public Ejercicio getExerciseAt(int rowIndex) {
	Ejercicio ejercicio = listEjercicios.get(rowIndex);
	return ejercicio;
    }

    @Override
    public String getColumnName(int column) {
	return "";
    }
}
