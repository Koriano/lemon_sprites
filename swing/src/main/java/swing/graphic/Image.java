package swing.graphic;
import javax.swing.ImageIcon;


/**
 * The implementation of the Image interface
 * @author Margaux SCHNELZAUER
 */
public class Image extends ImageIcon implements gui.graphic.Image {

    private int length;
    private int width;
    private String name;


    /**
     * Constructor of the image
     *
     * @param length The Image length
     * @param width The image width
     * @param name The image name
     */
    public Image(int length, int width, String name){
        this.length = length;
        this.width = width;
        this.name = name;
    }


    /**
     * Give the image length
     *
     * @return the image length
     */
    @Override
    public int getLength() {
        return this.length;
    }


    /**
     * Give the image width
     *
     * @return the image width
     */
    @Override
    public int getWidth() {
        return this.width;
    }


    /**
     * Give the image name
     *
     * @return the image name
     */
    @Override
    public String getName() {
        return this.name;
    }
}
