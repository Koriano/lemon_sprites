package swing.graphic;
import javax.swing.ImageIcon;


/**
 * The implementation of the Image interface
 * @author Margaux SCHNELZAUER
 */
public class Image extends ImageIcon implements gui.graphic.Image {

    private int heigth;
    private int width;
    private String name;


    /**
     * Constructor of the image
     *
     * @pre heigth >= 0 && width >=0 && !(name.equals(""))
     * @param heigth The Image length
     * @param width The image width
     * @param name The image name
     */
    public Image(int heigth, int width, String name){

        if (heigth >= 0 && width >=0 && !(name.equals(""))) {
            this.heigth = heigth;
            this.width = width;
            this.name = name;
        }

        else {
            System.out.println("No conform parameters");
        }

    }


    /**
     * Give the image length
     *
     * @pre heigth >= 0
     * @return the image length
     */
    @Override
    public int getHeigth() {
        int ret;

        if (this.heigth >= 0) {
            ret =  this.heigth;
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
