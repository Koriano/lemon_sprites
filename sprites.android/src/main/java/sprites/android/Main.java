package sprites.android;

import android.ImageLoaderImp;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import gui.graphic.GraphicEngine;
import gui.graphic.Image;
import gui.graphic.Snapshot;
import sprites.model.Movie;
import sprites.model.Scene;
import sprites.model.SingleObjectHolderImp;
import sprites.model.SpritesEngine;
import sprites.model.json.MovieJsonConverter;
import sprites.model.json.SceneJsonConverter;
import util.io.ZipLoaderImp;
import util.sync.SchedulerListener;
import util.sync.SchedulerUtil;
import android.GraphicImp;
import android.view.SurfaceView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 *
 * The main class for the android version of LemonSprites
 */
public class Main extends AppCompatActivity {

    /**
     * The static instance of the window
     */
    private static GraphicImp graphic;

    /**
     * The sprites periodic engine
     */
    private static SpritesEngine spritesEngine = null;

    /**
     * The graphical periodic engine
     */
    private static GraphicEngine graphicEngine = null;

    /**
     * Refresh rate of the scheduler in milliseconds
     */
    private final static long delay = 17;//17 ms for 60 frames/s~

    /**
     * Total duration of the schedulers in milliseconds
     */
    private final static long totalDuration = 150000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Preparing ZipLoader
        HashMap<String, Image> images = new HashMap<>();
        MovieJsonConverter jsonConverter = new MovieJsonConverter(images);
        ImageLoaderImp imgLoader = new ImageLoaderImp();
        ZipLoaderImp<Movie, Image> zipLoad = new ZipLoaderImp<>(jsonConverter, imgLoader, images);


        // Loading zip and creating scene
        Movie movie = zipLoad.load(this.getResources().openRawResource(R.raw.movie));

        setContentView(R.layout.activity_main);

        // If movie successfully loaded, create engines and graphic
        if (movie != null) {
            // If the spriteEngine is already started
            if (spritesEngine != null) {
                // Stop the scheduler
                spritesEngine.stop();
            }
            // If the graphicEngine is already started
            if (graphicEngine != null) {
                // Stop the scheduler
                graphicEngine.stop();
            }

            // Displaying snapshot
            graphic = new GraphicImp(((SurfaceView)this.findViewById(R.id.surfaceView)).getHolder());

            SingleObjectHolderImp<Snapshot> snapshotHolder = new SingleObjectHolderImp<>();

            // Sprites engine
            spritesEngine = new SpritesEngine(movie, snapshotHolder);
            SchedulerUtil spritesScheduler = new SchedulerUtil(spritesEngine, delay, totalDuration);
            spritesEngine.setScheduler(spritesScheduler);

            // Graphical engine
            graphicEngine = new GraphicEngine(snapshotHolder, graphic);
            SchedulerUtil graphicalScheduler = new SchedulerUtil(graphicEngine, delay, totalDuration);
            graphicEngine.setScheduler(graphicalScheduler);

            // Start the movie by launching the engines
            spritesEngine.start();
            graphicEngine.start();

        }

    }
}
