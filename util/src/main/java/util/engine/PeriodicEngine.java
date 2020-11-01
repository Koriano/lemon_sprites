package util.engine;

import util.sync.Scheduler;
import util.sync.SchedulerListener;

/**
 * @author Mathis RACINNE-DIVET
 *
 * Defines an Engine that holds a Scheduler which will trigger the engine periodically.
 */
public abstract class PeriodicEngine implements Engine, SchedulerListener {

    /**
     * The scheduler that will trigger the engine periodically.
     */
    private Scheduler scheduler = null;

    /**
     * Starts the engine
     */
    public void start() {
        if (this.scheduler != null) {
            this.scheduler.start();
        }
    }

    /**
     * Stops the engine
     */
    public void stop() {
        if (this.scheduler != null) {
            this.scheduler.stop();
        }
    };


    /**
     * Defines the scheduler that controls the engine
     * @param scheduler the scheduler that controls the engine
     * @pre scheduler != null
     */
    public void setListener(Scheduler scheduler) {
        assert scheduler != null;
        this.scheduler = scheduler;
    }
}
