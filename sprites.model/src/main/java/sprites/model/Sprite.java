package sprites.model;

import gui.graphic.Image;

/**
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 *
 * A Sprite is described by a list of images depending on the time
 *
 * @inv this.list.size() > 0 && this.totalDuration >= 0 && this.name != null
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
     * Return the name of the sprite
     *
     * @post result != null
     *
     * @return the name of the Sprite
     */
    public String getName();
}
