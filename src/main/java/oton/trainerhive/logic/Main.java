package oton.trainerhive.logic;

import com.formdev.flatlaf.FlatDarkLaf;
import oton.trainerhive.gui.MainFrame;
import oton.trainerhive.gui.util.SystemUIScale;

/**
 *
 * @author Alfonso Otón
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

	SystemUIScale.checkSystemUIScale();						// Comprueba la escala de la UI de la máquina

	try {
	    javax.swing.UIManager.setLookAndFeel(new FlatDarkLaf());			// Establece FlatLaf Dark como el tema de la aplicación
	} catch (javax.swing.UnsupportedLookAndFeelException e) {
	}
	java.awt.EventQueue.invokeLater(() -> new MainFrame().setVisible(true));	// Crea y muestra el frame principal
    }
}
