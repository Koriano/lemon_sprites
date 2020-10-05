package swing.json;

import gui.graphic.SnapshotLayer;
import org.json.JSONArray;
import org.json.JSONObject;
import swing.graphic.Snapshot;

/**
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 *
 * A class to convert Json object from/to snapshot object.
 * @see gui.json.SnapshotJsonConverter
 */
public class SnapshotJsonConverter implements gui.json.SnapshotJsonConverter{

    /**
     * This method converts a Json object to a snapshot object
     *
     * @pre jsonObj != null
     *
     * @param jsonObj : json describing a snapshot
     * @return the snapshot described
     */
    @Override
    public gui.graphic.Snapshot jsonToSnapshot(JSONObject jsonObj) {

        gui.graphic.Snapshot snapshot = null;

        // Precondition
        if(jsonObj != null){

            // Getting snapshot layers
            if(jsonObj.has("layers")){
                JSONArray jsonLayers = jsonObj.getJSONArray("layers");
                int length = jsonLayers.length();

                // Convert JSONArray to SnapshotLayer array
                SnapshotLayer[] layers = new SnapshotLayer[length];
                SnapshotLayerJsonConverter layerConverter = new SnapshotLayerJsonConverter();

                for(int i=0; i<length; i++){
                    layers[i] = layerConverter.jsonToLayer(jsonLayers.getJSONObject(i));
                }

                // Create returned snapshot
                snapshot = new Snapshot(layers);
            }
        }
        else{
            System.out.println("Wrong json content on snapshot converter.");
        }

        return snapshot;
    }

    /**
     * This method converts a snapshot object to a Json object
     *
     * @pre snapshot != null
     *
     * @param snapshot : snapshot composed of snapshot layers
     * @return the json describing the given snapshot
     */
    @Override
    public JSONObject snapshotToJson(gui.graphic.Snapshot snapshot) {

        JSONObject jsonSnapshot = new JSONObject();

        // Precondition
        if(snapshot != null){

            // Getting layers
            SnapshotLayer[] layers = snapshot.getSnapshotLayers();

            // Convert layer to json
            SnapshotLayerJsonConverter layerConverter = new SnapshotLayerJsonConverter();
            JSONArray jsonLayers = new JSONArray();

            for(SnapshotLayer layer: layers){
                jsonLayers.put(layerConverter.layerToJson(layer));
            }

            jsonSnapshot.put("layers", jsonLayers);
        }
        else{
            System.out.println("Null snapshot on json converter.");
        }

        return jsonSnapshot;
    }
}