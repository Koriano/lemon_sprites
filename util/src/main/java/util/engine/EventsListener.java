package util.engine;

/**
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 *
 * An interface that defines an event listener, which listens to an {@link EventsSource} to trigger an action.
 */
public interface EventsListener {

    /**
     * The action performed when an event is triggered
     *
     * @param evt: the event published
     *
     * @pre evt != null && "up".equals(evt.getDirection()) || "down".equals(evt.getDirection()) || "left".equals(evt.getDirection()) || "right".equals(evt.getDirection())
     */
    public void actionPerformed(Event evt);

}
