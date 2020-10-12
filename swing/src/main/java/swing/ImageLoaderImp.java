package swing;

import gui.graphic.Image;
import util.io.Loader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;

/**
 * The implementation of the ImageImp interface
 *
 * @author Margaux SCHNELZAUER
 * @see Loader
 */

public class ImageLoaderImp implements Loader<Image> {

    /**
     * Load object from an input stream
     *
     * @pre stream != null
     *
     * @param stream: the stream containing the file
     * @return the object loaded
     */
    @Override
    public Image load(InputStream stream){
        ImageImp image = null;

        if(stream != null){
            try {
                BufferedImage loadedImage = ImageIO.read(stream);
                image = new ImageImp(loadedImage);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            System.out.println("No conform parameter");
        }

        return image;
    }
}
