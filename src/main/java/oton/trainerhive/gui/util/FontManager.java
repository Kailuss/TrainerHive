package oton.trainerhive.gui.util;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Gestor de fuentes para la aplicación. Permite cargar y aplicar diferentes estilos de la fuente Outfit. (Este código está inspirado en respuestas de Stack Overflow)
 *
 * @author Alfonso Otón
 */
public class FontManager {

    private static final Map<String, Font> fonts = new HashMap<>();
    private static final String REGULAR_STYLE = "regular";
    private static final String REGULAR_FONT_PATH = "/fonts/Outfit-Regular.ttf";
    private static final String BOLD_STYLE = "bold";
    private static final String BOLD_FONT_PATH = "/fonts/Outfit-Bold.ttf";

    /**
     * Carga las fuentes Regular y Bold si no han sido cargadas previamente.
     *
     * @throws RuntimeException si hay un error al cargar las fuentes
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
     * Carga y registra una fuente específica en el sistema.
     *
     * @param fontPath ruta al archivo de la fuente
     * @param styleName nombre del estilo de la fuente
     * @throws IOException si hay un error al leer el archivo
     * @throws FontFormatException si el formato de la fuente es inválido
     */
    private static void loadAndRegisterFont(String fontPath, String styleName)
	    throws IOException, FontFormatException {
	InputStream fontStream = FontManager.class.getResourceAsStream(fontPath);
	Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
	fonts.put(styleName, font);

	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	ge.registerFont(font);
    }

    /**
     * Aplica las fuentes a todos los componentes de un contenedor.
     *
     * @param container contenedor al que aplicar las fuentes
     * @param regularSize tamaño para la fuente regular
     * @param boldSize tamaño para la fuente negrita
     */
    public static void applyFontToContainer(Container container, float regularSize, float boldSize) {
	loadFonts();
	applyFontToComponents(container, regularSize, boldSize);
    }

    /**
     * Aplica las fuentes a los componentes de un contenedor.
     */
    private static void applyFontToComponents(Container container, float regularSize, float boldSize) {
	Component[] components = container.getComponents();

	for (Component component : components) {
	    if (component instanceof JComponent) {
		applyFont((JComponent) component, REGULAR_STYLE, regularSize);
	    }

	    if (component instanceof Container) {
		applyFontToComponents((Container) component, regularSize, boldSize);
	    }
	}
    }

    /**
     * Aplica un estilo y tamaño de fuente específico a un componente.
     *
     * @param component componente al que aplicar la fuente
     * @param style estilo de la fuente ("regular" o "bold")
     * @param size tamaño de la fuente
     */
    public static void applyFont(JComponent component, String style, float size) {
	loadFonts();
	Font font = fonts.getOrDefault(style, fonts.get(REGULAR_STYLE));
	component.setFont(font.deriveFont(size));
    }
}
