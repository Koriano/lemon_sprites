package swing.graphic;

import gui.graphic.Image;



/**
 * The implementation of the Image interface
 * @author Margaux SCHNELZAUER
 * @see gui.graphic.ImageLoader
 */

public class ImageLoader implements gui.graphic.ImageLoader {

    /**
     * This method load an image from a path.
     *
     *
     * @param path : the image path
     * @return loadedImage : the loaded image
     */
    @Override
    public Image loadImage(String path) {
        return new swing.graphic.Image(path);
    }
}
