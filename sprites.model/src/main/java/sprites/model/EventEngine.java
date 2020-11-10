package sprites.model;

import java.util.ArrayList;

import util.engine.Engine;
import util.events.Event;
import util.events.EventsListener;

public class EventEngine implements EventsListener, Engine {

    private ArrayList<String> queue;
    private String currentDirection;

    public EventEngine(ArrayList<String> queue) {
        assert queue != null;
        this.queue = queue;
        this.currentDirection = null;
    }


    /**
     * The action performed when an event is triggered
     *
     * @param evt : the event published
     * @pre evt != null && ("up".equals(evt.getDirection()) || "down".equals(evt.getDirection()) || "left".equals(evt.getDirection()) || "right".equals(evt.getDirection()))
     */
    @Override
    public void actionPerformed(Event evt) {
        assert evt != null && ("up".equals(evt.getDirection()) || "down".equals(evt.getDirection()) || "left".equals(evt.getDirection()) || "right".equals(evt.getDirection()));
        this.currentDirection = evt.getDirection();
        this.update();
    }

    /**
     * To get shared information from model and update an other (or the same) model
     */
    @Override
    public void update() {
        if (this.currentDirection != null) {
            this.queue.add(this.currentDirection);
            this.currentDirection = null;
        }
    }
}
