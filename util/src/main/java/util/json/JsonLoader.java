package json; 
import java.io.InputStream; 
import java.io.OutputStream; 

/**
@author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY

A class to load/save json from/to input/output stream. 

*/

public interface JsonLoader {

    /**
    This method loads a json object from an input stream 

    @pre stream != null
    @return a json object    
     */

    public static JsonObject loadJson(InputStream stream); 


    /**
    This method saves a json object to an output stream 

    @pre stream != null  
     */
    public static void saveJson (OutputStream stream); 

}