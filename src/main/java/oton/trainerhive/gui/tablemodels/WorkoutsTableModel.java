package oton.trainerhive.gui.tablemodels;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import oton.trainerhive.dto.Workout;

public class WorkoutsTableModel extends AbstractTableModel {

    private final ArrayList<Workout> WorkoutList;

    public WorkoutsTableModel(ArrayList<Workout> workoutList) {
	this.WorkoutList = workoutList;
    }

    @Override
    public int getRowCount() {
	return WorkoutList.size();
    }

    @Override
    public int getColumnCount() {
	return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	Workout workout = WorkoutList.get(rowIndex);
	return switch (columnIndex) {
	    case 0 ->
		"<html><b>" + workout.getComments() + "</b></html>";
	    case 1 ->
		workout.getForDateFormatted();
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

    public Workout getEntrenamientoAt(int index) {
	// Verifica si el índice está dentro de los límites de la lista
	if (index >= 0 && index < WorkoutList.size()) {
	    return WorkoutList.get(index);
	}
	return null;
    }

}
