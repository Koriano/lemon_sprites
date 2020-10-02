package json; 
import graphic.Snapshot; 

/**
@author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY

A class to convert Json object from/to snapshot object. 

 */

public interface SnapshotJsonConverter {

    /**
    This method converts a Json object to a snapshot object

    @pre jsonObj != null
    @return a snapshot object
     */

    public static Snapshot jsonToSnapshot(JsonObject jsonObj); 


    /**
    This method converts a snapshot object to a Json object

    @pre snapshot != null
    @return a json object
     */
    public static JsonObject snapshotToJson (Snapshot snapshot);

}