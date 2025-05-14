package oton.trainerhive.gui.util;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

/**
 * Clase utilitaria para la gestión de escalado en entornos HiDPI.
 * Detecta automáticamente la escala del sistema operativo y configura
 * la propiedad Java correspondiente para un renderizado óptimo en pantallas
 * de alta densidad (2K, 4K, Retina, etc.).
 * 
 * @author Alfonso Otón
 * @version 1.2
 * @since 2025
 */
public class SystemUIScale {

    /**
     * Detecta y configura automáticamente la escala de la interfaz de usuario.
     * Obtiene la escala del sistema desde la configuración gráfica predeterminada
     * y la establece en la propiedad del sistema {@code sun.java2d.uiScale}.
     * 
     * <p>Este método debe llamarse al inicio de la aplicación, antes de crear
     * cualquier componente de la interfaz gráfica.</p>
     * 
     * <p><b>Nota:</b> La propiedad solo tiene efecto si se establece antes de
     * inicializar el subsistema gráfico de Java.</p>
     * 
     * @throws IllegalStateException Si no se puede acceder a la configuración gráfica
     */
    public static void checkSystemUIScale() {
        try {
            GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice();
            GraphicsConfiguration graphicsConfiguration = graphicsDevice.getDefaultConfiguration();
            
            // Obtiene el factor de escala del sistema (normalmente 1.0, 1.25, 1.5, 2.0, etc.)
            double systemUIScale = graphicsConfiguration.getDefaultTransform().getScaleX();
            
            // Establece la propiedad del sistema para escalado Java2D
            System.setProperty("sun.java2d.uiScale", String.valueOf(systemUIScale));
        } catch (Exception e) {
            throw new IllegalStateException("No se pudo detectar la escala del sistema", e);
        }
    }
}