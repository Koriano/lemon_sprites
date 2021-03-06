package sprites.model;

/**
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 *
 * An action is defined by a start and end time as well as the coordinates. You will compute compute the coordinates
 * at each time of the model.
 */
public interface SpriteAction {

    /**
     * To update a sprite position depending on the time passed
     *
     * @pre sprite != null && millis >= 0 && millis >= this.time && millis <= this.endTime
     *
     * @param sprite: the sprite to be updated
     * @param millis: the current time to get position
     *
     * @return the sprite updated
     */
    public Sprite updateSprite(Sprite sprite, long millis);

    /**
     * Returns the starting time of the action
     *
     * @return the starting time of the action
     */
    public int getStartTime();

    /**
     * Returns the ending time of the action
     *
     * @return the ending time of the action
     */
    public int getEndTime();

    /**
     * Returns the end coordinate on the x axis
     *
     * @return the end coordinate on the x axis
     */
    public int getEndX();

    /**
     * Returns the end coordinate on the y axis
     *
     * @return the end coordinate on the y axis
     */
    public int getEndY();

    /**
     * Returns the visibility of the sprite
     *
     * @return the visibility of the sprite
     */
    public int getVisible();
}
