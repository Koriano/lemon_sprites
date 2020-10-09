package swing.sync;
import util.sync.Scheduler;
import util.sync.SchedulerListener;

import javax.swing.*;

/**
 * @author Mathis RACINNE-DIVET
 *
 * An interface that triggers a task at a given rate
 *
 * @inv this.totalDuration >= 0 && this.delay >= 0 && this.listener != null
 */
public class SchedulerImp implements Scheduler{

    /**
     * The total duration of the timer
     */
    private long totalDuration;

    /**
     * The delay between two time triggers
     */
    private long delay;

    /**
     * The listener that will be triggered by the timer
     */
    private SchedulerListener listener;

    /**
     * The graphical timer
     */
    private Timer timer;

    /**
     * Constructor of the graphical scheduler
     * @param delay the delay between two time triggers
     * @param totalDuration the total duration of the timer
     */
    public SchedulerImp(long delay, long totalDuration){
        assert delay >= 0 && totalDuration >= 0;

        this.delay = delay;
        this.totalDuration = totalDuration;
        this.listener = new SchedulerListenerImp();//TODO Pass in param the instance of the window to display the new snapshot
        this.timer = new Timer((int)this.delay, this.listener);
    }

    /**
     * Run the thread and starts the timer
     *
     * @see Runnable#run()
     */
    @Override
    public void run() {
        this.start();
    }

    /**
     * Used to start the timer
     */
    @Override
    public void start() {
        this.timer.start();
    }

    /**
     * Used to stop the thread ans the timer
     */
    @Override
    public void stop() {
        this.timer.stop();
    }

    /**
     * Returns the delay between 2 triggers
     *
     * @return the delay in ms
     * @post result >= 0
     */
    @Override
    public long getDelay() {
        return this.delay;
    }

    /**
     * Returns the total duration
     *
     * @return the total duration in ms
     * @post result >= 0
     */
    @Override
    public long getTotalDuration() {
        return this.totalDuration;
    }
}
