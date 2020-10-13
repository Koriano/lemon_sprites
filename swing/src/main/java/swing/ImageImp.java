package swing;
import gui.graphic.Image;

import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;


/**
 * The implementation of the ImageImp interface
 *
 * @author Margaux SCHNELZAUER
 * @see gui.graphic.Image
 *
 * @inv this.loadedImage.getHeight() >= 0 && this.loadedImage.getWidth() >= 0 && this.name != null &&!(this.name.equals(""))
 */

public class ImageImp implements Image {

    /**
     * The name of the image
     */
    private String name;

    /**
     * The data of the image
     */
    private BufferedImage loadedImage;

    /**
     * Constructor of the image
     *
     * @pre image != null && name != null
     * @param image The loaded image
     */
    public ImageImp(BufferedImage image){
        assert image != null;
        this.loadedImage = image;
    }


    /**
     * Set the image name
     *
     * @param name : the image name
     * @pre name != null && !"".equals(name)
     */
    @Override
    public void setName(String name) {
        assert name != null && !"".equals(name);

        this.name = name;
    }

    /**
     * Return the image height
     *
     * @pre this.loadedImage.getHeight() >= 0
     * @return The image height
     */
    @Override
    public int getHeight() {
        assert this.loadedImage.getHeight() >= 0;

        return this.loadedImage.getHeight();
    }


    /**
     * Return the image width
     *
     * @pre this.loadedImage.getWidth() >= 0
     * @return The image width
     */
    @Override
    public int getWidth() {
        assert this.loadedImage.getWidth() >= 0;

        return this.loadedImage.getWidth();
    }


    /**
     * Return the image name
     *
     * @pre this.name != null && !"".equals(this.name)
     * @return The image name
     */
    @Override
    public String getName() {
        assert this.name != null && !"".equals(this.name);

        return this.name;
    }

    /**
     * Gives the loaded image
     *
     * @pre this.loadedImage != null
     * @return the loaded image
     */
    public BufferedImage getLoadedImage(){
        assert this.loadedImage != null;

        return this.loadedImage;
    }
}
