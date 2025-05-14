package oton.trainerhive.logic;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import java.util.Collections;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import oton.trainerhive.gui.MainFrame;
import oton.trainerhive.gui.util.SystemUIScale;

/**
 * Clase principal de la aplicación TrainerHive.
 * 
 * <p>Responsabilidades:</p>
 * <ul>
 *   <li>Configurar el aspecto visual de la aplicación</li>
 *   <li>Inicializar el escalado HiDPI</li>
 *   <li>Lanzar la ventana principal</li>
 * </ul>
 * 
 * @author Alfonso Otón
 * @version 1.2
 * @since 2025
 */
public class Main {

    /** Color de acento principal para la interfaz (amarillo dorado) */
    private static final String ACCENT_COLOR = "#f5d760";

    /**
     * Punto de entrada principal de la aplicación.
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     * @throws RuntimeException Si ocurre un error al configurar el tema visual
     */
    public static void main(String args[]) {
        configureSystemUI();
        setupLookAndFeel();
        launchApplication();
    }

    /**
     * Configura el escalado de la interfaz según el sistema operativo.
     */
    private static void configureSystemUI() {
        SystemUIScale.checkSystemUIScale();
    }

    /**
     * Configura el tema visual de la aplicación.
     * 
     * @throws RuntimeException Si el tema no es compatible con el sistema
     */
    private static void setupLookAndFeel() {
        try {
            // Configura el color de acento global
            FlatLaf.setGlobalExtraDefaults(
                Collections.singletonMap("@accentColor", ACCENT_COLOR)
            );
            
            // Establece el tema oscuro FlatLaf
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException("Error al configurar el tema visual", e);
        }
    }

    /**
     * Inicia la aplicación mostrando la ventana principal.
     * Se ejecuta en el hilo de eventos de Swing (EDT).
     */
    private static void launchApplication() {
        java.awt.EventQueue.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}