package oton.trainerhive.gui.tablemodels;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import oton.trainerhive.dto.Exercise;

/**
 * Modelo de tabla especializado para mostrar ejercicios con formato HTML.
 * Extiende AbstractTableModel para proveer datos a componentes JTable con presentación enriquecida.
 * 
 * @author Alfonso Otón
 * @version 1.2
 * @since 2025
 */
public class ExercisesTableModel extends AbstractTableModel {

    /** 
     * Lista de ejercicios que alimenta la tabla.
     * Contiene todos los elementos a mostrar en el modelo.
     */
    private final ArrayList<Exercise> ExerciseList;

    /**
     * Constructor que inicializa el modelo con una lista de ejercicios.
     * 
     * @param exerciseList Lista de ejercicios a representar en la tabla (no puede ser null)
     */
    public ExercisesTableModel(ArrayList<Exercise> exerciseList) {
        this.ExerciseList = exerciseList;
    }

    /**
     * Devuelve el número de filas de la tabla, equivalente al número de ejercicios.
     * 
     * @return Cantidad total de ejercicios en el modelo
     */
    @Override
    public int getRowCount() {
        return ExerciseList.size();
    }

    /**
     * Devuelve el número de columnas del modelo.
     * Esta implementación siempre devuelve 1 (modelo de una sola columna).
     * 
     * @return Entero 1 que representa la única columna
     */
    @Override
    public int getColumnCount() {
        return 1;
    }

    /**
     * Obtiene el valor formateado para una celda específica.
     * Devuelve un String con formato HTML que muestra la descripción en negrita
     * y el nombre en la línea siguiente.
     * 
     * @param rowIndex Índice de la fila (0-based)
     * @param columnIndex Índice de la columna (0-based)
     * @return Texto formateado en HTML para visualización en la celda
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Exercise exercise = ExerciseList.get(rowIndex);
        return "<html><b>" + exercise.getDescription() + "</b><br>" + exercise.getName() + "</html>";
    }
    
    /**
     * Obtiene el objeto Exercise completo correspondiente a una fila específica.
     * 
     * @param rowIndex Índice de la fila (0-based)
     * @return Objeto Exercise en la posición solicitada
     */
    public Exercise getExerciseAt(int rowIndex) {
        Exercise exercise = ExerciseList.get(rowIndex);
        return exercise;
    }

    /**
     * Devuelve el nombre de la columna para el encabezado de la tabla.
     * 
     * @param column Índice de la columna (0-based)
     * @return El título "Ejercicios" para la única columna
     */
    @Override
    public String getColumnName(int column) {
        return "Ejercicios";
    }
}