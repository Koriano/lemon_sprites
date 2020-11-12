package sprites.model;

public class DirectionAction implements SpriteAction{

    /**
     * The direction of movement, null if idle.
     */
    private String direction;

    /**
     * The start time of the action
     */
    private long startTime;

    /**
     * The speed of the sprite, in pixels.ms
     */
    private float speed;


    /**
     * Constructor of the DirectionAction
     * @param direction the action direction
     * @param startTime the time of beginning of the action
     * @param speed the movement speed
     */
    public DirectionAction(String direction, long startTime, float speed) {
        assert direction == null || "up".equals(direction) || "down".equals(direction) || "left".equals(direction) || "right".equals(direction) || "downleft".equals(direction) || "downright".equals(direction) || "leftup".equals(direction) || "rightup".equals(direction): "oui: " + direction;
        assert speed >= 0;

        this.direction = direction;
        this.startTime = startTime;
        this.speed = speed;
    }


    /**
     * To update a sprite position depending on the time passed
     *
     * @param sprite : the sprite to be updated
     * @param millis : the current time to get position
     * @return the sprite updated
     * @pre sprite != null && millis >= 0 && millis >= this.time && millis <= this.endTime
     */
    @Override
    public Sprite updateSprite(Sprite sprite, long millis) {
        assert sprite != null && millis >= 0 && millis >= this.startTime: "Precondition violated";

        if (this.direction != null) {
            // Compute the distance to travel
            int step = (int)(this.speed * (float)(millis - this.startTime));
            this.startTime = millis;

            switch (this.direction) {
                case "up" :
                    sprite.setY(sprite.getY()-step);
                    break;
                case "down" :
                    sprite.setY(sprite.getY()+step);
                    break;
                case "left" :
                    sprite.setX(sprite.getX()-step);
                    break;
                case "right" :
                    sprite.setX(sprite.getX()+step);
                    break;
                case "downleft" :
                    sprite.setY(sprite.getY()+step);
                    sprite.setX(sprite.getX()-step);
                    break;
                case "downright" :
                    sprite.setY(sprite.getY()+step);
                    sprite.setX(sprite.getX()+step);
                    break;
                case "leftup" :
                    sprite.setY(sprite.getY()-step);
                    sprite.setX(sprite.getX()-step);
                    break;
                case "rightup" :
                    sprite.setY(sprite.getY()-step);
                    sprite.setX(sprite.getX()+step);
                    break;
            }
        }

        // return the modified sprite (or the same if direction was null).
        return sprite;
    }

    /**
     * Returns the name of the associated sprite
     *
     * @return the name of the associated sprite
     */
    @Override
    public String getSprite() {
        return null;
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
        return 0;
    }

    /**
     * Returns the start coordinate on the x axis
     *
     * @return the start coordinate on the x axis
     */
    @Override
    public int getStartX() {
        return 0;
    }

    /**
     * Returns the start coordinate on the y axis
     *
     * @return the start coordinate on the y axis
     */
    @Override
    public int getStartY() {
        return 0;
    }

    /**
     * Returns the end coordinate on the x axis
     *
     * @return the end coordinate on the x axis
     */
    @Override
    public int getEndX() {
        return 0;
    }

    /**
     * Returns the end coordinate on the y axis
     *
     * @return the end coordinate on the y axis
     */
    @Override
    public int getEndY() {
        return 0;
    }

    /**
     * Returns the visibility of the sprite
     *
     * @return the visibility of the sprite
     */
    @Override
    public boolean isVisible() {
        return false;
    }

    /**
     * Returns the state of achievement of the action, i.e. if the action has been consumed
     *
     * @return the state of achievement of the action, i.e. if the action has been consumed
     */
    @Override
    public boolean isConsumed() {
        return false;
    }

    /**
     * Set the start and end coordinate of the sprite
     *
     * @param startX : start X coordinate
     * @param startY : start Y coordinate
     * @param endX   : end X coordinate
     * @param endY   : end Y coordinate
     */
    @Override
    public void setCoordinate(int startX, int startY, int endX, int endY) {}

    /**
     * Set the end time of the sprite action
     *
     * @param endTime
     * @return the new end time
     */
    @Override
    public void setEndTime(long endTime) {}

    /**
     * Set the visibility of the sprite
     *
     * @param isVisible
     * @return the visibility of the sprite
     */
    @Override
    public void setIsVisible(boolean isVisible) {}

    /**
     * Sets the state of achievement of the action, i.e. if the action has been consumed
     *
     * @param isConsumed
     * @return the state of achievement of the action, i.e. if the action has been consumed
     */
    @Override
    public void setConsumed(boolean isConsumed) {}

    /**
     * Return the direction of the action
     * @return the direction of the action
     */
    public String getDirection(){
        return this.direction;
    }
}
