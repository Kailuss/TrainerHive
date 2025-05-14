package oton.trainerhive.gui.tablemodels;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import oton.trainerhive.dto.Workout;

/**
 * Modelo de tabla especializado para mostrar entrenamientos con formato enriquecido.
 * Proporciona una representación visual de entrenamientos con comentarios destacados
 * y fechas formateadas.
 * 
 * @author Alfonso Otón
 * @version 1.2
 * @since 2025
 */
public class WorkoutsTableModel extends AbstractTableModel {

    /**
     * Lista de entrenamientos que alimenta el modelo de tabla.
     * Contiene todos los objetos Workout a mostrar.
     */
    private final ArrayList<Workout> WorkoutList;

    /**
     * Constructor que inicializa el modelo con una lista de entrenamientos.
     * 
     * @param workoutList Lista de entrenamientos a mostrar (no puede ser null)
     */
    public WorkoutsTableModel(ArrayList<Workout> workoutList) {
        this.WorkoutList = workoutList;
    }

    /**
     * Devuelve el número de filas de la tabla, equivalente al número de entrenamientos.
     * 
     * @return Cantidad total de entrenamientos en el modelo
     */
    @Override
    public int getRowCount() {
        return WorkoutList.size();
    }

    /**
     * Devuelve el número de columnas del modelo.
     * Esta implementación siempre devuelve 2 (comentarios y fecha).
     * 
     * @return Entero 2 que representa las dos columnas del modelo
     */
    @Override
    public int getColumnCount() {
        return 2;
    }

    /**
     * Obtiene el valor formateado para una celda específica.
     * Para la columna 0 devuelve comentarios en negrita (HTML).
     * Para la columna 1 devuelve la fecha formateada.
     * 
     * @param rowIndex Índice de la fila (0-based)
     * @param columnIndex Índice de la columna (0-based)
     * @return Valor formateado para la celda según el tipo de columna
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Workout workout = WorkoutList.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> "<html><b>" + workout.getComments() + "</b></html>";
            case 1 -> workout.getForDateFormatted();
            default -> null;
        };
    }

    /**
     * Devuelve el nombre de las columnas para el encabezado de la tabla.
     * 
     * @param column Índice de la columna (0-based)
     * @return Nombre de la columna solicitada o cadena vacía para índices inválidos
     */
    @Override
    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "Entrenamiento";
            case 1 -> "Fecha";
            default -> "";
        };
    }

    /**
     * Obtiene el objeto Workout completo correspondiente a una fila específica.
     * 
     * @param index Índice de la fila (0-based)
     * @return Objeto Workout en la posición solicitada, o null si el índice es inválido
     */
    public Workout getEntrenamientoAt(int index) {
        // Verifica si el índice está dentro de los límites de la lista
        if (index >= 0 && index < WorkoutList.size()) {
            return WorkoutList.get(index);
        }
        return null;
    }
}