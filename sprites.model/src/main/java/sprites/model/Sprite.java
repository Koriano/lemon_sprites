package sprites.model;

import gui.graphic.Image;
import java.util.ArrayList;


/**
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 *
 * A Sprite is described by a list of images depending on the time
 *
 * @inv this.list.size() > 0 && this.totalDuration >= 0 && this.name != null && this.x >=0 && this.y >= 0 && this.imageList != null
 */

public interface Sprite {

    /**
     * Method to get the image at the time t=millis
     *
     * @pre millis >= 0 && millis < this.totalDuration
     * @post Image != null
     *
     * @param millis: the time at which you want the sprite image (in milliseconds)
     * @return the Image corresponding at the time millis
     */
    public Image getCurrentImage(long millis);



    /**
     * Get the name of the sprite
     *
     * @post result != null
     *
     * @return the name of the Sprite
     */
    public String getName();



    /**
     * Get the visibility of the sprite
     *
     * @return the visibility of the sprite
     */
    public boolean isVisible();



    /**
     * Get the X position of the sprites
     *
     * @post result >=0
     *
     * @return the x position of the sprites
     */
    public int getX();



    /**
     * Get the Y position of the sprites
     *
     * @post result >= 0
     *
     * @return the y position of the sprites
     */
    public int getY();


    
    /**
     * Get the list of the sprites Images
     *
     * @post result != null
     *
     * @return the list of the sprites Images
     */
    public ArrayList<Image> getImages();


    /**
     * Get the sprite duration
     *
     * @post result >= 0
     *
     * @return the sprite duration
     */
    public long getDuration();
}
