package gui.graphic;

import util.engine.PeriodicEngine;
import util.engine.SingleObjectHolder;

/**
 * @author Mathis RACINNE-DIVET
 *
 * GraphicEngine that periodically get the last snapshot (if any) of the SingleObjectHolder, to pass it to the view so that it is displayed
 *
 * @inv snapshotHolder != null && view != null
 */
public class GraphicEngine extends PeriodicEngine {

    /**
     * The singleObjectHolder that holds the Snapshot waiting to be displayed
     */
    private final SingleObjectHolder<Snapshot> snapshotHolder;

    /**
     * The view that displays the snapshot
     */
    private final Graphic view;

    /**
     * The constructor of the GraphicEngine
     * @param snapshotHolder the singleObjectHolder that holds the Snapshot waiting to be displayed
     * @param view the view that displays the snapshot
     */
    public GraphicEngine(SingleObjectHolder<Snapshot> snapshotHolder, Graphic view) {
        assert snapshotHolder != null && view != null;

        this.snapshotHolder = snapshotHolder;
        this.view = view;
    }

    /**
     * Retrieve the current snapshot from the singleObjectHolder, to give it to the view so as to be displayed
     * @pre this.view != null && this.snapshotHolder != null
     */
    @Override
    public void update() {
        assert this.view != null && this.snapshotHolder != null;
        Snapshot snapshot = this.snapshotHolder.getObject();

        if (snapshot != null) {
            this.view.displaySnapshot(snapshot);
        }
    }

    /**
     * The task triggered by the scheduler
     * @param timeOftrigger the time of the trigger
     */
    @Override
    public void trigger(long timeOftrigger) {
        this.update();
    }
}
