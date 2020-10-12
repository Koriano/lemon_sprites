package sprites.swing;

import gui.graphic.Image;
import gui.graphic.Snapshot;
import json.SceneJsonConverter;
import swing.GraphicImp;
import swing.ImageLoaderImp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import sprites.model.Scene;
import swing.sync.SchedulerSwing;
import util.io.ZipLoaderImp;
import util.sync.Scheduler;
import util.sync.SchedulerListener;

/**
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 *
 * A main class to test implementation of lemon_sprites v1.0
 */

public class Main implements SchedulerListener {

    /**
     * The static instance of the window
     */
    private static GraphicImp graphic;

    /**
     * The static instance of the currently displayed scene
     */
    private static Scene scene;

    /**
     * The static instance of the graphical scheduler
     */
    private static Scheduler graphicalScheduler;

    public static void main(String[] args){
        // Preparing ZipLoader
        String DATA_DIRECTORY = "sprites.swing/data/";
        ArrayList<Image> images = new ArrayList<>();
        SceneJsonConverter jsonConverter = new SceneJsonConverter(images);
        ImageLoaderImp imgLoader = new ImageLoaderImp();
        ZipLoaderImp<Scene, Image> zipLoad = new ZipLoaderImp<>(jsonConverter, imgLoader, images);

        // Loading zip and creating scene

        scene = null;
        try {
            scene = zipLoad.load(new FileInputStream(new File(DATA_DIRECTORY+"scene.zip")));

        } catch (FileNotFoundException e) {
            System.err.println("ERROR : FAILED TO LOAD ZIP FILE !");
            e.printStackTrace();
        }


        // If scene successfully loaded, create window and scheduler -> starts the graphical application
        if (scene != null) {
            Snapshot snapshot = scene.getCurrentSnapshot(0);

            // Displaying snapshot
            graphic = new GraphicImp();
            graphic.displaySnapshot(snapshot);

            graphicalScheduler = new SchedulerSwing(new Main(), 1000, 10000);//TODO Récupérer delay et totalDuration en fction des Scene/Snapshot

            graphicalScheduler.start();

        }
    }

    /**
     * The task triggered by the scheduler
     *
     * @param timeOftrigger
     */
    @Override
    public void trigger(long timeOftrigger) {
        System.out.println("Trigger graphique !");
        Snapshot snapshot = scene.getCurrentSnapshot(timeOftrigger);
        graphic.displaySnapshot(snapshot);
    }
}
