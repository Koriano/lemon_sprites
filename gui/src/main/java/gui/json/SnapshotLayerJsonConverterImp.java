package gui.json;

import gui.graphic.Image;
import gui.graphic.SnapshotLayer;
import gui.graphic.SnapshotLayerImp;
import org.json.JSONObject;
import util.json.JsonConverter;

/**
 * @author Alexandre HAMON
 *
 * A class to convert Json object from/to snapshotlayer object.
 * @see JsonConverter
 */

public class SnapshotLayerJsonConverterImp implements JsonConverter<SnapshotLayer> {

    /**
     * The list of images already loaded.
     */
    private Image[] images;

    /**
     * Constructor of the SnapshotLayerJsonConverterImp
     *
     * @param images: the images already loaded
     */
    public SnapshotLayerJsonConverterImp(Image[] images){
        this.images = images;
    }

    /**
     * This method converts a Json object to a snapshotlayer object
     *
     * @pre jsonObj != null && jsonObj.has("image") && jsonObj.has("x") && jsonObj.has("y")
     *
     * @param jsonObj : json describing the layer
     * @return a snapshotlayer object
     */
    @Override
    public SnapshotLayer convertFromJson(JSONObject jsonObj) {

        SnapshotLayer layer = null;

        // Precondition
        if(jsonObj != null && jsonObj.has("image") && jsonObj.has("x") && jsonObj.has("y")){

            // Get required properties (image, x, y)
            String name = jsonObj.getString("image");
            int x = jsonObj.getInt("x");
            int y = jsonObj.getInt("y");

            layer = new SnapshotLayerImp(this.getImage(name), x, y);
        }
        else{
            System.out.println("One of the required fields (image, x, y) is missing in the snapshot layer json.");
        }

        return layer;
    }

    /**
     * This method converts a snapshotlayer object to a Json object
     *
     * @pre layer != null && layer.getImage() != null
     *
     * @param layer : snapshot layer
     * @return a json describing the given layer
     */
    @Override
    public JSONObject convertToJson(gui.graphic.SnapshotLayer layer) {

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

    /**
     * Gives the image corresponding to the name given in paramters
     *
     * @pre name != null && !name.equals("") && this.images != null && this.images.length > 0
     *
     * @param name: the file name
     * @return the loaded image corresponding
     */
    private Image getImage(String name){

        Image return_image = null;

        if(name != null && !name.equals("") && this.images != null && this.images.length > 0){

            // Iterating over Image list
            for(Image image: this.images){
                if(name.equals(image.getName())){
                    return_image = image;
                }
            }
        }

        return return_image;
    }
}