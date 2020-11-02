package sprites.model.json;

import gui.graphic.Image;
import org.json.JSONArray;
import org.json.JSONObject;
import sprites.model.Sequence;
import sprites.model.SequenceImp;
import sprites.model.Sprite;
import sprites.model.SpriteAction;
import util.json.JsonConverter;

import java.util.ArrayList;
import java.util.Collections;
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
     *
     * @param images: the list of images used for the sequence
     *
     * @pre images != null
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
            ArrayList<SpriteAction> actions = new ArrayList<>();
            JSONArray jsonActions = json.getJSONArray("actions");

            // Set visibility for every action
            for(Sprite sprite:sprites){
                // visibility is false by default
                boolean visible = false;

                // Iterate over json actions of the current sprite
                for(int i=0; i<jsonActions.length(); i++){
                    JSONObject jsonAction = jsonActions.getJSONObject(i);

                    if(sprite.getName().equals(jsonAction.get("sprite"))){
                        // If visibility action, get visibility
                        if(jsonAction.has("visible")){
                            visible = jsonAction.getBoolean("visible");
                        }
                        // If moving action, set visibility
                        else{
                            jsonAction.put("visible", visible);
                        }
                    }
                }
            }

            for(int i=0; i<jsonActions.length(); i++){
                actions.add(this.actionJsonConverter.convertFromJson(jsonActions.getJSONObject(i)));
            }


            // List to sort by starting time
            HashMap<String, ArrayList<SpriteAction>> sortedActions = new HashMap<>();

            // Set starting coordinates on actions
            for(Sprite sprite:sprites){

                ArrayList<Long> times = new ArrayList<>();

                // Get every start time from action to a specific sprite
                for(SpriteAction action:actions){
                    if(sprite.getName().equals(action.getSprite())){
                        times.add(action.getStartTime());
                    }
                }
                // Sort by start time
                Collections.sort(times);

                // put sorted times with key
                sortedActions.put(sprite.getName(), new ArrayList<>());

                // Sort action by startTime
                for(SpriteAction action:actions){
                    for(int i=0; i<times.size(); i++){
                        if(times.get(i) == action.getStartTime()) {
                            sortedActions.get(sprite.getName()).add(action);
                        }
                    }
                }
            }

            // Assign coordinates to action
            for(String key:sortedActions.keySet()){
                ArrayList<SpriteAction> actionList = sortedActions.get(key);

                // Get actions for a sprite
                for(int i=0; i<actionList.size(); i++){

                    SpriteAction action = actionList.get(i);
                    SpriteAction lastAction = actionList.get(i-1);

                    // If first action
                    if(i == 0){

                        int initial_x = 0;
                        int initial_y = 0;

                        // get sprite initial coordinates
                        for(Sprite sprite:sprites){
                            if(sprite.getName().equals(key)){
                                initial_x = sprite.getX();
                                initial_y = sprite.getY();
                            }
                        }

                        // If visibility action the sprite doesn't move
                        if(action.getEndTime() == -1){
                            action.setCoordinate(initial_x, initial_y, initial_x, initial_y);
                        }
                        // If moving action, the sprite moves
                        else{
                            action.setCoordinate(initial_x, initial_y, action.getEndX(), action.getEndY());
                        }
                    }
                    // if last action, set endtime with total duration
                    else if(i == actionList.size()-1){

                        // If visibility action, the sprite doesn't move
                        if(action.getEndTime() == actionList.size()-1){
                            action.setCoordinate(lastAction.getEndX(), lastAction.getEndY(), lastAction.getEndX(), lastAction.getEndY());
                            lastAction.setEndTime(duration);
                        }
                        // If moving action, the sprite moves
                        else{
                            action.setCoordinate(lastAction.getEndX(), lastAction.getEndY(), action.getEndX(), action.getEndY());
                        }
                    }
                    // else set coordinates with the last action and set endtime for visibility action
                    else{

                        // If visibility action, the sprite doesn't move
                        if(action.getEndTime() == -1){
                            action.setCoordinate(lastAction.getEndX(), lastAction.getEndY(), lastAction.getEndX(), lastAction.getEndY());
                            lastAction.setEndTime(action.getStartTime());
                        }
                        // If moving action, the sprite moves
                        else{
                            action.setCoordinate(lastAction.getEndX(), lastAction.getEndY(), action.getEndX(), action.getEndY());
                        }
                    }
                }
            }

            returnedSequence = new SequenceImp(background, sprites, actions, duration);
        }
        else{
            returnedSequence = new SequenceImp(background, null, null, duration);
        }



        invariant();

        // Postcondition
        assert returnedSequence != null && returnedSequence.getBackground() != null: "Postcondition violated.";

        return returnedSequence;
    }
}
