package swing;

import gui.graphic.Image;
import gui.graphic.ImageLoader;

/**
 * The implementation of the ImageImp interface
 *
 * @author Margaux SCHNELZAUER
 * @see gui.graphic.ImageLoader
 */

public class ImageLoaderImp implements ImageLoader {

    /**
     * Method to load an image
     *
     * @pre path != null && !path.equals("")
     *
     * @param path : the image path
     * @return the loaded image
     */
    @Override
    public Image loadImage(String path) {
        ImageImp image = null;

        if(path != null && !path.equals("")){
            image = new ImageImp(path);
        }
        else{
            System.out.println("No conform parameter");
        }

        return image;
    }
}
