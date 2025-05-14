package oton.trainerhive.gui.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import javax.imageio.ImageIO;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import oton.trainerhive.dto.User;

/**
 * Generador de avatares de usuario para la aplicación TrainerHive.
 * Proporciona funcionalidad para crear, modificar y almacenar avatares,
 * incluyendo manejo de imágenes personalizadas y generación de avatares por defecto.
 * 
 * <p>Características principales:</p>
 * <ul>
 *   <li>Generación de avatares a partir de fotos de usuario</li>
 *   <li>Creación de avatares por defecto con colores aleatorios</li>
 *   <li>Aplicación de máscaras hexagonales</li>
 *   <li>Escalado automático según la configuración HiDPI</li>
 *   <li>Almacenamiento en caché local</li>
 * </ul>
 * 
 * @author Alfonso Otón
 * @version 1.2
 * @since 2025
 */
public final class UserAvatarGenerator {
    
    /**
     * Paleta de colores predefinidos para avatares por defecto.
     * Colores vivos y accesibles que garantizan buen contraste.
     */
    private static final Color[] AVATAR_COLORS = {
        new Color(52, 152, 219),   // Azul
        new Color(231, 76, 60),    // Rojo
        new Color(46, 204, 113),   // Verde
        new Color(155, 89, 182),   // Púrpura
        new Color(241, 196, 15),   // Amarillo
        new Color(230, 126, 34),   // Naranja
        new Color(26, 188, 156),   // Turquesa
        new Color(41, 128, 185),   // Azul oscuro
        new Color(192, 57, 43),    // Rojo oscuro
        new Color(22, 160, 133),   // Verde azulado
        new Color(142, 68, 173),   // Violeta
        new Color(44, 62, 80),     // Azul marino
        new Color(243, 156, 18),   // Ámbar
        new Color(39, 174, 96),    // Esmeralda
        new Color(211, 84, 0),     // Naranja oscuro
        new Color(127, 140, 141)   // Gris
    };
    
    /** Generador de números aleatorios para selección de colores */
    private static final Random random = new Random();
    
    /**
     * Constructor privado para prevenir instanciación.
     * Esta clase solo debe contener métodos estáticos.
     */
    private UserAvatarGenerator() {
        throw new AssertionError("Clase utilitaria no debe ser instanciada");
    }
    
    /**
     * Crea y almacena un avatar para el usuario especificado.
     * 
     * @param user Usuario para el que generar el avatar
     * @param avatarSize Tamaño deseado del avatar en píxeles (sin escalado HiDPI)
     * @param applyMask Indica si se aplica máscara hexagonal (true) o se mantiene rectangular (false)
     * @throws IllegalArgumentException Si el tamaño del avatar es menor que 1
     */
    public static void createUserAvatar(User user, int avatarSize, boolean applyMask) {
        if (avatarSize < 1) {
            throw new IllegalArgumentException("El tamaño del avatar debe ser al menos 1 píxel");
        }
        
        // Ajustar tamaño según escala HiDPI
        float systemUIScale = Float.parseFloat(System.getProperty("sun.java2d.uiScale", "1.0"));
        int finalAvatarSize = (int) (avatarSize * systemUIScale);
        
        try {
            File outputFile = prepareOutputFile(user);
            BufferedImage originalImage = createBaseImage(user, finalAvatarSize);
            
            // Aplicar máscara si está configurado
            if (applyMask) {
                BufferedImage maskedImage = applyHexagonalMask(originalImage, finalAvatarSize);
                ImageIO.write(maskedImage, "PNG", outputFile);
            } else {
                ImageIO.write(originalImage, "PNG", outputFile);
            }
        } catch (IOException ex) {
            System.err.println("Error al generar avatar: " + ex.getMessage());
        }
    }
    
    /**
     * Prepara el archivo de salida para el avatar.
     * 
     * @param user Usuario para el que se genera el avatar
     * @return Archivo destino para el avatar
     * @throws IOException Si no se puede crear el directorio de caché
     */
    private static File prepareOutputFile(User user) throws IOException {
        String cachePath = System.getProperty("user.home") + "/AppData/Local/TrainerHive/cache";
        File cacheDir = new File(cachePath);
        
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        
        String filename = (user.getPhotoFilename() == null || user.getPhotoFilename().isEmpty())
            ? "default_user_" + user.getId() + ".png"
            : user.getPhotoFilename();
            
        return new File(cacheDir, filename);
    }
    
