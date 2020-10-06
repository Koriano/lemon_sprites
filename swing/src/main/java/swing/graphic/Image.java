package swing.graphic;
import javax.swing.ImageIcon;


/**
 * The implementation of the Image interface
 * @author Margaux SCHNELZAUER
 * @see gui.graphic.Image
 */
public class Image extends ImageIcon implements gui.graphic.Image {

    private int height;
    private int width;
    private String name;


    /**
     * Constructor of the image
     *
     * @pre !(name.equals("")) && !(path.equals(""))
     * @param path The image path
     */
    public Image(String path){

        super(path);
        if (!(path.equals(""))) {
            this.name = path;
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
        return super.getIconHeight();
    }


    /**
     * Give the image width
     *
     * @pre width >=0
     * @return the image width
     */
    @Override
    public int getWidth() {
        return super.getIconWidth();
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
