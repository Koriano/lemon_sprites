package sprites.model.json;

import gui.graphic.Image;
import org.json.JSONArray;
import org.json.JSONObject;
import sprites.model.Sequence;
import sprites.model.Sprite;
import sprites.model.SpriteAction;
import util.json.JsonConverter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Alexandre HAMON
 *
 * A class that implements the {@link JsonConverter} for Sequence objects
 *
 * @inv this.spriteJsonConverter != null && actionJsonConverter != null && images != null
 */
public class SequenceJsonConverter implements JsonConverter<Sequence> {

    /**
     * List of images of zip file
     */
    private HashMap<String, Image> images;

    /**
     * Json converter to convert json to sprites
     */
    private SpriteJsonConverter spriteJsonConverter;

    /**
     * Json converter to convert json to actions
     */
    private ActionJsonConverter actionJsonConverter;

    /**
     * Constructor of SequenceJsonConverter class
     */
    public SequenceJsonConverter(HashMap<String, Image> images){
        this.images = images;
        this.spriteJsonConverter = new SpriteJsonConverter(images);
        this.actionJsonConverter = new ActionJsonConverter();
        invariant();
    }

    /**
     * Invariant of the class
     */
    private void invariant(){
        assert this.spriteJsonConverter != null && actionJsonConverter != null && images != null: "Invariant violated";
    }

    /**
     * Convert a object of type Sequence to json object
     *
     * @param object : an object of Sequence type
     *
     * @return the json object describing the specified Sequence
     *
     * @pre object != null && object.getBackground() != null
     *
     * @post result != null
     */
    @Override
    public JSONObject convertToJson(Sequence object) {

        // Precondition
        assert object != null && object.getBackground() != null: "Precondition violated";

        // Returned object
        JSONObject returnedJson = new JSONObject();

        // Get required properties of the object and put it into the json
        returnedJson.put("background", object.getBackground().getName());
        returnedJson.put("duration", object.getDuration());

        // Optional properties
        if(object.getSprites() != null && object.getActions() != null){

            // Convert sprites to json
            JSONArray jsonSprites = new JSONArray();

            for(Sprite sp:object.getSprites()){
                jsonSprites.put(this.spriteJsonConverter.convertToJson(sp));
            }

            // Convert Actions to json
            JSONArray jsonActions = new JSONArray();

            for(SpriteAction act:object.getActions()){
                jsonActions.put(this.actionJsonConverter.convertToJson(act));
            }

            // Add sprites and json to returned json
            returnedJson.put("sprites", jsonSprites);
            returnedJson.put("actions", jsonActions);
        }

        invariant();

        // Postcondition
        assert returnedJson.has("background"): "Postcondition violated";

        return returnedJson;
    }

    /**
     * Convert a json object to object of type Sequence
     *
     * @param json : a json object
     *
     * @return the Sequence described by the json object
     *
     * @pre json != null && json.has("background")
     * @post result != null
     */
    @Override
    public Sequence convertFromJson(JSONObject json) {

        // Precondition
        assert json != null && json.has("background"): "Precondition violated";

        // Returned object
        Sequence returnedSequence = null;

        // Get required properties from json and convert into Sequence
        Image background = this.images.get(json.getString("background"));
        long duration = json.getLong("duration");

        // Optional properties
        if(json.has("sprites") && json.has("actions")){

            // Get sprites
            ArrayList<Sprite> sprites = new ArrayList<>();
            JSONArray jsonSprites = json.getJSONArray("sprites");

            for(int i=0; i<jsonSprites.length(); i++){
                sprites.add(this.spriteJsonConverter.convertFromJson(jsonSprites.getJSONObject(i)));
            }

            // Get actions
            HashMap<String, SpriteAction> actions = new HashMap<>();
            JSONArray jsonActions = json.getJSONArray("actions");
            JSONObject jsonAction;

            for(int i=0; i<jsonActions.length(); i++){
                jsonAction = jsonActions.getJSONObject(i);

                actions.put(jsonAction.getString("sprite"), this.actionJsonConverter.convertFromJson(jsonAction));
            }

            returnedSequence = new Sequence(background, duration, sprites, actions);
        }
        else{
            returnedSequence = new Sequence(background, duration, null, null);
        }

        invariant();

        // Postcondition
        assert returnedSequence != null && returnedSequence.getBackground() != null: "Postcondition violated.";

        return returnedSequence;
    }
}
