package sprites.swing;

import gui.graphic.Snapshot;
import gui.json.SnapshotJsonConverterImp;
import gui.json.SnapshotLayerJsonConverterImp;
import org.json.JSONObject;
import swing.GraphicImp;
import swing.ImageImp;
import util.json.JsonLoaderImp;

import java.io.File;

/**
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 *
 * A main class to test implementation of lemon_sprites v1.0
 */

public class Main {
    public static void main(String[] args){
        String IMAGE_DIRECTORY = "sprites.swing/img/";
        JsonLoaderImp jloader = new JsonLoaderImp();

        // Getting all file names in img directory
        String[] images_name;
        File f = new File(IMAGE_DIRECTORY);
        images_name = f.list();

        int nb_images = images_name.length;

        // Loading images
        ImageImp[] loaded_images = new ImageImp[nb_images];
        for(int i=0; i<nb_images; i++){
            loaded_images[i] = new ImageImp(IMAGE_DIRECTORY + images_name[i]);
        }

        // Initializing converters with loaded images
        SnapshotLayerJsonConverterImp layerConverter = new SnapshotLayerJsonConverterImp(loaded_images);
        SnapshotJsonConverterImp snapConverter = new SnapshotJsonConverterImp(layerConverter);

        // Converting json to snapshot
        JSONObject json = jloader.loadJson("sprites.swing/json/snapshot.json");
        Snapshot snapshot = snapConverter.jsonToSnapshot(json);

        // Displaying snapshot
        GraphicImp graphic = new GraphicImp();
        graphic.displaySnapshot(snapshot);
    }
}
