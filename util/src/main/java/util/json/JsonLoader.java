package util.json;
import org.json.JSONObject;

/**
@author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY

A class to load/save json from/to input/output stream. 

*/

public interface JsonLoader {

    /**
    This method loads a json object from an input stream 

    @pre !(path.equals(""))

    @param path : the image path
    @return a json object    
     */

    public JSONObject loadJson(String path);


    /**
    This method saves a json object to an output stream 

    @pre !(path.equals("")) && jsonObj != null

    @param path : the image path
    @param jsonObj : the json to save
     */
    public void saveJson (String path, JSONObject jsonObj);

}