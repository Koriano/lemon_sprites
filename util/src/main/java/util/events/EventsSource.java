package util.events;

import util.events.Event;
import util.events.EventsListener;

/**
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 *
 * An interface that defines an event source, which sends {@link Event} objects.
 */
public interface EventsSource {

    /**
     * To fire an event to every register
     */
    public void notifyListeners();

    /**
     * To register a new listener to the subscriber list
     *
     * @param listener: the new listener
     *
     * @pre listener != null
     */
    public void register(util.events.EventsListener listener);

    /**
     * To unregister a listener from the subscriber list
     *
     * @param listener: the listener to unsubscribe
     *
     * @pre listener != null
     */
    public void unregister(EventsListener listener);
}
