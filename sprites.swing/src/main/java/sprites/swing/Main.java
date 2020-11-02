package sprites.swing;

import gui.graphic.Image;
import gui.graphic.Snapshot;
import sprites.model.json.SceneJsonConverter;
import swing.GraphicImp;
import swing.ImageLoaderImp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import sprites.model.Scene;
import swing.sync.SchedulerSwing;
import util.io.ZipLoaderImp;
import util.sync.Scheduler;
import util.sync.SchedulerListener;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 *
 * The main class for the swing version of LemonSprites
 */

public class Main implements SchedulerListener, ActionListener {

    /**
     * The path of the zip file to load
     */
    private static String filePath = null;

    /**
     * The static instance of the window
     */
    private static GraphicImp graphic = null;

    /**
     * The static instance of the currently displayed scene
     */
    private static Scene scene;

    /**
     * The static instance of the graphical scheduler
     */
    private static Scheduler graphicalScheduler = null;

    /**
     * The system time at the beginning of the animation
     */
    private static long startTime;

    /**
     * Refresh rate of the scheduler in milliseconds
     */
    private final static long delay = 17;//17 ms for 60 frames/s~

    /**
     * Total duration of the scene in milliseconds
     */
    private final static long totalDuration = 15000;

    /**
     * The listener that will be used by the scheduler and le graphic menu bar
     */
    private final static Main listener = new Main();

    public static void main(String[] args){

        // Loading zip
        showFileChooser();

        // Load and start the app
        loadZip();

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

            // If the movie is already started
            if (graphicalScheduler != null) {
                // Stop the scheduler
                graphicalScheduler.stop();
            }

            // Preparing ZipLoader
            HashMap<String, Image> images = new HashMap<>();
            SceneJsonConverter jsonConverter = new SceneJsonConverter(images);
            ImageLoaderImp imgLoader = new ImageLoaderImp();
            ZipLoaderImp<Scene, Image> zipLoad = new ZipLoaderImp<>(jsonConverter, imgLoader, images);

            // Loading zip and creating scene
            scene = null;
            try {
                scene = zipLoad.load(new FileInputStream(new File(filePath)));

            } catch (FileNotFoundException e) {
                System.err.println("ERROR : FAILED TO LOAD ZIP FILE !");
                e.printStackTrace();
            }


            // If scene successfully loaded, create window and scheduler -> starts the graphical application
            if (scene != null) {
                Snapshot snapshot = scene.getCurrentSnapshot(0);
                startTime = System.currentTimeMillis();


                // Displaying snapshot
                if (graphic == null) {
                    graphic = new GraphicImp(listener);
                }
                graphic.displaySnapshot(snapshot);


                graphicalScheduler = new SchedulerSwing(listener, delay, totalDuration);

                graphicalScheduler.start();

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
