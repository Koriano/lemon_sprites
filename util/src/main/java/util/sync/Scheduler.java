package util.sync;

/**
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 *
 * An interface that triggers a task at a given rate
 *
 * @inv this.totalDuration >= 0 && ths.delay >= 0 && this.listener != null
 */
public interface Scheduler extends Runnable{

    /**
     * Run the thread and starts the timer
     * @see Runnable#run()
     */
    @Override
    public void run();

    /**
     * Used to start the timer
     */
    public void start();

    /**
     * Used to stop the thread ans the timer
     */
    public void stop();

    /**
     * Returns the delay between 2 triggers
     *
     * @post result >= 0
     *
     * @return the delay in ms
     */
    public long getDelay();

    /**
     * Returns the total duration
     *
     * @post result >= 0
     *
     * @return the total duration in ms
     */
    public long getTotalDuration();
}