    /**
     * Crea la imagen base para el avatar.
     * 
     * @param user Usuario para el que se genera el avatar
     * @param size Tamaño final de la imagen
     * @return Imagen procesada lista para aplicar máscara
     */
    private static BufferedImage createBaseImage(User user, int size) throws IOException {
        if (user.getPhoto() != null && user.getPhoto().length > 0) {
            return processUserPhoto(user.getPhoto(), size);
        } else {
            return createDefaultAvatar(user.getId(), size);
        }
    }
    
    /**
     * Procesa la foto de usuario subida.
     * 
     * @param photoData Bytes de la foto del usuario
     * @param size Tamaño deseado de salida
     * @return Imagen redimensionada y centrada
     * @throws IOException Si hay error al procesar la imagen
     */
    private static BufferedImage processUserPhoto(byte[] photoData, int size) throws IOException {
        return Thumbnails.of(new ByteArrayInputStream(photoData))
                .size(size, size)
                .crop(Positions.CENTER)
                .outputQuality(1.0)
                .asBufferedImage();
    }
    
    /**
     * Crea un avatar por defecto cuando el usuario no tiene foto.
     * 
     * @param userId ID del usuario para generación determinista de color
     * @param size Tamaño deseado del avatar
     * @return Avatar generado con color único
     */
    private static BufferedImage createDefaultAvatar(long userId, int size) throws IOException {
        BufferedImage defaultImage;
        
        try (InputStream defaultImageStream = UserAvatarGenerator.class
                .getClassLoader()
                .getResourceAsStream("default-avatar.jpg")) {
                
            if (defaultImageStream != null) {
                defaultImage = Thumbnails.of(defaultImageStream)
                        .size(size, size)
                        .crop(Positions.CENTER)
                        .outputQuality(1.0)
                        .asBufferedImage();
            } else {
                defaultImage = createColoredBackground(size, getRandomAvatarColor(userId));
            }
        } catch (Exception e) {
            defaultImage = createColoredBackground(size, getRandomAvatarColor(userId));
        }
        
        return applyColorTint(defaultImage, getRandomAvatarColor(userId));
    }
    
    /**
     * Aplica máscara hexagonal a la imagen.
     * 
     * @param image Imagen original
     * @param size Tamaño de la máscara
     * @return Imagen con máscara aplicada
     * @throws IOException Si no se puede cargar la máscara
     */
    private static BufferedImage applyHexagonalMask(BufferedImage image, int size) throws IOException {
        try (InputStream maskStream = UserAvatarGenerator.class
                .getClassLoader()
                .getResourceAsStream("images/hexMask.png")) {
            
            if (maskStream != null) {
                BufferedImage mask = Thumbnails.of(ImageIO.read(maskStream))
                        .size(size, size)
                        .asBufferedImage();
                return applyMask(image, mask);
            }
            return image;
        }
    }
    
    /**
     * Crea una imagen de fondo plano con el color especificado.
     * 
     * @param size Tamaño de la imagen
     * @param color Color de fondo
     * @return Imagen generada
     */
    private static BufferedImage createColoredBackground(int size, Color color) {
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.setColor(color);
        g.fillRect(0, 0, size, size);
        g.dispose();
        return image;
    }
    
    /**
     * Obtiene un color consistente para un ID de usuario dado.
     * 
     * @param userId ID del usuario
     * @return Color de la paleta AVATAR_COLORS
     */
    private static Color getRandomAvatarColor(long userId) {
        int colorIndex;
	colorIndex = Math.abs(Long.hashCode(userId) % AVATAR_COLORS.length);
        return AVATAR_COLORS[colorIndex];
    }
    
    /**
     * Aplica un tinte de color a la imagen.
     * 
     * @param image Imagen original
     * @param tintColor Color del tinte
     * @return Imagen con tinte aplicado
     */
    private static BufferedImage applyColorTint(BufferedImage image, Color tintColor) {
        BufferedImage tinted = new BufferedImage(
            image.getWidth(), 
            image.getHeight(), 
            BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g2d = tinted.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.setComposite(AlphaComposite.SrcAtop.derive(0.7f));
        g2d.setColor(tintColor);
        g2d.fillRect(0, 0, image.getWidth(), image.getHeight());
        g2d.dispose();
        
        return tinted;
    }
    
    /**
     * Aplica una máscara de transparencia a la imagen.
     * 
     * @param image Imagen original
     * @param mask Máscara a aplicar
     * @return Imagen con máscara aplicada
     */
    private static BufferedImage applyMask(BufferedImage image, BufferedImage mask) {
        BufferedImage result = new BufferedImage(
            image.getWidth(), 
            image.getHeight(), 
            BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g2d = result.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.setComposite(AlphaComposite.DstIn);
        g2d.drawImage(mask, 0, 0, null);
        g2d.dispose();
        
        return result;
    }
}