package util.json;

import org.json.JSONObject;
import util.io.Loader;

import java.io.*;

/**
 * @author Mathis RACINNE-DIVET
 *
 * Implementation of the JsonLoader interface to load/save json from/to input/output stream.
 */

public class JsonLoaderImp implements Loader<JSONObject> {

  /**
   * @see Loader#load(InputStream) 
   * Load a JSONObject from an input stream
   *
   * @param stream: the stream containing the file
   * @return the JSONObject loaded
   * @pre stream != null
   */
  @Override
  public JSONObject loadJson(InputStream stream) {
    assert stream != null;

    // The JSONObject to return
    JSONObject jsonObject = null;

    // Try with resource stream - closes the stream at the end of use
    try (InputStreamReader streamReader = new InputStreamReader(stream)){
      BufferedReader bf = new BufferedReader(streamReader);
      StringBuilder json = new StringBuilder();
      String line = bf.readLine();

      // Reading each line of the json file
      while (line != null) {
        json.append(line);
        line = bf.readLine();
      }

      // Parsing the json into a JSONObject
      jsonObject = new JSONObject(json.toString());

    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("Cannot read the file");
    }

    return jsonObject;
  }


  /**
   * This method saves a json object using an output stream
   * @param stream The OutputStream of the file to save
   * @param jsonObj The JSON object to write in a file
   * @pre stream != null && jsonObj != null
   */
  public void saveJson(OutputStream stream, JSONObject jsonObj) {
    assert stream != null && jsonObj != null;

    PrintWriter pw = new PrintWriter(stream);
    pw.println(jsonObj.toString());
    pw.close();

  }

}