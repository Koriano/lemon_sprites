package sprites.swing;

import gui.graphic.GraphicEngine;
import gui.graphic.Image;
import gui.graphic.Snapshot;
import sprites.model.DirectionEvent;
import sprites.model.Movie;
import sprites.model.SingleObjectHolderImp;
import sprites.model.SpritesEngine;
import sprites.model.json.MovieJsonConverter;
import swing.GraphicImp;
import swing.ImageLoaderImp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import swing.sync.SchedulerSwing;
import util.events.EventsListener;
import util.events.EventsSource;
import util.io.ZipLoaderImp;
import util.sync.SchedulerUtil;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 *
 * The main class for the swing version of LemonSprites
 */

public class Main implements ActionListener, EventsSource, KeyListener {

    /**
     * The path of the zip file to load
     */
    private static String filePath = null;

    /**
     * The static instance of the window
     */
    private static GraphicImp graphic = null;

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

    /**
     * The listener that will be used by the scheduler and le graphic menu bar
     */
    private final static Main listener = new Main();

    /**
     * The array of listeners that listen to DirectionEvents
     */
    private final static ArrayList<EventsListener> eventsListeners = new ArrayList<>();

    /**
     * The keyboard key that has been pressed by the player
     */
    private static int lastKeyCode = -1;


    public static void main(String[] args){
        // Loading zip
        showFileChooser();
    }


    /**
     * Displays a file chooser to select the zip file to load
     */
    private static void showFileChooser() {
        JFileChooser fileChooser = new JFileChooser(new File("data/"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("Fichier Zip", "zip"));
        if (fileChooser.showDialog(graphic, "Charger") == JFileChooser.APPROVE_OPTION) {
            filePath = fileChooser.getSelectedFile().getAbsolutePath();
            loadZip();
        }
    }

    /**
     * Loads the zip file and starts the movie
     */
    private static void loadZip() {
        if (filePath != null) {


            // Preparing ZipLoader
            HashMap<String, Image> images = new HashMap<>();
            MovieJsonConverter jsonConverter = new MovieJsonConverter(images);
            ImageLoaderImp imgLoader = new ImageLoaderImp();
            ZipLoaderImp<Movie, Image> zipLoad = new ZipLoaderImp<>(jsonConverter, imgLoader, images);

            // Loading zip and creating scene
            Movie movie = null;
            try {
                movie = zipLoad.load(new FileInputStream(new File(filePath)));

            } catch (FileNotFoundException e) {
                System.err.println("ERROR : FAILED TO LOAD ZIP FILE !");
                e.printStackTrace();
            }




            // If movie successfully loaded, create window and engines -> starts the graphical application
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
                if (graphic == null) {
                    graphic = new GraphicImp(listener);
                    graphic.addKeyListener(new Main());
                }

                SingleObjectHolderImp<Snapshot> snapshotHolder = new SingleObjectHolderImp<>();

                // Sprites engine
                spritesEngine = new SpritesEngine(movie, snapshotHolder);
                SchedulerUtil spritesScheduler = new SchedulerUtil(spritesEngine, delay, totalDuration);
                spritesEngine.setScheduler(spritesScheduler);

                // Graphical engine
                graphicEngine = new GraphicEngine(snapshotHolder, graphic);
                SchedulerSwing graphicalScheduler = new SchedulerSwing(graphicEngine, delay, totalDuration);
                graphicEngine.setScheduler(graphicalScheduler);

                // Start the movie by launching the engines
                spritesEngine.start();
                graphicEngine.start();

            }
        }
    }


    /**
     * Invoked when an action occurs on the menu bar.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        showFileChooser();
    }

    /**
     * To fire an event to every register
     */
    @Override
    public void notifyListeners() {

        if (lastKeyCode != -1) {
            switch (lastKeyCode){
                case KeyEvent.VK_LEFT:
                    for (EventsListener el : eventsListeners) {
                        el.actionPerformed(new DirectionEvent("left"));
                    }
                    break;

                case KeyEvent.VK_RIGHT:
                    for (EventsListener el : eventsListeners) {
                        el.actionPerformed(new DirectionEvent("right"));
                    }
                    break;

                case KeyEvent.VK_UP:
                    for (EventsListener el : eventsListeners) {
                        el.actionPerformed(new DirectionEvent("up"));
                    }
                    break;

                case KeyEvent.VK_DOWN:
                    for (EventsListener el : eventsListeners) {
                        el.actionPerformed(new DirectionEvent("down"));
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

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        lastKeyCode = e.getKeyCode();
        notifyListeners();
        lastKeyCode = -1;
    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {}
}
