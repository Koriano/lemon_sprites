package util.engine;

import util.sync.Scheduler;
import util.sync.SchedulerListener;

/**
 * @author Mathis RACINNE-DIVET
 *
 * Defines an Engine that holds a Scheduler which will trigger the engine periodically.
 *
 * @see util.engine.Engine
 * @see util.sync.SchedulerListener
 */
public abstract class PeriodicEngine implements Engine, SchedulerListener {

    /**
     * The scheduler that will trigger the engine periodically.
     */
    private Scheduler scheduler = null;

    /**
     * The time of the engine start
     */
    private long startTime;

    /**
     * Starts the engine
     */
    public void start() {
        if (this.scheduler != null) {
            this.scheduler.start();
        }

        this.startTime = System.currentTimeMillis();
    }

    /**
     * Stops the engine
     */
    public void stop() {
        if (this.scheduler != null) {
            this.scheduler.stop();
        }
    }

    /**
     * Returns the time of the engine start
     * @return the time of the engine start
     */
    public long getStartTime() {
        return this.startTime;
    }

    /**
     * Defines the scheduler that controls the engine
     * @param scheduler the scheduler that controls the engine
     * @pre scheduler != null
     */
    public void setScheduler(Scheduler scheduler) {
        assert scheduler != null;
        this.scheduler = scheduler;
    }
}
