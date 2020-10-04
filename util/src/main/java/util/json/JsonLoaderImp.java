package util.json;

import org.json.JSONObject;
import java.io.InputStream;
import java.io.OutputStream;

/**
 @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY

 Implementation of the JsonLoader interface to load/save json from/to input/output stream.
 */

public class JsonLoaderImp implements JsonLoader {

  /**
  This method loads a json object from an input stream 

  @pre stream != null
  @return a json object    
   */
  @Override
  public JSONObject loadJson(InputStream stream) {
    return new JSONObject();
  }


  /**
  This method saves a json object to an output stream 

  @pre stream != null  
   */
  @Override
  public void saveJson (OutputStream stream) {

  }

}