package sprites.model.json;

import gui.graphic.Image;
import org.json.JSONArray;
import org.json.JSONObject;
import sprites.model.Game;
import sprites.model.GameImp;
import sprites.model.Sequence;
import util.json.JsonConverter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class that implements the {@link JsonConverter} interface
 *
 * @author Alexandre HAMON
 *
 * @inv this.sequenceJsonConverter != null && spriteJsonConverter != null
 */
public class GameJsonConverter implements JsonConverter<Game> {

    /**
     * The sequence to/from json converter
     */
    private SequenceJsonConverter sequenceJsonConverter;

    /**
     * The sprite to/from json converter
     */
    private SpriteJsonConverter spriteJsonConverter;

    /**
     * The constructor of the Game json converter class
     *
     * @param images the loaded images
     *
     * @pre images != null
     */
    public GameJsonConverter(HashMap<String, Image> images){

        assert images != null: "Precondition violated";

        this.sequenceJsonConverter = new SequenceJsonConverter(images);
        this.spriteJsonConverter = new SpriteJsonConverter(images);
    }

    /**
     * Convert a object of type Game to json object
     *
     * @param object : an object of Game type
     * @return the json object describing the game object
     *
     * @pre object != null && object.getPlayerSprite() != null && object.getWidth() > 0 && object.getSequences() != null && object.getSequences().size() > 0
     * @post result != null && result.has("player") && result.has("width") && result.has("sequences")
     */
    @Override
    public JSONObject convertToJson(Game object) {

        // Precondition
        assert object != null && object.getPlayerSprite() != null && object.getWidth() > 0 && object.getSequences() != null && object.getSequences().size() > 0: "Precondition violated";

        JSONObject returnedJson = new JSONObject();

        // Transform properties into json
        returnedJson.put("player", this.spriteJsonConverter.convertToJson(object.getPlayerSprite()));
        returnedJson.put("width", object.getWidth());

        // Iterate on sequences
        JSONArray jsonSequences = new JSONArray();

        for(Sequence seq:object.getSequences()){
            jsonSequences.put(this.sequenceJsonConverter.convertToJson(seq));
        }

        returnedJson.put("sequences", jsonSequences);

        invariant();

        assert returnedJson != null && returnedJson.has("player") && returnedJson.has("width") && returnedJson.has("sequences"): "Postcondition violated";

        return returnedJson;
    }

    /**
     * Convert a json object to object of type Game
     *
     * @param json : a json object
     * @return the Game object described by the json
     *
     * @pre json != null && json.has("player") && json.has("width") && json.has("sequences")
     * @post result != null && object.getPlayerSprite() != null && object.getWidth() > 0 && object.getSequences() != null && object.getSequences().size() > 0
     */
    @Override
    public Game convertFromJson(JSONObject json) {

        assert json != null && json.has("player") && json.has("width") && json.has("sequences"): "Precondition violated";


        // Iterate over sequences
        JSONArray jsonSequences = json.getJSONArray("sequences");
        ArrayList<Sequence> sequences = new ArrayList<>();

        for (int i=0; i<jsonSequences.length(); i++){
            sequences.add(this.sequenceJsonConverter.convertFromJson(jsonSequences.getJSONObject(i)));
        }

        Game returnedGame = new GameImp(this.spriteJsonConverter.convertFromJson(json.getJSONObject("player")), sequences, json.getInt("width"));

        invariant();

        assert returnedGame != null && returnedGame.getPlayerSprite() != null && returnedGame.getWidth() > 0 && returnedGame.getSequences() != null && returnedGame.getSequences().size() > 0: "Postcondition violated";

        return returnedGame;
    }

    /**
     * Invariant of the class
     */
    private void invariant(){
        assert this.sequenceJsonConverter != null && spriteJsonConverter != null: "Precondition violated";
    }
}
