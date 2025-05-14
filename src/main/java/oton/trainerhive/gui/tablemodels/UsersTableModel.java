package oton.trainerhive.gui.tablemodels;
import java.io.File;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import oton.trainerhive.dto.User;

public class UsersTableModel extends AbstractTableModel {
    private final ArrayList<User> userList;
    String systemUserHome = System.getProperty("user.home");
    File cacheDir = new File(systemUserHome, "AppData/Local/TrainerHive/cache");

    public UsersTableModel(ArrayList<User> userList) {
        this.userList = userList;
    }

    @Override
    public int getRowCount() {
        return userList.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = userList.get(rowIndex);
        
        // Determine the correct filename - handle the case when photoFilename is null
        String photoFilename = user.getPhotoFilename();
        if (photoFilename == null || photoFilename.isEmpty()) {
            photoFilename = "default_user_" + user.getId() + ".png";
        }
        
        // Create the full file path
        File imageFile = new File(cacheDir, photoFilename);
        
        // Check if the file actually exists
        if (!imageFile.exists()) {
            // If the file doesn't exist, we'll try to generate it
            oton.trainerhive.gui.util.UserAvatarGenerator.createUserAvatar(user, 40, true);
        }
        
        // Use file:// protocol for local files in HTML
        String imagePath = "file:///" + imageFile.getAbsolutePath().replace("\\", "/");
        
        // Usa HTML para mostrar el nombre y el correo en líneas separadas
        return "<html><table><tr>"
                + "<td><img src='" + imagePath + "' width='40' height='40'></td>" // Columna con la imagen
                + "<td style='padding-left:8px;'><b>" + user.getName() + "</b><br>" + user.getEmail() + "</td>" // Columna con nombre y correo
                + "</tr></table></html>";
    }

    @Override
    public String getColumnName(int column) {
        return "Alumnos"; // Nombre de la columna
    }

    public User getUserAt(int index) {
        // Verifica que el índice esté dentro de los límites de la lista
        if (index >= 0 && index < userList.size()) {
            return userList.get(index);
        }
        return null;
    }
}