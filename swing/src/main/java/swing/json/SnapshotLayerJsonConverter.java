package swing.json;

import org.json.JSONObject;
import swing.graphic.Image;
import swing.graphic.SnapshotLayer;

/**
 * A class representing a Snapshot layer from/to json converter
 * @see gui.json.SnapshotLayerJsonConverter
 */
public class SnapshotLayerJsonConverter implements gui.json.SnapshotLayerJsonConverter {

    @Override
    public gui.graphic.SnapshotLayer jsonToLayer(JSONObject jsonObj) {

        gui.graphic.SnapshotLayer layer = null;

        // Precondition
        if(jsonObj != null && jsonObj.has("image") && jsonObj.has("x") && jsonObj.has("y")){

            // Get required properties (image, x, y)
            String name = jsonObj.getString("image");
            int x = jsonObj.getInt("x");
            int y = jsonObj.getInt("y");

            gui.graphic.Image img;
            // Get optional properties (width, height) if they are given
            if(jsonObj.has("width") && jsonObj.has("height")){
                int width = jsonObj.getInt("width");
                int height = jsonObj.getInt("height");

                // If there are optional properties, create Image with width and height
                img = new Image(height, width, name);
            }
            else{
                // If there are no optional properties, create Image with name only
                img = new Image(name);
            }

            layer = new SnapshotLayer(x, y, img);
        }
        else{
            System.out.println("One of the required fields (image, x, y) is missing in the snapshot layer json.");
        }

        return layer;
    }

    @Override
    public JSONObject layerToJson(gui.graphic.SnapshotLayer layer) {

        JSONObject jsonLayer = null;

        // Precondition
        if(layer != null && layer.getImage() != null){

            jsonLayer = new JSONObject();
            gui.graphic.Image img = layer.getImage();

            // Putting required properties into json (image, x, y)
            jsonLayer.put("image", img.getName());
            jsonLayer.put("x", layer.getX());
            jsonLayer.put("y", layer.getY());

            // Putting optional properties into json, if they are set
            if(img.getHeight() != 0 && img.getWidth() != 0){
                jsonLayer.put("width", img.getWidth());
                jsonLayer.put("length", img.getHeight());
            }
        }
        else{
            System.out.println("Layer or image is null.");
        }

        return jsonLayer;
    }
}