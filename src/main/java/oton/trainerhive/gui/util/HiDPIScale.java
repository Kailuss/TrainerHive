package oton.trainerhive.gui.util;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

/**
 * Clase para detectar y establecer la escala de UI en entornos HiDPI.
 *
 * Esta clase obtiene la escala del sistema y la almacena en la propiedad {@code sun.java2d.uiScale}, lo que permite adaptar los elementos gráficos a pantallas de alta resolución (2K/4K). (Este código ha sido optimizado con instrucciones de ChatGPT)
 *
 * @author Kailuss
 */
public class HiDPIScale {

    public static void checkHiDPIScale() {
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	GraphicsConfiguration gc = gd.getDefaultConfiguration();
	double scale = gc.getDefaultTransform().getScaleX();
	System.setProperty("sun.java2d.uiScale", String.valueOf(scale));
    }
}
