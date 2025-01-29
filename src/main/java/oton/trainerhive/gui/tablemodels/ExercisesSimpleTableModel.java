package oton.trainerhive.gui.tablemodels;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import oton.trainerhive.dto.Exercise;

public class ExercisesSimpleTableModel extends AbstractTableModel {

    private final ArrayList<Exercise> ExerciseList;

    public ExercisesSimpleTableModel(ArrayList<Exercise> exerciseList) {
	this.ExerciseList = exerciseList;
    }

    @Override
    public int getRowCount() {
	return ExerciseList.size();
    }

    @Override
    public int getColumnCount() {
	return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	Exercise exercise = ExerciseList.get(rowIndex);
	return exercise;
    }

    public Exercise getExerciseAt(int rowIndex) {
	Exercise exercise = ExerciseList.get(rowIndex);
	return exercise;
    }

    @Override
    public String getColumnName(int column) {
	return "";
    }
}
