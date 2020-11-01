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
     * @pre object != null && object.getStartTime() >= 0
     * @post result != null
     */
    @Override
    public JSONObject convertToJson(SpriteAction object) {

        // Precondition
        assert object != null: "Precondition violated.";

        // Returned object
        JSONObject returnedJson = new JSONObject();

        // Get properties and put it into json
        returnedJson.put("time", object.getStartTime());
        returnedJson.put("endTime", object.getEndTime());
        returnedJson.put("endX", object.getEndX());
        returnedJson.put("endY", object.getEndY());
        returnedJson.put("visible", object.getVisible());

        // Postcondition
        assert returnedJson.keySet().size() > 0 && returnedJson.has("time"): "Postcondition violated";

        return returnedJson;
    }

    /**
     * Convert a json object to object of type ActionSprite
     *
     * @param json : a json object
     *
     * @return the ActionSprite described by the json object
     *
     * @pre json != null && json.has("time")
     * @post result != null
     */
    @Override
    public SpriteAction convertFromJson(JSONObject json) {

        // Precondition
        assert json != null && json.has("time"): "Precondition violated";

        // Returned object
        SpriteAction returnedAction = null;

        // Get required properties from json and put it into object
        int time = json.getInt("time");

        // And other properties, if given
        if(json.has("endTime") && json.has("endX") && json.has("endY")) {
            returnedAction = new SpriteAction(time, json.getInt("endTime"), json.getInt("endX"), json.getInt("endY"));
        }
        else if(json.has("visible")){
            returnedAction = new SpriteAction(time, json.getBoolean("visible"));
        }

        // Postcondition
        assert returnedAction != null && returnedAction.getStartTime() >= -1: "Postcondition violated";

        return returnedAction;
    }
}
