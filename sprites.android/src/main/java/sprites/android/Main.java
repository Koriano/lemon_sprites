package sprites.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import util.sync.SchedulerListener;
import util.sync.SchedulerUtil;

public class Main extends AppCompatActivity implements SchedulerListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SchedulerUtil scheduler = new SchedulerUtil(this, 1000, 30000);
        scheduler.start();
    }

    /**
     * The task triggered by the scheduler
     *
     * @param timeOftrigger
     */
    @Override
    public void trigger(long timeOftrigger) {
        Log.println(Log.INFO, "TEST_TIMER", "Trigger de temps !");
    }
}
