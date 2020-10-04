package swing.json;

import gui.graphic.Image;
import gui.graphic.SnapshotLayer;
import org.json.JSONObject;
import swing.graphic.ImageMock;
import swing.graphic.SnapshotLayerMock;

import java.util.Iterator;

/**
 * A class representing a Snapshot layer from/to json converter
 * @see gui.json.SnapshotLayerJsonConverter
 */
public class SnapshotLayerJsonConverter implements gui.json.SnapshotLayerJsonConverter {

    @Override
    public SnapshotLayer jsonToLayer(JSONObject jsonObj) {

        SnapshotLayer layer = null;

        // Precondition
        if(jsonObj != null && jsonObj.has("image") && jsonObj.has("x") && jsonObj.has("y")){

            // Get required properties (image, x, y)
            String name = jsonObj.getString("image");
            int x = jsonObj.getInt("x");
            int y = jsonObj.getInt("y");

            Image img;
            // Get optionnal properties (width, height) if they are given
            if(jsonObj.has("width") && jsonObj.has("height")){
                int width = jsonObj.getInt("width");
                int height = jsonObj.getInt("height");

                // If there are optionnal properties, create Image with width and height
                img = new ImageMock(width, height, name);
            }
            else{
                // If there are no optionnal properties, create Image with name only
                img = new ImageMock(name);
            }

            layer = new SnapshotLayerMock(x, y, img);
        }
        else{
            System.out.println("One of the required fields (image, x, y) is missing in the snapshot layer json.");
        }

        // Create new layer
        return layer;
    }

    @Override
    public JSONObject layerToJson(SnapshotLayer layer) {

        JSONObject jsonLayer = null;

        // Precondition
        if(layer != null && layer.getImage() != null){

            jsonLayer = new JSONObject();
            Image img = layer.getImage();

            // Putting required properties into json (image, x, y)
            jsonLayer.put("image", img.getName());
            jsonLayer.put("x", layer.getX());
            jsonLayer.put("y", layer.getY());

            // Putting optionnal properties into json, if they are set
            if(img.getLength() != 0 && img.getWidth() != 0){
                jsonLayer.put("width", img.getWidth());
                jsonLayer.put("length", img.getLength());
            }
        }
        else{
            System.out.println("Layer or image is null.");
        }

        return jsonLayer;
    }
}