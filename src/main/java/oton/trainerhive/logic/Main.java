package oton.trainerhive.logic;

import com.formdev.flatlaf.FlatDarkLaf;
import oton.trainerhive.gui.FramePrincipal;
import oton.trainerhive.gui.util.HiDPIScale;

/**
 *
 * @author Alfonso Otón
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

	HiDPIScale.checkHiDPIScale();							// Comprueba la escala de la UI de la máquina
	try {
	    javax.swing.UIManager.setLookAndFeel(new FlatDarkLaf());			// Establece FlatLaf Dark como el tema de la aplicación
	} catch (javax.swing.UnsupportedLookAndFeelException e) {
	}
	java.awt.EventQueue.invokeLater(() -> new FramePrincipal().setVisible(true));	// Crea y muestra el frame principal
    }
}
