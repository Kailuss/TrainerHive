package oton.trainerhive.gui.util;

import java.awt.Color;

/**
 * Clase de constantes para la interfaz gráfica de TrainerHive.
 * Contiene definiciones de colores, dimensiones y valores de diseño
 * consistentes para toda la aplicación.
 * 
 * <p>Esta clase no puede ser instanciada (utiliza patrón de constantes).</p>
 * 
 * @author Alfonso Otón
 * @version 1.2
 * @since 2025
 */
public final class UIConstants {
    
    // ========== COLORES DE LA INTERFAZ ========== //
    
    /**
     * Color gris oscuro utilizado como fondo principal (#262829)
     * RGB: 38, 40, 41
     */
    public static final Color GRIS_OSCURO = new Color(38, 40, 41);
    
    /**
     * Color gris medio utilizado para bordes y elementos secundarios (#5A5E60)
     * RGB: 90, 94, 96
     */
    public static final Color GRIS_MEDIO = new Color(90, 94, 96);

    // ========== ESPACIADO Y PADDING ========== //
    
    /**
     * Padding izquierdo estándar para celdas (16 píxeles)
     */
    public static final int CELL_PADDING_LEFT = 16;
    
    /**
     * Padding derecho estándar para celdas (16 píxeles)
     */
    public static final int CELL_PADDING_RIGHT = 16;
    
    // ========== ANCHOS DE COLUMNAS ========== //
    
    /**
     * Ancho estándar para columnas de alumnos (239 píxeles)
     */
    public static final int COLUMN_WIDTH_ALUMNOS = 239;
    
    /**
     * Ancho estándar para la primera columna de entrenamientos (240 píxeles)
     */
    public static final int COLUMN_WIDTH_ENTRENAMIENTOS_1 = 240;
    
    /**
     * Ancho estándar para la segunda columna de entrenamientos (240 píxeles)
     */
    public static final int COLUMN_WIDTH_ENTRENAMIENTOS_2 = 240;
    
    /**
     * Ancho estándar para columnas de ejercicios (239 píxeles)
     */
    public static final int COLUMN_WIDTH_EJERCICIOS = 239;

    /**
     * Constructor privado para prevenir instanciación.
     * Esta clase solo debe contener constantes.
     */
    private UIConstants() {
        throw new AssertionError("Clase de constantes no debe ser instanciada");
    }
}