package swing.sync;
import util.sync.SchedulerListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchedulerListenerImp implements SchedulerListener{

    public SchedulerListenerImp() {
        //TODO take the instance of the window/graphical content manager to display the new snapshots
    }

    /**
     * The task triggered by the scheduler
     *
     * @param e : the event that triggered the listener
     * @see ActionListener#actionPerformed(ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO Retrieve the new snapshot given the current time
    }
}
