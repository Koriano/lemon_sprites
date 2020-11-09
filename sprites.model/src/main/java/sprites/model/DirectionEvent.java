package sprites.model;

import util.events.Event;

public class DirectionEvent implements Event {


    private String direction;


    public DirectionEvent(String direction) {
        assert !(direction != null && !("up".equals(direction) || "down".equals(direction) || "left".equals(direction) || "right".equals(direction))): "Assert violation: direction is not null but is neither \"up\", \"down\", \"left\" nor \"right\"";
        this.direction = direction;
    }


    /**
     * A method to set the direction pushed
     *
     * @param direction : the direction pushed
     * @pre direction == null || "up".equals(direction) || "down".equals(direction) || "left".equals(direction) || "right".equals(direction)
     */
    @Override
    public void setDirection(String direction) {
        assert direction == null || "up".equals(direction) || "down".equals(direction) || "left".equals(direction) || "right".equals(direction): "Assert violation: direction should be either null,\"up\", \"down\", \"left\" or \"right\"";
        this.direction = direction;
    }

    /**
     * A method to get the direction pushed
     *
     * @return the direction pushed
     */
    @Override
    public String getDirection() {
        return this.direction;
    }
}
