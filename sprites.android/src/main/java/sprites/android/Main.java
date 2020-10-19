package sprites.android;

import android.ImageLoaderImp;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import gui.graphic.Image;
import gui.graphic.Snapshot;
import sprites.model.Scene;
import sprites.model.json.SceneJsonConverter;
import util.io.ZipLoaderImp;
import util.sync.SchedulerListener;
import util.sync.SchedulerUtil;
import android.GraphicImp;
import android.view.SurfaceView;

import java.util.HashMap;

/**
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 *
 * The main class for the android version of LemonSprites
 */
public class Main extends AppCompatActivity implements SchedulerListener {

    /**
     * The static instance of the window
     */
    private static GraphicImp graphic;

    /**
     * The static instance of the currently displayed scene
     */
    private static Scene scene;

    /**
     * The system time at the beginning of the animation
     */
    private static long startTime;

    /**
     * Refresh rate of the scheduler in milliseconds
     */
    private final static long delay = 100;

    /**
     * Total duration of the scene in milliseconds
     */
    private final static long totalDuration = 15000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Preparing ZipLoader
        HashMap<String, Image> images = new HashMap<>();
        SceneJsonConverter jsonConverter = new SceneJsonConverter(images);
        ImageLoaderImp imgLoader = new ImageLoaderImp();
        ZipLoaderImp<Scene, Image> zipLoad = new ZipLoaderImp<>(jsonConverter, imgLoader, images);

        // Loading zip and creating scene
        scene = zipLoad.load(this.getResources().openRawResource(R.raw.scene));

        setContentView(R.layout.activity_main);

        // If scene successfully loaded, create window and scheduler -> starts the graphical application
        if (scene != null) {
            Snapshot snapshot = scene.getCurrentSnapshot(0);
            startTime = System.currentTimeMillis();

            // Displaying snapshot
            graphic = new GraphicImp(((SurfaceView)this.findViewById(R.id.surfaceView)).getHolder());
            graphic.displaySnapshot(snapshot);

            SchedulerUtil scheduler = new SchedulerUtil(this, delay, totalDuration);
            scheduler.start();

        }

    }


    /**
     * The task triggered by the scheduler
     *
     * @param timeOftrigger
     */
    @Override
    public void trigger(long timeOftrigger) {
        Snapshot snapshot = scene.getCurrentSnapshot(timeOftrigger - startTime);
        graphic.displaySnapshot(snapshot);
    }
}
