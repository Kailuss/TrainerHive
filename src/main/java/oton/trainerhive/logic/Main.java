package oton.trainerhive.logic;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;

import java.util.Collections;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import oton.trainerhive.gui.MainFrame;
import oton.trainerhive.gui.util.SystemUIScale;

/**
 * Clase principal que inicializa la aplicación y configura el tema visual.
 */
public class Main {

    /**
     * Método principal que inicia la aplicación.
     * 
     * @param args (no se usan).
     */
    public static void main(String args[]) {

        // Comprueba y ajusta la escala de la UI del sistema operativo
        SystemUIScale.checkSystemUIScale();

        try {
            // Configura el color de acento para el tema
            FlatLaf.setGlobalExtraDefaults(Collections.singletonMap("@accentColor", "#f5d760"));
            
            // Establece FlatDarkLaf como tema de la aplicación
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException("Error al cambiar el tema de la interfaz", e);
        }

        // Crea y muestra la ventana principal de la aplicación en el hilo de eventos de Swing
        java.awt.EventQueue.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
