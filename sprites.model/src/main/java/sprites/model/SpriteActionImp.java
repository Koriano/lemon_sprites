package sprites.model;

public class SpriteActionImp implements SpriteAction{


    int startX;

    int startY;

    int endX;

    int endY;

    long startTime;

    long endTime;

    String sprite;

    boolean isVisible;

    /**
     * The constructor of the class
     *
     * @param sprite the name of the sprite related to the action
     * @param time the start time of the action
     * @param endTime the end time of the action
     * @param endX the end X coordinate of the action
     * @param endY the end Y coordinate of the action
     * @param isVisible the visibility of the action
     */
    public SpriteActionImp(String sprite, long time, long endTime, int endX, int endY, boolean isVisible) {
        this.endX = endX;
        this.endY = endY;
        this.startTime = time;
        this.endTime = endTime;
        this.sprite = sprite;
        this.isVisible = isVisible;
    }


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
    @Override
    public Sprite updateSprite(Sprite sprite, long millis) {
        return null;
    }


    /**
     * Returns the name of the associated sprite
     *
     * @return the name of the associated sprite
     */
    @Override
    public String getSprite() {
        return this.sprite;
    }


    /**
     * Returns the starting time of the action
     *
     * @return the starting time of the action
     */
    @Override
    public long getStartTime() {
        return this.startTime;
    }


    /**
     * Returns the ending time of the action
     *
     * @return the ending time of the action
     */
    @Override
    public long getEndTime() {
        return this.endTime;
    }


    /**
     * Returns the start coordinate on the x axis
     *
     * @return the start coordinate on the x axis
     */
    @Override
    public int getStartX() {
        return this.startX;
    }


    /**
     * Returns the start coordinate on the y axis
     *
     * @return the start coordinate on the y axis
     */
    @Override
    public int getStartY() {
        return this.startY;
    }


    /**
     * Returns the end coordinate on the x axis
     *
     * @return the end coordinate on the x axis
     */
    @Override
    public int getEndX() {
        return this.endX;
    }


    /**
     * Returns the end coordinate on the y axis
     *
     * @return the end coordinate on the y axis
     */
    @Override
    public int getEndY() {
        return this.endY;
    }


    /**
     * Returns the visibility of the sprite
     *
     * @return the visibility of the sprite
     */
    @Override
    public boolean getVisible() {
        return this.isVisible;
    }


    /**
     * Set the start and end coordinate of the sprite
     *
     * @return the new coordinate
     */
    @Override
    public void setCoordinate(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }


    /**
     * Set the end time of the sprite action
     *
     * @return the new end time
     */
    @Override
    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }


    /**
     * Set the visibility of the sprite
     *
     * @return the visibility of the sprite
     */
    @Override
    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
}
