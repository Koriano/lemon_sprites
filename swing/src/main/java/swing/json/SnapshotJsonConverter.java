package swing.json;

import gui.graphic.Snapshot;
import gui.graphic.SnapshotLayer;
import org.json.JSONArray;
import org.json.JSONObject;
import swing.graphic.SnapshotMock;

/**
 * A class representing a Snapshot from/to json converter
 * @see gui.json.SnapshotJsonConverter
 */
public class SnapshotJsonConverter implements gui.json.SnapshotJsonConverter{

    @Override
    public Snapshot jsonToSnapshot(JSONObject jsonObj) {

        Snapshot snapshot = null;

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
                snapshot = new SnapshotMock(layers);
            }
        }
        else{
            System.out.println("Wrong json content on snapshot converter.");
        }

        return snapshot;
    }

    @Override
    public JSONObject snapshotToJson(Snapshot snapshot) {

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