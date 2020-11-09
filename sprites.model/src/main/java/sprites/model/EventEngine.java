package sprites.model;

import java.util.ArrayList;

import util.engine.Engine;
import util.events.Event;
import util.events.EventsListener;

public class EventEngine implements EventsListener, Engine {

    private ArrayList<DirectionAction> queue;
    private DirectionAction currentAction;

    public EventEngine(ArrayList<DirectionAction> queue) {
        assert queue != null;
        this.queue = queue;
        this.currentAction = null;
    }


    /**
     * The action performed when an event is triggered
     *
     * @param evt : the event published
     * @pre evt != null && "up".equals(evt.getDirection()) || "down".equals(evt.getDirection()) || "left".equals(evt.getDirection()) || "right".equals(evt.getDirection())
     */
    @Override
    public void actionPerformed(Event evt) {

        this.currentAction = new DirectionAction();
        this.update();
    }

    /**
     * To get shared information from model and update an other (or the same) model
     */
    @Override
    public void update() {
        if (this.currentAction != null) {
            this.queue.add(this.currentAction);
            this.currentAction = null;
        }
    }
}
