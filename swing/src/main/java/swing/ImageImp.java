package swing;
import gui.graphic.Image;

import javax.swing.ImageIcon;


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
    private int width;
    private int height;
    private ImageIcon loadedImage;

    /**
     * Constructor of the image
     *
     * @pre !path.equals("")
     * @param path The image path
     */
    public ImageImp(String path){

        if (!path.equals("")) {
            this.loadedImage = new ImageIcon(path);
            this.width = this.loadedImage.getIconWidth();
            this.height = this.loadedImage.getIconHeight();

            String[] parts = path.split("/");
            this.name = parts[parts.length-1];
       }
        else {
            System.out.println("No conform parameters");
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
        return this.height;
    }


    /**
     * Return the image width
     *
     * @pre this.width >= 0
     * @return The image width
     */
    @Override
    public int getWidth() {
        return this.width;
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
    public ImageIcon getLoadedImage(){

        ImageIcon img = null;

        if(this.loadedImage != null){
            img = this.loadedImage;
        }

        return img;
    }
}
