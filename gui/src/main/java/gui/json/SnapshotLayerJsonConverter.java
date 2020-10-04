package gui.json;
import gui.graphic.SnapshotLayer;
import org.json.JSONObject;

/**
@author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY

A class to convert Json object from/to snapshotlayer object. 

 */

public interface SnapshotLayerJsonConverter {

    /**
    This method converts a Json object to a snapshotlayer object

    @pre jsonObj != null
    @return a snapshotlayer object
     */

    public SnapshotLayer jsonToLayer(JSONObject jsonObj); 


    /**
    This method converts a snapshotlayer object to a Json object

    @pre layer != null
    @return a json object
     */

    public JSONObject layerToJson (SnapshotLayer layer);

}