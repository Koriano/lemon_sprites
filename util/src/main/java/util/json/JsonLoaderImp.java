package util.json;

import org.json.JSONObject;

import java.io.*;

/**
 * @author Mathis RACINNE-DIVET
 *
 * Implementation of the JsonLoader interface to load/save json from/to input/output stream.
 */

public class JsonLoaderImp implements JsonLoader {

  /**
   * @see JsonLoader
   * This method loads a json object from an input stream
   *
   * @param path The path of the file to load
   * @pre path != null
   * @return a json object
   */
  @Override
  public JSONObject loadJson(String path) {
    // The JSONObject to return
    JSONObject jsonObject = null;

    if (path != null) {
      File jsonFile = new File(path);

      // Checks if the given path leads to an actual file on the disk
      if (jsonFile.exists()) {
        try {
          BufferedReader bf = new BufferedReader(new FileReader(path));
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
          System.err.println("Cannot read the file " + path);
        }

      } else {
        System.err.println("Error : the file " + path + " does not exist !");
      }
    }
    return jsonObject;
  }


  /**
   * This method saves a json object to an output stream
   * @param path The path of the file to save
   * @param jsonObj The JSON object to write in a file
   * @pre stream != null
   */
  @Override
  public void saveJson (String path, JSONObject jsonObj) {
    if (path != null && jsonObj != null) {
      File jsonFile = new File(path);

      if (jsonFile.exists()) {
        jsonFile.delete();
      }

      // Try with ressource -> the output stream is automatically closed
      try (PrintWriter pw = new PrintWriter(jsonFile);) {
        pw.println(jsonObj.toString());
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }
  }

}