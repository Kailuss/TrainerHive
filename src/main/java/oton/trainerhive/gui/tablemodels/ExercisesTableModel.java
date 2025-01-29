package oton.trainerhive.gui.tablemodels;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import oton.trainerhive.dto.Exercise;

public class ExercisesTableModel extends AbstractTableModel {

    private final ArrayList<Exercise> ExerciseList;

    public ExercisesTableModel(ArrayList<Exercise> exerciseList) {
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
	return "<html><b>" + exercise.getDescription() + "</b><br>" + exercise.getName() + "</html>";
    }
    
    public Exercise getExerciseAt(int rowIndex) {
	Exercise exercise = ExerciseList.get(rowIndex);
	return exercise;
    }

    @Override
    public String getColumnName(int column) {
	return "Ejercicios";
    }
}
