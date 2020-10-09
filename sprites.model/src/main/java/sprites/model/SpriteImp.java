package sprites.model;

import gui.graphic.Image;

import java.util.ArrayList;

/**
 * A class to implement Sprite Interface
 *
 * @author Margaux SCHNELZAUER
 * @see sprites.model.Sprite
 */

public class SpriteImp implements Sprite{

    private ArrayList<Image> imageList;
    private long totalDuration;
    private String name;


    /**
     * Constructor of a sprite
     *
     * @param name : name of the sprite
     * @param imageList : a list of images
     * @param totalDuration : the total duration of the sprite
     */
    public SpriteImp(String name, ArrayList<Image> imageList, long totalDuration){
        this.name = name;
        this.imageList = imageList;
        this.totalDuration = totalDuration;
    }


    /**
     * Return the image at the given time
     *
     * @pre millis >= 0 && millis < this.totalDuration
     * @param millis: the time at which you want the sprite image (in milliseconds)
     *
     * @return the current image
     * @post Image != null
     */
    @Override
    public Image getCurrentImage(long millis) {

        assert millis >= 0 && millis < this.totalDuration : "Precondition violated";

        // find the index of the image
        int index = (int) (this.totalDuration/millis);
        Image currentImage = this.imageList.get(index - 1);

        assert currentImage != null : "Precondition violated";

        return currentImage;
    }

    /**
     * Give the name of the sprite
     *
     * @return the name of the sprite
     * @post result != null
     */
    @Override
    public String getName() {

        String name = this.name;
        assert name != null : "Postcondition violated";

        return name;
    }
}
