package sprites.model;


/**
 * @author Margaux SCHNELZAUER
 *
 * A class to implement SpriteAction Interface
 *
 * @see sprites.model.SpriteAction
 */
public class SpriteActionImp implements SpriteAction{

    /**
     * The X coordinate for the start of the action
     */
    int startX;

    /**
     * The Y coordinate for the start of the action
     */
    int startY;

    /**
     * The X coordinate for the end of the action
     */
    int endX;

    /**
     * The Y coordinate for the end of the action
     */
    int endY;

    /**
     * The start time of the action
     */
    long startTime;

    /**
     * The end time of the action
     */
    long endTime;

    /**
     * The name of the sprite
     */
    String sprite;

    /**
     * The visibility of the action
     */
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

        assert sprite != null && millis >= 0 && millis >= this.startTime && millis <= this.endTime : "Pre condition violated";

        // interval calculation
        int deltaX = this.endX - this.startX;
        int deltaY = this.endY - this.startY;
        long deltaT = this.endTime - this.startTime;

        // new coordinate calculation
        int newX = (int) ((((long) deltaX / deltaT) * millis) + this.startX);
        int newY = (int) ((((long) deltaY / deltaT) * millis) + this.startY);

        // return a new sprite with the new X and Y coordinate
        return new SpriteImp(sprite.getName(), newX, newY, sprite.isVisible(), sprite.getImages(), sprite.getDuration());
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
    public boolean isVisible() {
        return this.isVisible;
    }


    /**
     * Set the start and end coordinate of the sprite
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
     */
    @Override
    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }


    /**
     * Set the visibility of the sprite
     */
    @Override
    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
}
