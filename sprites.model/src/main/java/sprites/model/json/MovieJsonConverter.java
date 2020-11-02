package sprites.model.json;

import gui.graphic.Image;
import org.json.JSONArray;
import org.json.JSONObject;
import sprites.model.Movie;
import sprites.model.MovieImp;
import sprites.model.Sequence;
import util.json.JsonConverter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Alexandre HAMON
 *
 * A class which implements the {@link JsonConverter} to convert json from/to Movie objects
 *
 * @inv this.sequenceJsonConverter != null
 */
public class MovieJsonConverter implements JsonConverter<Movie> {

    /**
     * Json converter to convert sequence into json
     */
    private SequenceJsonConverter sequenceJsonConverter;

    /**
     * Constructor of the MovieJsonConverter class
     *
     * @param images: the list of images of the zip file
     *
     * @pre images != null
     */
    public MovieJsonConverter(HashMap<String, Image> images){
        // Precondition
        assert images != null: "Precondition violated";

        this.sequenceJsonConverter = new SequenceJsonConverter(images);

        invariant();
    }

    /**
     * Invariant of the class
     */
    private void invariant(){
        assert this.sequenceJsonConverter != null: "Invariant violated";
    }

    /**
     * Convert a object of type Movie to json object
     *
     * @param object : an object of Movie type
     *
     * @return the json object describing the specified Movie
     *
     * @pre object != null && object.getSequences() != null && object.getSequences().size() > 0
     * @post result != null
     */
    @Override
    public JSONObject convertToJson(Movie object) {

        // Precondition
        assert object != null && object.getSequences() != null && object.getSequences().size() > 0: "Precondition violated";

        // Returned object
        JSONObject returnedJson = new JSONObject();

        // Get sequences from movie to convert into Json
        JSONArray jsonSequences = new JSONArray();

        for(Sequence seq:object.getSequences()){
            jsonSequences.put(this.sequenceJsonConverter.convertToJson(seq));
        }

        returnedJson.put("sequences", jsonSequences);

        invariant();

        // Postcondition
        assert returnedJson.has("sequences"): "Postcondition violated";

        return returnedJson;
    }

    /**
     * Convert a json object to object of type Movie
     *
     * @param json : a json object
     *
     * @return the Movie described by the json object
     *
     * @pre json != null && json.has("sequences")
     * @post result != null && result.getSequences().size() > 0
     */
    @Override
    public Movie convertFromJson(JSONObject json) {

        // Precondition
        assert json != null && json.has("sequences"): "Precondition violated";

        // Returned object
        Movie returnedMovie = null;

        // Get sequence from json and convert into sequence objects
        JSONArray jsonSequences = json.getJSONArray("sequences");
        ArrayList<Sequence> sequences = new ArrayList<>();

        for(int i=0; i<jsonSequences.length(); i++){
            sequences.add(this.sequenceJsonConverter.convertFromJson(jsonSequences.getJSONObject(i)));
        }

        returnedMovie = new MovieImp(sequences);

        invariant();

        // Postcondition
        assert returnedMovie != null: "Postcondition violated";

        return returnedMovie;
    }
}
