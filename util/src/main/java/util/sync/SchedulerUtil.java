package util.sync;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Mathis RACINNE-DIVET
 *
 * A class that triggers a task at a given rate, on the main thread
 *
 * @inv this.totalDuration >= 0 && this.delay >= 0 && this.listener != null
 */
public class SchedulerUtil implements Scheduler {

    /**
     * The timer that will trigger event at a given rate
     */
    private Timer timer;

    /**
     * The timer task that will be called by the timer at a given rate
     */
    private TimerTask timerTask;

    /**
     * The total duration of the timer
     */
    private long totalDuration;

    /**
     * The delay between two time triggers
     */
    private long delay;

    /**
     * Constructor of the main thread timer
     * @param listener listener that will be triggered by the timer
     * @param delay the delay between two time triggers
     * @param totalDuration the total duration of the timer
     * @pre delay >= 0 && totalDuration >= 0 && listener != null
     */
    public SchedulerUtil(final SchedulerListener listener, long delay, long totalDuration){
        assert delay >= 0 && totalDuration >= 0 && listener != null;
        this.delay = delay;
        this.totalDuration = totalDuration;
        this.timer = new Timer();
        this.timerTask = new TimerTask() {
            @Override
            public void run() {
                listener.trigger(this.scheduledExecutionTime());
            }
        };
    }

    /**
     * Used to start the timer
     */
    @Override
    public void start() {
        this.timer.scheduleAtFixedRate(this.timerTask, 0, delay);
    }

    /**
     * Used to stop the timer
     */
    @Override
    public void stop() {
        this.timer.cancel();
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
