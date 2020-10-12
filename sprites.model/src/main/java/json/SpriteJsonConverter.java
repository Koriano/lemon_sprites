package json;

import gui.graphic.Image;
import org.json.JSONArray;
import org.json.JSONObject;
import sprites.model.Sprite;
import util.json.JsonConverter;

import java.util.ArrayList;

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
        ArrayList<Image> images = sprite.getImages();
        JSONArray names = new JSONArray();

        for(Image img: images){
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
     * @post result != null && result.getName() != null && !result.getName().equals("") && result.getDuration() > 0
     */
    @Override
    public Sprite convertFromJson(JSONObject jsonSprite) {

        assert jsonSprite != null && jsonSprite.has("name") && jsonSprite.has("duration"):
                "SpriteJsonConverter#convertFromJson";

        String name = jsonSprite.getString("name");
        int duration = jsonSprite.getInt("duration");
        int x = jsonSprite.getInt("x");
        int y = jsonSprite.getInt("y");
        boolean visible = jsonSprite.getBoolean("visible");

        JSONArray array = jsonSprite.getJSONArray("images");

        return null; //TODO Fixer ce retour par terrible (il faudra aussi passer le ArrayList<Image> au constructeur de la classe pour rebalancer ça dans le constructeur de Sprite)
    }
}
