package util.sync;

/**
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 *
 * An interface to provide a task to the scheduler
 */
public interface SchedulerListener {

    /**
     * The task triggered by the scheduler
     */
    public void trigger(long timeOftrigger);
}
