package sprites.android;

import android.ImageLoaderImp;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import gui.graphic.GraphicEngine;
import gui.graphic.Image;
import gui.graphic.Snapshot;
import sprites.model.DirectionEvent;
import sprites.model.EventEngine;
import sprites.model.Game;
import sprites.model.Sequence;
import sprites.model.SingleObjectHolderImp;
import sprites.model.SpritesEngine;
import sprites.model.json.GameJsonConverter;
import util.events.EventsListener;
import util.events.EventsSource;
import util.io.ZipLoaderImp;
import util.sync.SchedulerUtil;
import android.GraphicImp;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 *
 * The main class for the android version of LemonSprites
 */
public class Main extends AppCompatActivity implements EventsSource, View.OnTouchListener {

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
     * The event engine reacting to the main key events
     */
    private static EventEngine eventEngine = null;

    /**
     * Refresh rate of the scheduler in milliseconds
     */
    private final static long delay = 80;//17 ms for 60 frames/s~

    /**
     * Total duration of the schedulers in milliseconds
     */
    private final static long totalDuration = 150000;

    /**
     * The array of listeners that listen to DirectionEvents
     */
    private final static ArrayList<EventsListener> eventsListeners = new ArrayList<>();

    /**
     * The keyboard key that has been pressed by the player
     */
    private static int lastKeyCode = -1;

    /**
     * Whether the last pressed key is released or not
     */
    private static boolean isKeyReleased = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Preparing ZipLoader
        HashMap<String, Image> images = new HashMap<>();
        GameJsonConverter jsonConverter = new GameJsonConverter(images);
        ImageLoaderImp imgLoader = new ImageLoaderImp();
        ZipLoaderImp<Game, Image> zipLoad = new ZipLoaderImp<>(jsonConverter, imgLoader, images);


        // Loading zip and creating scene
        Game game = zipLoad.load(this.getResources().openRawResource(R.raw.game));

        setContentView(R.layout.activity_main);

        // If movie successfully loaded, create window and engines -> starts the graphical application
        if (game != null) {
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

            // get the greatest background height to set the view height
            int maxheight = 0;
            for (Sequence seq : game.getSequences()) {
                if (seq.getBackground().getHeight() > maxheight) {
                    maxheight = seq.getBackground().getHeight();
                }
            }

            // Displaying snapshot
            graphic = new GraphicImp(((SurfaceView)this.findViewById(R.id.surfaceView)), game.getWidth(), maxheight);

            SingleObjectHolderImp<Snapshot> snapshotHolder = new SingleObjectHolderImp<>();

            ArrayList<String> actionQueue = new ArrayList<>();

            // Event engine
            eventEngine = new EventEngine(actionQueue);

            // Sprites engine
            spritesEngine = new SpritesEngine(game, snapshotHolder, actionQueue);
            SchedulerUtil spritesScheduler = new SchedulerUtil(spritesEngine, delay, totalDuration);
            spritesEngine.setScheduler(spritesScheduler);

            // Graphical engine
            graphicEngine = new GraphicEngine(snapshotHolder, graphic);
            SchedulerUtil graphicalScheduler = new SchedulerUtil(graphicEngine, delay, totalDuration);
            graphicEngine.setScheduler(graphicalScheduler);

            // Start the movie by launching the engines
            spritesEngine.start();
            graphicEngine.start();
            this.register(eventEngine);


        }

        (this.findViewById(R.id.up_button)).setOnTouchListener(this);
        (this.findViewById(R.id.right_button)).setOnTouchListener(this);
        (this.findViewById(R.id.left_button)).setOnTouchListener(this);
        (this.findViewById(R.id.down_button)).setOnTouchListener(this);
    }

    /**
     * To fire an event to every register
     */
    @Override
    public void notifyListeners() {
        if (lastKeyCode != -1) {
            switch (lastKeyCode){
                case R.id.left_button:
                    for (EventsListener el : eventsListeners) {
                        if (isKeyReleased) {
                            el.actionPerformed(new DirectionEvent("END_left"));
                        } else {
                            el.actionPerformed(new DirectionEvent("left"));
                        }
                    }
                    break;

                case R.id.right_button:
                    for (EventsListener el : eventsListeners) {
                        if (isKeyReleased) {
                            el.actionPerformed(new DirectionEvent("END_right"));
                        } else {
                            el.actionPerformed(new DirectionEvent("right"));
                        }

                    }
                    break;

                case R.id.up_button:
                    for (EventsListener el : eventsListeners) {
                        if (isKeyReleased) {
                            el.actionPerformed(new DirectionEvent("END_up"));
                        } else {
                            el.actionPerformed(new DirectionEvent("up"));
                        }
                    }
                    break;

                case R.id.down_button:
                    for (EventsListener el : eventsListeners) {
                        if (isKeyReleased) {
                            el.actionPerformed(new DirectionEvent("END_down"));
                        } else {
                            el.actionPerformed(new DirectionEvent("down"));
                        }
                    }
                    break;
            }
        }
    }

    /**
     * To register a new listener to the subscriber list
     *
     * @param listener : the new listener
     * @pre listener != null
     */
    @Override
    public void register(EventsListener listener) {
        assert listener != null;
        eventsListeners.add(listener);
    }

    /**
     * To unregister a listener from the subscriber list
     *
     * @param listener : the listener to unsubscribe
     * @pre listener != null
     */
    @Override
    public void unregister(EventsListener listener) {
        assert listener != null;
        eventsListeners.remove(listener);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            lastKeyCode = view.getId();
            isKeyReleased = false;
            notifyListeners();
            lastKeyCode = -1;
        } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            lastKeyCode = view.getId();
            isKeyReleased = true;
            notifyListeners();
            lastKeyCode = -1;
        }

        return true;
    }
}
