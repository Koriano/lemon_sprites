package sprites.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import util.engine.Engine;
import util.events.Event;
import util.events.EventsListener;

/**
 * Engine that is triggered by a DirectionEvent, send by the EventSource.
 *
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 */
public class EventEngine implements EventsListener, Engine {

    /**
     * The map that saves the state of the keys (i.e. pressed or not)
     */
    private HashMap<String, Boolean> currentEventMap;

    /**
     * The queue of the pending directions
     */
    private ArrayList<String> queue;

    /**
     * The directions that have been pressed
     */
    private ArrayList<String> currentDirections;

    /**
     * Constructor of the EventEngine class
     * @param queue the direction queue shared with the SpriteEngine
     */
    public EventEngine(ArrayList<String> queue) {
        assert queue != null;
        this.queue = queue;
        this.currentDirections = new ArrayList<>();
        this.currentEventMap = new HashMap<>();
        this.currentEventMap.put("up", false);
        this.currentEventMap.put("down", false);
        this.currentEventMap.put("left", false);
        this.currentEventMap.put("right", false);
    }


    /**
     * The action performed when an event is triggered
     *
     * @param evt : the event published
     * @pre evt != null && ("up".equals(evt.getDirection()) || "down".equals(evt.getDirection()) || "left".equals(evt.getDirection()) || "right".equals(evt.getDirection()) || "END_up".equals(evt.getDirection()) || "END_down".equals(evt.getDirection()) || "END_left".equals(evt.getDirection()) || "END_right".equals(evt.getDirection()))
     */
    @Override
    public void actionPerformed(Event evt) {
        assert evt != null && ("up".equals(evt.getDirection()) || "down".equals(evt.getDirection()) || "left".equals(evt.getDirection()) || "right".equals(evt.getDirection()) || "END_up".equals(evt.getDirection()) || "END_down".equals(evt.getDirection()) || "END_left".equals(evt.getDirection()) || "END_right".equals(evt.getDirection()));

        if ("up".equals(evt.getDirection()) || "down".equals(evt.getDirection()) || "left".equals(evt.getDirection()) || "right".equals(evt.getDirection())) {
            // If the key hasn't already been pressed
            if (!this.currentEventMap.get(evt.getDirection())) {
                // Set the key as being pressed
                this.currentEventMap.put(evt.getDirection(), true);
                // Update the current pressed keys
                this.currentDirections.add(evt.getDirection());
                this.update();
            }
        } else {
            // Reset the direction because the key has been released
            String endEvent = evt.getDirection().split("_")[1];
            this.currentEventMap.put(endEvent, false);
            this.currentDirections.remove(endEvent);
            this.update();
        }
    }

    /**
     * Filter the current pressed keys, to build or not a new direction to be passed in the queue.
     */
    @Override
    public void update() {
        ArrayList<String> actions = (ArrayList<String>) this.currentDirections.clone();

        if (this.currentDirections.size() > 0) {
            // If multiple keys are pressed at the same time
            if (this.currentDirections.size() > 1){
                // Remove incoherent movements, i.e. up and down, or left and right
                if (this.currentDirections.contains("up") && this.currentDirections.contains("down")) {
                    actions.remove("up");
                    actions.remove("down");
                }
                if (this.currentDirections.contains("right") && this.currentDirections.contains("left")) {
                    actions.remove("right");
                    actions.remove("left");
                }

                // Sort the remaining movements
                Collections.sort(actions);
                String finalDirection = "";

                // Concatenate all the directions into a string passed to the queue.
                for (String dir : actions) {
                    finalDirection += dir;
                }

                // If no direction remains, set to null
                if ("".equals(finalDirection)) {
                    finalDirection = null;
                }
                this.queue.add(finalDirection);

            } else {
                this.queue.add(this.currentDirections.get(0));
            }
        } else {
            // Check if all keys are released
            boolean areAllKeysReleased = true;
            for (Boolean value : this.currentEventMap.values()) {
                // If at least one key is pressed
                if (value) {
                    areAllKeysReleased = false;
                    break;
                }
            }
            if (areAllKeysReleased) {
                // Specify that all keys are released
                this.queue.add(null);
            }

        }
    }
}
