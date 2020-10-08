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
     * @return the current image
     */
    @Override
    public Image getCurrentImage(long millis) {

        // find the index of the image
        int index = (int) (millis/this.totalDuration);

        return this.imageList.get(index - 1);
    }

    /**
     * Give the name of the sprite
     *
     * @return the name of the sprite
     * @post result != null
     */
    @Override
    public String getName() {
        return this.name;
    }
}
