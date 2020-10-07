package swing;

import gui.graphic.Image;
import gui.graphic.ImageLoader;


/**
 * The implementation of the ImageImp interface
 * @author Margaux SCHNELZAUER
 * @see gui.graphic.ImageLoader
 */

public class ImageLoaderImp implements ImageLoader {

    /**
     * This method load an image from a path.
     *
     *
     * @param path : the image path
     * @return loadedImage : the loaded image
     */
    @Override
    public Image loadImage(String path) {
        return new ImageImp(path);
    }
}
