package oton.trainerhive.gui.util;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Gestor de fuentes para la aplicación TrainerHive.
 * Permite cargar, registrar y aplicar la familia de fuentes Outfit con diferentes estilos.
 * Implementa un sistema de caché para fuentes cargadas y proporciona métodos para
 * aplicar estilos de fuente a componentes Swing de forma jerárquica.
 * 
 * @author Alfonso Otón
 * @version 1.2
 * @since 2025
 */
public class FontManager {

    /** Mapa que almacena las fuentes cargadas por estilo */
    private static final Map<String, Font> fonts = new HashMap<>();
    
    /** Clave para el estilo regular de la fuente */
    private static final String REGULAR_STYLE = "regular";
    
    /** Ruta al archivo de fuente regular */
    private static final String REGULAR_FONT_PATH = "/fonts/Outfit-Regular.ttf";
    
    /** Clave para el estilo negrita de la fuente */
    private static final String BOLD_STYLE = "bold";
    
    /** Ruta al archivo de fuente negrita */
    private static final String BOLD_FONT_PATH = "/fonts/Outfit-Bold.ttf";

    /**
     * Carga las fuentes necesarias si no han sido cargadas previamente.
     * Registra las fuentes en el sistema para su uso global.
     * 
     * @throws RuntimeException Si ocurre un error al cargar o registrar las fuentes
     */
    public static void loadFonts() {
        if (!fonts.isEmpty()) {
            return;
        }

        try {
            loadAndRegisterFont(REGULAR_FONT_PATH, REGULAR_STYLE);
            loadAndRegisterFont(BOLD_FONT_PATH, BOLD_STYLE);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException("Error al cargar las fuentes", e);
        }
    }

    /**
     * Carga una fuente desde un archivo y la registra en el sistema.
     * 
     * @param fontPath Ruta relativa al archivo de fuente
     * @param styleName Identificador del estilo de fuente
     * @throws IOException Si no se puede leer el archivo de fuente
     * @throws FontFormatException Si el archivo no contiene una fuente válida
     */
    private static void loadAndRegisterFont(String fontPath, String styleName)
            throws IOException, FontFormatException {
        InputStream fontStream = FontManager.class.getResourceAsStream(fontPath);
        if (fontStream == null) {
            throw new IOException("Archivo de fuente no encontrado: " + fontPath);
        }
        
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
        fonts.put(styleName, font);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
    }

    /**
     * Aplica fuentes a todos los componentes dentro de un contenedor.
     * Usa fuente regular para componentes normales y negrita para títulos.
     * 
     * @param container Contenedor padre y sus hijos
     * @param regularSize Tamaño para la fuente regular (en puntos)
     * @param boldSize Tamaño para la fuente negrita (en puntos)
     */
    public static void applyFontToContainer(Container container, float regularSize, float boldSize) {
        loadFonts();
        applyFontToComponents(container, regularSize, boldSize);
    }

    /**
     * Recorre recursivamente todos los componentes de un contenedor aplicando fuentes.
     * 
     * @param container Contenedor padre
     * @param regularSize Tamaño para fuente regular
     * @param boldSize Tamaño para fuente negrita
     */
    private static void applyFontToComponents(Container container, float regularSize, float boldSize) {
        Component[] components = container.getComponents();

        for (Component component : components) {
            if (component instanceof JLabel && ((JLabel) component).getText() != null) {
                // Aplica negrita a los JLabel que contienen texto
                applyFont((JComponent) component, BOLD_STYLE, boldSize);
            } else if (component instanceof JComponent) {
                applyFont((JComponent) component, REGULAR_STYLE, regularSize);
            }

            if (component instanceof Container) {
                applyFontToComponents((Container) component, regularSize, boldSize);
            }
        }
    }

    /**
     * Aplica un estilo de fuente específico a un componente Swing.
     * 
     * @param component Componente a modificar
     * @param style Estilo de fuente ("regular" o "bold")
     * @param size Tamaño de la fuente en puntos
     */
    public static void applyFont(JComponent component, String style, float size) {
        loadFonts();
        Font font = fonts.getOrDefault(style, fonts.get(REGULAR_STYLE));
        if (font != null) {
            component.setFont(font.deriveFont(size));
            component.putClientProperty("fontStyle", style); // Almacena el estilo aplicado
        }
    }
}