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

public class UserAvatarGenerator {
    // Array de colores predefinidos para los avatares por defecto
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
    
    // Objeto Random para generar índices aleatorios
    private static final Random random = new Random();
    
    /**
     * Crea un avatar de usuario y lo guarda en la caché del sistema. Este código ha sido creado siguiendo las instrucciones del módulo Thumbnailator.
     *
     * @param user Objeto Usuario con la imagen en formato de bytes.
     * @param avatarSize Tamaño deseado del avatar en píxeles.
     * @param applyMask Indica si se debe aplicar una máscara hexagonal a la imagen.
     */
    public static void createUserAvatar(User user, int avatarSize, boolean applyMask) {
        // Obtiene la escala de UI del sistema (Windows)
        String systemUIScaleProperty = System.getProperty("sun.java2d.uiScale", "1.0"); // Escala al 100% por defecto
        float systemUIScale = Float.parseFloat(systemUIScaleProperty);
        int finalAvatarSize = (int) (avatarSize * systemUIScale); // Tamaño final del avatar
        try {
            // Define la ruta de la caché
            String systemUserHome = System.getProperty("user.home");
            File cacheDir = new File(systemUserHome, "AppData/Local/TrainerHive/cache");
            
            // Verifica si el nombre del archivo de foto es null y asigna un nombre por defecto si es necesario
            String photoFilename = user.getPhotoFilename();
            if (photoFilename == null || photoFilename.isEmpty()) {
                photoFilename = "default_user_" + user.getId() + ".png";
            }
            
            File outputFile = new File(cacheDir, photoFilename);
            if (!cacheDir.exists()) {
                cacheDir.mkdirs(); // Crea la carpeta de caché si no existe
            }
            
            BufferedImage originalImage;
            boolean isDefaultAvatar = false;
            
            // Verifica si el usuario tiene una foto
            if (user.getPhoto() != null && user.getPhoto().length > 0) {
                // Carga y ajusta la imagen de usuario
                ByteArrayInputStream bis = new ByteArrayInputStream(user.getPhoto());
                originalImage = Thumbnails.of(bis)
                        .size(finalAvatarSize, finalAvatarSize)
                        .crop(Positions.CENTER)
                        .outputQuality(1.0)
                        .asBufferedImage();
            } else {
                // Carga y ajusta la imagen predeterminada
                InputStream defaultImageStream = UserAvatarGenerator.class.getClassLoader().getResourceAsStream("default-avatar.jpg");
                isDefaultAvatar = true;
                
                if (defaultImageStream == null) {
                    System.err.println("No se pudo cargar la imagen por defecto: default-avatar.jpg");
                    // Si no podemos cargar la imagen predeterminada, creamos una imagen en blanco
                    originalImage = createColoredBackgroundImage(finalAvatarSize);
                } else {
                    try {
                        originalImage = Thumbnails.of(defaultImageStream)
                                .size(finalAvatarSize, finalAvatarSize)
                                .crop(Positions.CENTER)
                                .outputQuality(1.0)
                                .asBufferedImage();
                    } catch (Exception e) {
                        System.err.println("Error al procesar la imagen predeterminada: " + e.getMessage());
                        // Si hay error al procesar, creamos una imagen con color aleatorio
                        originalImage = createColoredBackgroundImage(finalAvatarSize);
                    }
                }
                
                // Aplicamos un tinte de color aleatorio si es un avatar por defecto
                if (isDefaultAvatar) {
                    originalImage = applyColorTint(originalImage, getRandomAvatarColor(user.getId()));
                }
            }
            
            InputStream maskFile = UserAvatarGenerator.class.getClassLoader().getResourceAsStream("images/hexMask.png");
            
            // Aplica la máscara si es necesario
            if (applyMask && maskFile != null) {
                // Carga y escala la máscara
                BufferedImage mask = Thumbnails.of(ImageIO.read(maskFile))
                        .size(finalAvatarSize, finalAvatarSize)
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
     * Crea una imagen de fondo con un color aleatorio
     * 
     * @param size Tamaño de la imagen a crear
     * @return BufferedImage con el fondo coloreado
     */
    private static BufferedImage createColoredBackgroundImage(int size) {
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        // Usar un color aleatorio de la lista en lugar de gris claro
        g.setColor(getRandomAvatarColor(System.currentTimeMillis()));
        g.fillRect(0, 0, size, size);
        g.dispose();
        return image;
    }
    
        /**
     * Obtiene un color aleatorio del array de colores predefinidos
     * Para un mismo ID de usuario siempre devuelve el mismo color
     * 
     * @param seed Semilla para generar el color (ID del usuario)
     * @return Color aleatorio para el avatar
     */
    private static Color getRandomAvatarColor(long seed) {
        // Asegúrate de que usuarios diferentes obtengan colores diferentes
        // Hacemos un hash simple del ID para que la distribución sea mejor
        int hashCode = Long.hashCode(seed);
        int colorIndex = Math.abs(hashCode % AVATAR_COLORS.length);
        return AVATAR_COLORS[colorIndex];
    }
    
    /**
     * Aplica un tinte de color a la imagen
     * 
     * @param image Imagen original
     * @param tintColor Color a aplicar como tinte
     * @return Imagen con el tinte aplicado
     */
    private static BufferedImage applyColorTint(BufferedImage image, Color tintColor) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage tinted = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g2d = tinted.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        
        // Aplicar tinte de color usando un composite de mezcla
        g2d.setComposite(AlphaComposite.SrcAtop.derive(0.7f)); // 70% de intensidad del tinte
        g2d.setColor(tintColor);
        g2d.fillRect(0, 0, width, height);
        g2d.dispose();
        
        return tinted;
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