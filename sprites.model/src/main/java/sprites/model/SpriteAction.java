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
     * Returns the name of the associated sprite
     *
     * @return the name of the associated sprite
     */
    public String getSprite();

    /**
     * Returns the starting time of the action
     *
     * @return the starting time of the action
     */
    public long getStartTime();

    /**
     * Returns the ending time of the action
     *
     * @return the ending time of the action
     */
    public long getEndTime();

    /**
     * Returns the start coordinate on the x axis
     *
     * @return the start coordinate on the x axis
     */
    public int getStartX();

    /**
     * Returns the start coordinate on the y axis
     *
     * @return the start coordinate on the y axis
     */
    public int getStartY();


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
    public boolean isVisible();


    /**
     * Returns the state of achievement of the action, i.e. if the action has been consumed
     * @return the state of achievement of the action, i.e. if the action has been consumed
     */
    public boolean isConsumed();


    /**
     * Set the start and end coordinate of the sprite
     *
     * @return the new coordinate
     */
    public void setCoordinate(int startX, int startY, int endX, int endY);


    /**
     * Set the end time of the sprite action
     *
     * @return the new end time
     */
    public void setEndTime(long endTime);


    /**
     * Set the visibility of the sprite
     *
     * @return the visibility of the sprite
     */
    public void setIsVisible(boolean isVisible);


    /**
     * Sets the state of achievement of the action, i.e. if the action has been consumed
     * @return the state of achievement of the action, i.e. if the action has been consumed
     */
    public void setConsumed(boolean isConsumed);

}
