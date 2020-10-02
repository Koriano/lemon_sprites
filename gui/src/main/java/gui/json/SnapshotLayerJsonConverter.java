package json; 
import graphic.SnapshotLayer; 

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

    public SnapshotLayer jsonToLayer(JsonObject jsonObj); 


    /**
    This method converts a snapshotlayer object to a Json object

    @pre layer != null
    @return a json object
     */

    public JsonObject layerToJson (SnapshotLayer layer);

}