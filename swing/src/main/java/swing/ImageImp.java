package swing;
import gui.graphic.Image;

import javax.swing.ImageIcon;


/**
 * The implementation of the ImageImp interface
 * @author Margaux SCHNELZAUER
 * @see gui.graphic.Image
 */
public class ImageImp implements Image {

    private String name;
    private int width;
    private int height;
    private ImageIcon loadedImage;

    /**
     * Constructor of the image
     *
     * @pre !path.equals("") && width >= 0 && height >= 0
     * @param path The image path
     */
    public ImageImp(String path){

        if (!path.equals("") && width >= 0 && height >= 0) {
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
     * Give the image height
     *
     * @pre height >= 0
     * @return the image height
     */
    @Override
    public int getHeight() {
        return this.height;
    }


    /**
     * Give the image width
     *
     * @pre width >=0
     * @return the image width
     */
    @Override
    public int getWidth() {
        return this.width;
    }


    /**
     * Give the image name
     *
     * @pre !(name.equals(""))
     * @return the image name
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
