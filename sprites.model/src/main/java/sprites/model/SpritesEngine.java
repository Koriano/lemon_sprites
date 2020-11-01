package sprites.model;

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
     * The movie that builds the snapshot given a specific time
     */
    private final Movie movie;

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
     * @param movie the movie that builds the snapshot given a specific time
     * @param snapshotHolder the singleObjectHolder that holds the Snapshot waiting to be displayed
     */
    public SpritesEngine(Movie movie, SingleObjectHolder<Snapshot> snapshotHolder) {
        assert movie != null && snapshotHolder != null;

        this.movie = movie;
        this.snapshotHolder = snapshotHolder;
        this.lastTrigger = 0;
    }


    /**
     * Retrieve the current snapshot from the movie, then stores it in the snapshotHolder waiting to be displayed
     * @pre this.movie != null && this.snaphotHolder != null
     */
    @Override
    public void update() {
        assert this.movie != null && this.snapshotHolder != null;

        this.snapshotHolder.setObject(this.movie.getCurrentSnapshot(this.lastTrigger));

    }

    /**
     * The task triggered by the scheduler
     *
     * @param timeOftrigger the time of the trigger
     */
    @Override
    public void trigger(long timeOftrigger) {
        this.lastTrigger = timeOftrigger;
        this.update();
    }
}
