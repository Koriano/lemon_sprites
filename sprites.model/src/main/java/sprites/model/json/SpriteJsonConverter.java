package sprites.model.json;

import gui.graphic.Image;
import org.json.JSONArray;
import org.json.JSONObject;
import sprites.model.Sprite;
import sprites.model.SpriteImp;
import util.json.JsonConverter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Alexandre HAMON
 *
 * A class that implements the JsonConverter interface to convert Json to sprites and sprites to json
 * @see util.json.JsonConverter
 *
 * @inv this.loadedImages != null
 */
public class SpriteJsonConverter implements JsonConverter<Sprite> {

    /**
     * The map that contains the images used to build the sprites, related to their names
     */
    private HashMap<String, Image> images;


    /**
     * A constructor for the SpriteJsonConverter
     *
     * @param images: the map that contains the images used to build the sprites, related to their names
     *
     * @pre images != null
     */
    public SpriteJsonConverter(HashMap<String, Image> images){

        assert images != null:
                "SpriteJsonConverter#constructor : precondition violated";

        this.images = images;
    }


    /**
     * Convert a object of type Sprite to json object
     *
     * @param sprite : the sprite to convert
     * @return the json object describing the specified object
     *
     * @pre sprite != null && sprite.getName() != null && !sprite.getName().equals("") && sprite.getDuration() > 0
     * @post result != null && result.has("name") && result.has("duration")
     */
    @Override
    public JSONObject convertToJson(Sprite sprite) {

        // Precondition
        assert sprite != null && sprite.getName() != null && !sprite.getName().equals("") && sprite.getDuration() > 0:
                "SpriteJsonConverter#convertToJson : Precondition violated";

        JSONObject spriteRet = new JSONObject();

        // Get attributes
        spriteRet.put("name", sprite.getName());
        spriteRet.put("duration", sprite.getDuration());
        spriteRet.put("x", sprite.getX());
        spriteRet.put("y", sprite.getY());
        spriteRet.put("visible", sprite.isVisible());

        // Get image names
        ArrayList<Image> spriteImages = sprite.getImages();
        JSONArray names = new JSONArray();

        for(Image img: spriteImages){
            names.put(img.getName());
        }

        assert spriteRet != null && spriteRet.has("name") && spriteRet.has("duration"):
                "SpriteJsonConverter#convertTojson : postcondition violated";

        return spriteRet;
    }

    /**
     * Convert a json object to object of type Sprite
     *
     * @param jsonSprite : a json object describing the Sprite
     * @return the sprite described by the json object
     *
     * @pre jsonSprite != null && jsonSprite.has("name") && jsonSprite.has("duration")
     * @post result != null
     */
    @Override
    public Sprite convertFromJson(JSONObject jsonSprite) {

        assert jsonSprite != null && jsonSprite.has("name") && jsonSprite.has("duration"):
                "SpriteJsonConverter#convertFromJson: precondition violated";

        String name = jsonSprite.getString("name");
        int duration = jsonSprite.getInt("duration");
        int x = jsonSprite.getInt("x");
        int y = jsonSprite.getInt("y");
        boolean visible = jsonSprite.getBoolean("visible");

        JSONArray jsonImagesArray = jsonSprite.getJSONArray("images");

        ArrayList<Image> imagesList = new ArrayList<>();

        // Select the loaded images used in the current sprite
        for (int i = 0; i < jsonImagesArray.length(); i++) {
            Image img = this.images.get(jsonImagesArray.getString(i));

            if (img != null) {
                img.setName(jsonImagesArray.getString(i));
                imagesList.add(img);
            }
        }

        // Create sprite
        Sprite result = new SpriteImp(name, x, y, visible, imagesList, duration);

        // Postcondition
        assert result != null:
                "SpriteJsonConverter#convertFromJson: postcondition violated";

        return result;
    }
}
