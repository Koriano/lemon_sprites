package swing.sync;
import util.sync.Scheduler;
import util.sync.SchedulerListener;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Mathis RACINNE-DIVET
 *
 * A class that triggers a task at a given rate, on the graphical thread
 *
 * @inv this.totalDuration >= 0 && this.delay >= 0 && this.listener != null
 */
public class SchedulerSwing implements Scheduler, ActionListener {

    /**
     * The graphical timer that will trigger event at a given rate
     */
    private Timer timer;

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
     * Constructor of the graphical scheduler
     * @param listener listener that will be triggered by the timer
     * @param delay the delay between two time triggers
     * @param totalDuration the total duration of the timer
     * @pre delay >= 0 && totalDuration >= 0 && listener != null
     */
    public SchedulerSwing(SchedulerListener listener, long delay, long totalDuration){
        assert delay >= 0 && totalDuration >= 0 && listener != null;
        this.listener = listener;
        this.delay = delay;
        this.totalDuration = totalDuration;
        this.timer = new Timer((int)this.delay, this);
    }

    /**
     * Used to start the timer
     */
    @Override
    public void start() {
        this.timer.start();
    }

    /**
     * Used to stop the timer
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

    /**
     * Invoked by the timer every delay ms.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.listener.trigger(System.currentTimeMillis());
    }
}
