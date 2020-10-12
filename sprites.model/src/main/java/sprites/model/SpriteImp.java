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
    private boolean isVisible;
    private int x;
    private int y;


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


    /**
     * Get the visibility of the sprite
     *
     * @return the visibility of the sprite
     */
    @Override
    public boolean getVisible() {
        return this.isVisible;
    }


    /**
     * Get the X position of the sprite
     *
     * @post result >= 0
     *
     * @return x position of the sprite
     */
    @Override
    public int getX() {

        int x = this.x;

        assert x >= 0 : "Postcondition violated";
        return x;
    }


    /**
     * Get the Y position of the sprite
     *
     * @post result >= 0
     *
     * @return y position of the sprite
     */
    @Override
    public int getY() {
        int y = this.y;

        assert y >= 0 : "Postcondition violated";
        return y;
    }


    /**
     * Get the list of the sprite images
     *
     * @post result != null
     *
     * @return list of the sprite images
     */
    @Override
    public ArrayList<Image> getImage() {

        ArrayList<Image> imageList = this.imageList;

        assert imageList != null : "Postcondition violated";
        return imageList;
    }


    /**
     * Get the total duration of the sprite
     *
     * @post result >= 0
     *
     * @return total duration of the sprite
     */
    @Override
    public long getDuration() {
        long totalDuration = this.totalDuration;

        assert totalDuration >= 0 : "Postcondition violated";
        return totalDuration;
    }
}
