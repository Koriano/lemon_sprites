package swing.graphic;
import javax.swing.ImageIcon;


/**
 * The implementation of the Image interface
 * @author Margaux SCHNELZAUER
 */
public class Image implements gui.graphic.Image {

    private int height;
    private int width;
    private String name;


    /**
     * Constructor of the image
     *
     * @pre height >= 0 && width >=0 && !(name.equals(""))
     * @param height The Image length
     * @param width The image width
     * @param name The image name
     */
    public Image(int height, int width, String name){

        if (height >= 0 && width >=0 && !(name.equals(""))) {
            this.height = height;
            this.width = width;
            this.name = name;
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
        int ret;

        if (this.height >= 0) {
            ret =  this.height;
        }

        else {
            System.out.println("No conform parameters");
            ret = 0;
        }

        return ret;

    }
    

    /**
     * Give the image width
     *
     * @pre width >=0
     * @return the image width
     */
    @Override
    public int getWidth() {
        int ret;

        if (this.width >= 0) {
            ret = this.width;
        }

        else {
            System.out.println("No conform parameters");
            ret = 0;
        }

        return ret;
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
}
