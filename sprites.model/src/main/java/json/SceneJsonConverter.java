package json;

import gui.graphic.Image;
import org.json.JSONArray;
import org.json.JSONObject;
import sprites.model.Scene;
import sprites.model.SceneImp;
import sprites.model.Sprite;
import util.json.JsonConverter;

import java.util.ArrayList;

/**
 * @author Alexandre HAMON
 *
 * A class that implements the JsonConverter interface to convert Json to scene and scene to json
 * @see util.json.JsonConverter
 *
 * @inv this.spriteConverter != null
 */
public class SceneJsonConverter implements JsonConverter<Scene> {

    /**
     * The sprite converter containing loaded images
     */
    private SpriteJsonConverter spriteConverter;

    /**
     * A constructor for the SceneJsonConverter, instanciated with a SpriteJsonConverter
     *
     * @param spriteConverter: the sprite converter conatining already loaded images
     *
     * @pre spriteConverter != null
     */
    public SceneJsonConverter(SpriteJsonConverter spriteConverter){

        assert spriteConverter != null:
                "SceneJsonConverter#constructor : precondition violated";

        this.spriteConverter = spriteConverter;
    }

    /**
     * Convert a object of type scene to json object
     *
     * @param scene : an object of scene type
     * @return the json object describing the specified scene
     *
     * @pre scene != null && scene.getBackground() != null
     * @post result != null && result.has("background")
     */
    @Override
    public JSONObject convertToJson(Scene scene) {

        assert scene != null && scene.getBackground() != null:
                "SceneJsonConverter#convertToJson : precondition violated";

        // Instanciate return json
        JSONObject jsonScene = new JSONObject();
        JSONArray jsonSprites = new JSONArray();

        // get sprites composing the scene
        ArrayList<Sprite> sprites = scene.getSprites();

        for(Sprite sprite: sprites){
            jsonSprites.put(this.spriteConverter.convertToJson(sprite));
        }

        jsonScene.put("background", scene.getBackground().getName());
        jsonScene.put("sprites", jsonSprites);

        assert jsonScene != null && jsonScene.has("background"):
                "SceneJsonConverter#convertToJson: postcondition violated";

        return jsonScene;
    }

    /**
     * Convert a json object to object of type scene
     *
     * @param jsonScene : a scene desribed in json
     * @return the scene described by the json object
     *
     * @pre jsonScene != null && jsonScene.has("background")
     * @post result != null && result.getBackground() != null
     */
    @Override
    public Scene convertFromJson(JSONObject jsonScene) {

        // Precondition
        assert jsonScene != null && jsonScene.has("background"):
                "SceneJsonConverter#convertFromJson: precondition violated";

        // Get background
        String backgroundName = jsonScene.getString("background");
        Image background = this.spriteConverter.getImage(backgroundName);

        // Get sprites
        JSONArray jsonSprites = jsonScene.getJSONArray("sprites");
        ArrayList<Sprite> sprites = new ArrayList<>();

        for(int i=0; i< jsonSprites.length(); i++){
            sprites.add(this.spriteConverter.convertFromJson(jsonSprites.getJSONObject(i)));
        }

        Scene scene = new SceneImp(sprites, background);
        // Postcondition
        assert scene != null && scene.getBackground() != null:
                "SceneJsonConverter#convertFromJson: postcondition violated";

        // Create scene and return it
        return scene;
    }
}
