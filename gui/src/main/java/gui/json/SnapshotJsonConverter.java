package gui.json;
import gui.graphic.Snapshot;
import org.json.JSONObject;

/**
@author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY

A class to convert Json object from/to snapshot object. 

 */

public interface SnapshotJsonConverter {

    /**
    This method converts a Json object to a snapshot object

    @pre jsonObj != null

    @param jsonObj : json describing a snapshot
    @return the snapshot described
     */

    public Snapshot jsonToSnapshot(JSONObject jsonObj); 


    /**
    This method converts a snapshot object to a Json object

    @pre snapshot != null

    @param snapshot : snapshot composed of snapshot layers
    @return the json describing the given snapshot
     */
    public JSONObject snapshotToJson (Snapshot snapshot);

}