package oton.trainerhive.gui.util;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.RenderingHints;
import java.net.URISyntaxException;

import com.kitfox.svg.app.beans.SVGIcon;

/**
 *
 * @author Alfonso Otón
 */
public class SVGRenderer {

    private final float uiScale = Float.parseFloat(System.getProperty("sun.java2d.uiScale", "1.0"));

    public SVGIcon getSVGIcon(String path, int width, int height) {
	try {

	    // Adapta el tamaño a la escala del sistema
	    height = (int) (height * uiScale);
	    width = (int) (width * uiScale);
	    return setupSVG(path, width, height);

	} catch (Exception e) {
	    throw new RuntimeException("Error al generar el icono SVG", e);
	}
    }

    public Image getSVGImage(String path, int height, int width) {
	try {

	    // Adapta el tamaño a la escala del sistema
	    height = (int) (height * uiScale);
	    width = (int) (width * uiScale);

	    // Configura el SVG
	    SVGIcon svg = setupSVG(path, height, width);

	    // Crea imagen de buffer
	    BufferedImage image = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);

	    // Renderiza el SVG en la imagen
	    Graphics2D g2d = image.createGraphics();
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    svg.paintIcon(null, g2d, 0, 0);
	    g2d.dispose();
	    return image;

	} catch (Exception e) {
	    throw new RuntimeException("Error al generar la imagen SVG", e);
	}
    }

    private SVGIcon setupSVG(String path, int height, int width) {

	try {

	    // Configura el SVG
	    SVGIcon svg = new SVGIcon();
	    svg.setSvgURI(getClass().getResource(path + ".svg").toURI());
	    svg.setPreferredSize(new Dimension(height, width));	// Tamaño
	    svg.setScaleToFit(true);				// Se ajusta al tamaño
	    svg.setAntiAlias(true);
	    return svg;

	} catch (URISyntaxException e) {
	    throw new RuntimeException("Error al generar la imagen SVG", e);
	}
    }
}
