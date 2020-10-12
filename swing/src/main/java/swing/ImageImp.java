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
 * @inv this.height >= 0 && this.width >= 0 && !(this.name.equals(""))
 */

public class ImageImp implements Image {

    private String name;
    private BufferedImage loadedImage;

    /**
     * Constructor of the image
     *
     * @pre image != null
     * @param image The loaded image
     */
    public ImageImp(BufferedImage image){

        if (image != null) {
            this.loadedImage = image;
            this.name = name;
       }
        else {
            System.out.println("No conform parameters");
        }
    }


    /**
     * Set the image name
     *
     * @param name : the image name
     * @pre name != null && !"".equals(name)
     */
    @Override
    public void setName(String name) {
        if(name != null && !"".equals(name)){
            this.name = name;
        }
    }

    /**
     * Return the image height
     *
     * @pre this.height >= 0
     * @return The image height
     */
    @Override
    public int getHeight() {
        return this.loadedImage.getHeight();
    }


    /**
     * Return the image width
     *
     * @pre this.width >= 0
     * @return The image width
     */
    @Override
    public int getWidth() {
        return this.loadedImage.getWidth();
    }


    /**
     * Return the image name
     *
     * @pre !(this.name.equals(""))
     * @return The image name
     */
    @Override
    public String getName() {

        if (!(this.name.equals(""))) {
            return this.name;
        }

        else {
            System.out.println("No conform parameters");
            return "";
        }
    }

    /**
     * Gives the loaded image
     *
     * @pre this.loadedImage != null
     * @return the loaded image
     */
    public BufferedImage getLoadedImage(){

        BufferedImage img = null;

        if(this.loadedImage != null){
            img = this.loadedImage;
        }

        return img;
    }
}
