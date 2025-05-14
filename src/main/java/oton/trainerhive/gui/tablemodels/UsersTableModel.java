package oton.trainerhive.gui.tablemodels;

import java.io.File;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import oton.trainerhive.dto.User;

/**
 * Modelo de tabla especializado para mostrar usuarios con imágenes y formato HTML.
 * Proporciona una representación visual enriquecida con avatares de usuario.
 * 
 * @author Alfonso Otón
 * @version 1.2
 * @since 2025
 */
public class UsersTableModel extends AbstractTableModel {

    /** 
     * Lista de usuarios que alimenta el modelo de tabla.
     * Contiene todos los objetos User a mostrar.
     */
    private final ArrayList<User> userList;
    
    /** 
     * Ruta del directorio home del sistema para almacenar caché de imágenes.
     * Se obtiene automáticamente del sistema.
     */
    private final String systemUserHome = System.getProperty("user.home");
    
    /** 
     * Directorio de caché donde se almacenan las imágenes de perfil.
     * Ubicado en AppData/Local/TrainerHive/cache dentro del user home.
     */
    private final File cacheDir = new File(systemUserHome, "AppData/Local/TrainerHive/cache");

    /**
     * Constructor que inicializa el modelo con una lista de usuarios.
     * 
     * @param userList Lista de usuarios a mostrar en la tabla (no puede ser null)
     */
    public UsersTableModel(ArrayList<User> userList) {
        this.userList = userList;
    }

    /**
     * Devuelve el número de filas de la tabla, equivalente al número de usuarios.
     * 
     * @return Cantidad total de usuarios en el modelo
     */
    @Override
    public int getRowCount() {
        return userList.size();
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
     * Obtiene el valor formateado para una celda específica con formato HTML.
     * Incluye el avatar del usuario, nombre en negrita y email en línea separada.
     * Gera automáticamente avatares si no existen en caché.
     * 
     * @param rowIndex Índice de la fila (0-based)
     * @param columnIndex Índice de la columna (0-based)
     * @return Texto formateado en HTML para visualización en la celda
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = userList.get(rowIndex);
        
        // Determine el nombre de archivo - maneja casos cuando photoFilename es null
        String photoFilename = user.getPhotoFilename();
        if (photoFilename == null || photoFilename.isEmpty()) {
            photoFilename = "default_user_" + user.getId() + ".png";
        }
        
        // Crea la ruta completa del archivo
        File imageFile = new File(cacheDir, photoFilename);
        
        // Verifica si el archivo existe realmente
        if (!imageFile.exists()) {
            // Si el archivo no existe, intenta generarlo
            oton.trainerhive.gui.util.UserAvatarGenerator.createUserAvatar(user, 40, true);
        }
        
        // Usa protocolo file:// para archivos locales en HTML
        String imagePath = "file:///" + imageFile.getAbsolutePath().replace("\\", "/");
        
        return "<html><table><tr>"
                + "<td><img src='" + imagePath + "' width='40' height='40'></td>"
                + "<td style='padding-left:8px;'><b>" + user.getName() + "</b><br>" + user.getEmail() + "</td>"
                + "</tr></table></html>";
    }

    /**
     * Devuelve el nombre de la columna para el encabezado de la tabla.
     * 
     * @param column Índice de la columna (0-based)
     * @return El título "Alumnos" para la única columna
     */
    @Override
    public String getColumnName(int column) {
        return "Alumnos";
    }

    /**
     * Obtiene el objeto User completo correspondiente a una fila específica.
     * 
     * @param index Índice de la fila (0-based)
     * @return Objeto User en la posición solicitada, o null si el índice es inválido
     */
    public User getUserAt(int index) {
        // Verifica que el índice esté dentro de los límites de la lista
        if (index >= 0 && index < userList.size()) {
            return userList.get(index);
        }
        return null;
    }
}