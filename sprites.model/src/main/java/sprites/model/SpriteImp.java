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

    /**
     * The array containing the images composing the sprite
     */
    private ArrayList<Image> imageList;

    /**
     * The total duration of the sprite
     */
    private long totalDuration;

    /**
     * The name of the sprite
     */
    private String name;

    /**
     * The visibility of the sprite
     */
    private boolean isVisible;

    /**
     * The X position of the sprite
     */
    private int x;

    /**
     * The Y position of the sprite
     */
    private int y;


    /**
     * Constructor of a sprite
     *
     * @param name : name of the sprite
     * @param x : the x coordinate of the sprite
     * @param y : the y coordinate of the sprite
     * @param isVisible : whether the sprite is displayed or not
     * @param imageList : a list of images
     * @param totalDuration : the total duration of the sprite
     * @pre name != null && x >= 0 && y >= 0 && imageList != null && imageList.size() > 0 && totalDuration >= 0
     */
    public SpriteImp(String name, int x, int y, boolean isVisible, ArrayList<Image> imageList, long totalDuration){
        assert name != null && x >= 0 && y >= 0 && imageList != null && imageList.size() > 0 && totalDuration >= 0;
        this.name = name;
        this.x = x;
        this.y = y;
        this.isVisible = isVisible;
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
        int index = (int) ((millis%this.totalDuration)*this.imageList.size()/this.totalDuration);
        Image currentImage = this.imageList.get(index);

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
    public boolean isVisible() {
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
    public ArrayList<Image> getImages() {

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
