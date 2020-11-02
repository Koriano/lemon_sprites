package sprites.model.json;

import org.json.JSONObject;
import sprites.model.SpriteAction;
import util.json.JsonConverter;

/**
 * @author Alexandre HAMON
 *
 * A class that implements the {@link JsonConverter} interface for SpriteAction objects
 */
public class ActionJsonConverter implements JsonConverter<SpriteAction> {

    /**
     * Convert a object of type ActionSprite to json object
     *
     * @param object : an object of ActionSprite type
     *
     * @return the json object describing the specified ActionSprite
     *
     * @pre object != null && object.getStartTime() >= 0 && object.getSprite() != null && !"".equals(object.getSprite())
     * @post returnedJson.keySet().size() > 0 && returnedJson.has("time") && returnedJson.has("sprite")
     */
    @Override
    public JSONObject convertToJson(SpriteAction object) {

        // Precondition
        assert object != null && object.getStartTime() >= 0 && object.getSprite() != null && !"".equals(object.getSprite()): "Precondition violated.";

        // Returned object
        JSONObject returnedJson = new JSONObject();

        // Get properties and put it into json
        returnedJson.put("sprite", object.getSprite());
        returnedJson.put("time", object.getStartTime());
        returnedJson.put("endTime", object.getEndTime());
        returnedJson.put("endX", object.getEndX());
        returnedJson.put("endY", object.getEndY());
        returnedJson.put("visible", object.getVisible());

        // Postcondition
        assert returnedJson.keySet().size() > 0 && returnedJson.has("time") && returnedJson.has("sprite"): "Postcondition violated";

        return returnedJson;
    }

    /**
     * Convert a json object to object of type ActionSprite
     *
     * @param json : a json object
     *
     * @return the ActionSprite described by the json object
     *
     * @pre json != null && json.has("time") && json.has("sprite")
     * @post returnedAction != null && returnedAction.getStartTime() >= 0 && returnedAction.getSprite() != null && !"".equals(returnedAction.getSprite())
     */
    @Override
    public SpriteAction convertFromJson(JSONObject json) {

        // Precondition
        assert json != null && json.has("time") && json.has("sprite"): "Precondition violated";

        // Returned object
        SpriteAction returnedAction = null;

        // Get required properties from json and put it into object
        String sprite = json.getString("sprite");
        int time = json.getInt("time");

        // And other properties, if given
        if(json.has("endTime")){
            returnedAction = new SpriteAction(sprite, time, json.getInt("endTime"), json.getInt("endX"), json.getInt("endY"), json.getBoolean("visible"));
        }
        else{
            returnedAction = new SpriteAction(sprite, time, -1, json.getInt("endX"), json.getInt("endY"), json.getBoolean("visible"));
        }

        // Postcondition
        assert returnedAction != null && returnedAction.getStartTime() >= 0 && returnedAction.getSprite() != null && !"".equals(returnedAction.getSprite()): "Postcondition violated";

        return returnedAction;
    }
}
