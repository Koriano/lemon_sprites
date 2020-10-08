package util.sync;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 *
 * An interface to provide a task to the scheduler
 */
public interface SchedulerListener extends ActionListener {

    /**
     * The task triggered by the scheduler
     * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
     *
     * @param e: the event that triggered the listener
     */
    @Override
    public void actionPerformed(ActionEvent e);
}
