package util.engine;

/**
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 *
 * An interface that defines an event, send by an {@link EventsSource} and obtained by {@link EventsListener}.
 */
public interface Event {

    /**
     * A method to set the direction pushed
     *
     * @param direction: the direction pushed
     *
     * @pre "up".equals(direction) || "down".equals(direction) || "left".equals(direction) || "right".equals(direction)
     */
    public void setDirection(String direction);

    /**
     * A method to get the direction pushed
     *
     * @return the direction pushed
     */
    public String getDirection();
}
