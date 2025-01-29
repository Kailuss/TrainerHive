package oton.trainerhive.gui.util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import oton.trainerhive.dto.Usuario;

public class UserAvatarGenerator {

    /**
     * Crea un avatar de usuario y lo guarda en la caché del sistema. Este código ha sido creado siguiendo las instrucciones del módulo Thumbnailator.
     *
     * @param usuario Objeto Usuario con la imagen en formato de bytes.
     * @param size Tamaño deseado del avatar en píxeles.
     * @param applyMask Indica si se debe aplicar una máscara hexagonal a la imagen.
     */
    public static void createUserAvatar(Usuario usuario, int size, boolean applyMask) {
	// Obtiene la escala de UI del sistema (Windows)
	String uiScaleProperty = System.getProperty("sun.java2d.uiScale", "1.0"); // Escala al 100% por defecto
	float uiScale = Float.parseFloat(uiScaleProperty);
	int avatarSize = (int) (size * uiScale); // Tamaño final del avatar

	try {
	    // Define la ruta de la caché
	    String userHome = System.getProperty("user.home");
	    File cacheDir = new File(userHome, "AppData/Local/TrainerHive/cache");
	    File outputFile = new File(cacheDir, usuario.getFotoFilename());
	    InputStream maskFile = UserAvatarGenerator.class.getClassLoader().getResourceAsStream("images/hexMask.png");

	    if (!cacheDir.exists()) {
		cacheDir.mkdirs(); // Crea la carpeta de caché si no existe
	    }

	    // Carga y ajusta la imagen de usuario
	    ByteArrayInputStream bis = new ByteArrayInputStream(usuario.getFoto());
	    BufferedImage originalImage = Thumbnails.of(bis)
		    .size(avatarSize, avatarSize)
		    .crop(Positions.CENTER)
		    .outputQuality(1.0)
		    .asBufferedImage();

	    // Aplica la máscara si es necesario
	    if (applyMask) {
		// Carga y escala la máscara
		BufferedImage mask = Thumbnails.of(ImageIO.read(maskFile))
			.size(avatarSize, avatarSize)
			.asBufferedImage();
		BufferedImage hexThumbnail = applyMask(originalImage, mask);
		ImageIO.write(hexThumbnail, "PNG", outputFile);
	    } else {
		ImageIO.write(originalImage, "PNG", outputFile);
	    }
	} catch (IOException ex) {
	    System.err.println("Error al guardar la foto en caché: " + ex);
	}
    }

    /**
     * Aplica una máscara de transparencia a una imagen. Esta clase ha sido optimizada siguiendo instrucciones de ChatGPT
     *
     * @param image Imagen original a la que se aplicará la máscara.
     * @param mask Imagen de la máscara que define las áreas visibles.
     * @return Imagen con la máscara aplicada.
     */
    private static BufferedImage applyMask(BufferedImage image, BufferedImage mask) {
	int width = image.getWidth();
	int height = image.getHeight();

	// Crea una nueva imagen con canal alfa (transparencia)
	BufferedImage maskedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	Graphics2D g2d = maskedImage.createGraphics();

	// Dibuja la imagen original
	g2d.drawImage(image, 0, 0, null);

	// Aplica la máscara con AlphaComposite
	g2d.setComposite(AlphaComposite.DstIn);
	g2d.drawImage(mask, 0, 0, null);

	g2d.dispose();
	return maskedImage;
    }
}
