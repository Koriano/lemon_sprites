package gui.json;

import gui.graphic.*;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Alexandre HAMON
 *
 * A class to convert Json object from/to snapshot object.
 * @see gui.json.SnapshotJsonConverter
 */

public class SnapshotJsonConverterImp implements SnapshotJsonConverter{

    /**
     * The layer converter containing the images to create snapshot.
     */
    private SnapshotLayerJsonConverterImp layerConverter;

    /**
     * Constructor of SnapshotJsonConverterImp
     *
     * @param layerConverter: the layer converter with already loaded images.
     */
    public SnapshotJsonConverterImp(SnapshotLayerJsonConverterImp layerConverter){
        this.layerConverter = layerConverter;
    }

    /**
     * This method converts a Json object to a snapshot object
     *
     * @pre jsonObj != null
     *
     * @param jsonObj : json describing a snapshot
     * @return the snapshot described
     */
    @Override
    public Snapshot jsonToSnapshot(JSONObject jsonObj) {

        Snapshot snapshot = null;

        // Precondition
        if(jsonObj != null && this.layerConverter != null){

            // Getting snapshot layers
            if(jsonObj.has("layers")){
                JSONArray jsonLayers = jsonObj.getJSONArray("layers");
                int length = jsonLayers.length();

                // Creating final layers tab
                SnapshotLayer[] layers = new SnapshotLayer[length];

                // Iterating over every jsonLayer
                for(int i=0; i<length; i++){
                    layers[i] = this.layerConverter.jsonToLayer(jsonLayers.getJSONObject(i));
                }

                // Create returned snapshot
                snapshot = new SnapshotImp(layers);
            }
        }
        else{
            System.out.println("Wrong json content or wrong SnapshotLayerJsonConverter.");
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
    public JSONObject snapshotToJson(Snapshot snapshot) {

        JSONObject jsonSnapshot = new JSONObject();

        // Precondition
        if(snapshot != null){

            // Getting layers
            SnapshotLayer[] layers = snapshot.getSnapshotLayers();

            // Convert layer to json
            SnapshotLayerJsonConverterImp layerConverter = new SnapshotLayerJsonConverterImp(null);
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