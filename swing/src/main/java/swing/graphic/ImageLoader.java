package swing.graphic;
import gui.graphic.Image;
import javax.swing.ImageIcon;


/**
 * The implementation of the Image interface
 * @author Margaux SCHNELZAUER
 * @see gui.graphic.ImageLoader
 */

public class ImageLoader implements gui.graphic.ImageLoader {

    /**
     * This method load an image from a path, using an imageIcon.
     *
     *
     * @param path : the image path
     * @return the loaded image
     */
    @Override
    public Image loadImage(String path) {

        //create an imageIcon loaded an image from a path
        ImageIcon image = new ImageIcon(path);

        // get imageIcon properties
        int height = image.getIconHeight();
        int width = image.getIconWidth();
        String name = image.getDescription();

        // create and return  an Image from the ImageIcon
        return new swing.graphic.Image(height, width, name);
    }
}
