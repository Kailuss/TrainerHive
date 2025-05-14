package oton.trainerhive.gui.util;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.RenderingHints;
import java.net.URISyntaxException;

import com.kitfox.svg.app.beans.SVGIcon;

/**
 * Clase para renderizar imágenes SVG en componentes Swing.
 * Proporciona métodos para convertir archivos SVG en iconos o imágenes rasterizadas,
 * con soporte para escalado según la configuración del sistema.
 * 
 * @author Alfonso Otón
 * @version 1.2
 * @since 2025
 */
public class SVGRenderer {

    /** 
     * Factor de escala de la interfaz del sistema.
     * Se obtiene de la propiedad "sun.java2d.uiScale" (valor por defecto 1.0)
     */
    private final float uiScale = Float.parseFloat(System.getProperty("sun.java2d.uiScale", "1.0"));

    /**
     * Genera un SVGIcon a partir de un archivo SVG.
     * 
     * @param path Ruta relativa al archivo SVG (sin extensión)
     * @param width Ancho deseado (antes de aplicar escala)
     * @param height Alto deseado (antes de aplicar escala)
     * @return SVGIcon configurado y escalado
     * @throws RuntimeException Si ocurre algún error al cargar o procesar el SVG
     */
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

    /**
     * Genera una Image rasterizada a partir de un archivo SVG.
     * 
     * @param path Ruta relativa al archivo SVG (sin extensión)
     * @param height Alto deseado (antes de aplicar escala)
     * @param width Ancho deseado (antes de aplicar escala)
     * @return Imagen rasterizada del SVG
     * @throws RuntimeException Si ocurre algún error al renderizar el SVG
     */
    public Image getSVGImage(String path, int height, int width) {
        try {
            // Adapta el tamaño a la escala del sistema
            height = (int) (height * uiScale);
            width = (int) (width * uiScale);

            // Configura el SVG
            SVGIcon svg = setupSVG(path, height, width);

            // Crea imagen de buffer
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            // Renderiza el SVG en la imagen con calidad
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

    /**
     * Configura un SVGIcon con los parámetros básicos.
     * 
     * @param path Ruta relativa al archivo SVG (sin extensión)
     * @param height Alto deseado (ya escalado)
     * @param width Ancho deseado (ya escalado)
     * @return SVGIcon configurado
     * @throws URISyntaxException Si la URI del recurso es inválida
     */
    private SVGIcon setupSVG(String path, int width, int height) throws URISyntaxException {
        // Configura el SVG
        SVGIcon svg = new SVGIcon();
        svg.setSvgURI(getClass().getResource(path + ".svg").toURI());
        svg.setPreferredSize(new Dimension(width, height));  // Tamaño
        svg.setScaleToFit(true);                            // Se ajusta al tamaño
        svg.setAntiAlias(true);                             // Suavizado de bordes
        return svg;
    }
}