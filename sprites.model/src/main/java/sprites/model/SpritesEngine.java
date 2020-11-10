package sprites.model;

import java.util.ArrayList;

import gui.graphic.Snapshot;
import util.engine.PeriodicEngine;
import util.engine.SingleObjectHolder;

/**
 * @author Mathis RACINNE-DIVET
 *
 * SpritesEngine that periodically stores a Snapshot in the snapshotHolder, waiting to be displayed
 *
 * @inv movie != null && snapshotHolder != null && lastTrigger >= 0
 */
public class SpritesEngine extends PeriodicEngine {

    /**
     * The game that builds the snapshot given a specific time and player direction
     */
    private final Game game;

    /**
     * The queue containing all the pending user movements
     */
    private ArrayList<String> directionsQueue;

    /**
     * The singleObjectHolder that holds the Snapshot waiting to be displayed
     */
    private final SingleObjectHolder<Snapshot> snapshotHolder;

    /**
     * The last time of the engine's trigger
     */
    private long lastTrigger;


    /**
     * Constructor of the SpritesEngine
     * @param game the game that builds the snapshot given a specific time
     * @param snapshotHolder the singleObjectHolder that holds the Snapshot waiting to be displayed
     * @param directionsQueue the queue containing all the pending user movements
     * @pre game != null && snapshotHolder != null && directionsQueue != null
     */
    public SpritesEngine(Game game, SingleObjectHolder<Snapshot> snapshotHolder, ArrayList<String> directionsQueue) {
        assert game != null && snapshotHolder != null && directionsQueue != null;

        this.game = game;
        this.snapshotHolder = snapshotHolder;
        this.directionsQueue = directionsQueue;
        this.lastTrigger = 0;
    }


    /**
     * Retrieve the current snapshot from the movie, then stores it in the snapshotHolder waiting to be displayed
     * @pre this.movie != null && this.snaphotHolder != null
     */
    @Override
    public void update() {
        assert this.game != null && this.snapshotHolder != null;

        Snapshot snapshot = this.game.getCurrentSnapshot(this.lastTrigger, this.directionsQueue.remove(0));
        if (snapshot !=  null) {
            this.snapshotHolder.setObject(snapshot);

        } else {
            this.stop();
        }


    }

    /**
     * The task triggered by the scheduler
     *
     * @param timeOftrigger the time of the trigger
     */
    @Override
    public void trigger(long timeOftrigger) {
        this.lastTrigger = timeOftrigger - this.getStartTime();
        this.update();
    }
}
