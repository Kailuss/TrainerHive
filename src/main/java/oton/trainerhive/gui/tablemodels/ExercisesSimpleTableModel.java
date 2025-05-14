package oton.trainerhive.gui.tablemodels;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import oton.trainerhive.dto.Exercise;

/**
 * Modelo de tabla para mostrar una lista simple de ejercicios.
 * Implementa AbstractTableModel para proporcionar los datos a un JTable.
 * 
 * @author Alfonso Otón
 * @version 1.2
 * @since 2025
 */
public class ExercisesSimpleTableModel extends AbstractTableModel {

    /** Lista de ejercicios que se mostrarán en la tabla */
    private final ArrayList<Exercise> ExerciseList;

    /**
     * Constructor que inicializa el modelo con una lista de ejercicios.
     * 
     * @param exerciseList Lista de ejercicios a mostrar en la tabla
     */
    public ExercisesSimpleTableModel(ArrayList<Exercise> exerciseList) {
        this.ExerciseList = exerciseList;
    }

    /**
     * Obtiene el número de filas de la tabla (corresponde al número de ejercicios).
     * 
     * @return Número total de ejercicios en el modelo
     */
    @Override
    public int getRowCount() {
        return ExerciseList.size();
    }

    /**
     * Obtiene el número de columnas de la tabla.
     * 
     * @return Siempre 1, ya que es un modelo de una sola columna
     */
    @Override
    public int getColumnCount() {
        return 1;
    }

    /**
     * Obtiene el valor para una celda específica de la tabla.
     * 
     * @param rowIndex Índice de la fila (basado en 0)
     * @param columnIndex Índice de la columna (basado en 0)
     * @return El objeto Exercise correspondiente a la fila solicitada
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Exercise exercise = ExerciseList.get(rowIndex);
        return exercise;
    }

    /**
     * Obtiene el ejercicio en una posición específica de la tabla.
     * 
     * @param rowIndex Índice de la fila (basado en 0)
     * @return El objeto Exercise correspondiente a la fila solicitada
     */
    public Exercise getExerciseAt(int rowIndex) {
        Exercise exercise = ExerciseList.get(rowIndex);
        return exercise;
    }

    /**
     * Obtiene el nombre de la columna (no utilizado en este modelo simple).
     * 
     * @param column Índice de la columna (basado en 0)
     * @return Cadena vacía ya que no se muestran nombres de columna
     */
    @Override
    public String getColumnName(int column) {
        return "";
    }
}