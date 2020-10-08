package util.json;

import org.json.JSONObject;

/**
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 *
 * A generic converter which converts Json to objects and objects to json
 *
 * @param <T>: the type of object read
 */
public interface JsonConverter<T> {

    /**
     * Convert a object of type T to json object
     *
     * @pre object != null
     * @post result != null
     *
     * @param object: an object of T type
     * @return the json object describing the specified object
     */
    public JSONObject convertToJson(T object);

    /**
     * Convert a json object to object of type T
     *
     * @pre json != null
     * @post result != null
     *
     * @param json: a json object
     * @return the object described by the json object
     */
    public T convertFromJson(JSONObject json);
}
