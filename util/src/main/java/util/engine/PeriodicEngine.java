package util.engine;

import util.sync.SchedulerListener;

public abstract class PeriodicEngine implements Engine, SchedulerListener {

    /**
     * To start the engine
     */
    public abstract void start();

    /**
     * To stop the engine
     */
    public abstract void stop();
}
