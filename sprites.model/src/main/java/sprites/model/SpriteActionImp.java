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
     * Whether the action has been consumed
     */
    boolean isConsumed;


    /**
     * The constructor of the class
     *
     * @param sprite the name of the sprite related to the action
     * @param time the start time of the action
     * @param endTime the end time of the action
     * @param endX the end X coordinate of the action
     * @param endY the end Y coordinate of the action
     * @param isVisible the visibility of the action
     *
     * @pre sprite != null && !"".equals(sprite) && time >= 0 && endTime >= 0
     */
    public SpriteActionImp(String sprite, long time, long endTime, int endX, int endY, boolean isVisible) {
        assert sprite != null && !"".equals(sprite) && time >= 0 && endTime >= 0: "Precondition violated";

        this.endX = endX;
        this.endY = endY;
        this.startTime = time;
        this.endTime = endTime;
        this.sprite = sprite;
        this.isVisible = isVisible;
        this.isConsumed = false;
    }


    /**
     * To update a sprite position depending on the time passed
     *
     * @pre sprite != null && millis >= 0 && millis >= this.startTime && millis <= this.endTime
     *
     * @param sprite: the sprite to be updated
     * @param millis: the current time to get position
     *
     * @return the sprite updated
     */
    @Override
    public Sprite updateSprite(Sprite sprite, long millis) {

        assert sprite != null && millis >= 0 && millis >= this.startTime && millis <= this.endTime: "Precondition violated";

        // interval calculation
        int deltaX = this.endX - this.startX;
        int deltaY = this.endY - this.startY;
        long deltaT = this.endTime - this.startTime;

        float dxDt = ((float)deltaX / (float)deltaT);
        float dyDt = ((float)deltaY / (float)deltaT);

        // new coordinate calculation
        int newX = (int) ((dxDt * (float)(millis - startTime)) + this.startX);
        int newY = (int) ((dyDt * (float)(millis - startTime)) + this.startY);

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
     * Returns the state of achievement of the action, i.e. if the action has been consumed
     *
     * @return the state of achievement of the action, i.e. if the action has been consumed
     */
    @Override
    public boolean isConsumed() {
        return this.isConsumed;
    }


    /**
     * Set the start and end coordinate of the sprite
     *
     * @param startX: start X coordinate
     * @param startY: start Y coordinate
     * @param endX: end X coordinate
     * @param endY: end Y coordinate
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

    /**
     * Sets the state of achievement of the action, i.e. if the action has been consumed
     *
     * @param isConsumed
     */
    @Override
    public void setConsumed(boolean isConsumed) {
        this.isConsumed = isConsumed;
    }
}
