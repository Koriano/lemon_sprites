package sprites.swing;

import gui.graphic.Graphic;
import gui.graphic.Snapshot;
import org.json.JSONObject;
import swing.json.SnapshotJsonConverter;
import util.json.*;

/**
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 *
 * A main class to test implementation of lemon_sprites v1.0
 */
public class Main {
    public static void main(String[] args){
        JsonLoader jloader = new JsonLoaderImp();
        SnapshotJsonConverter snapConverter = new SnapshotJsonConverter();

        JSONObject json = jloader.loadJson("sprites.swing/json/snapshot.json");
        Snapshot snapshot = snapConverter.jsonToSnapshot(json);

        Graphic graphic = new Graphic();
        graphic.displaySnapshot(snapshot);
    }
}
