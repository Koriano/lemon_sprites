package util.io;

import org.json.JSONObject;
import util.json.JsonConverter;
import util.json.JsonLoaderImp;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 *
 * A generic Loader of a generic type from an input stream containing a zip file
 */
public class ZipLoaderImp<T,R> implements ZipLoader<T> {

    /**
     * The jsonConverter which will parse the JSON file contained in the zip
     */
    private JsonConverter<T> jsonConverter;

    /**
     * The Loader that will load the data from the zip (such as a JSON file or images)
     */
    private Loader<R> resourceLoader;

    /**
     * the ArrayList that will contain the loaded resources, such as images
     */
    private ArrayList<R> resourcesList;

    /**
     * The constructor of the ZipLoaderImp class, taking a jsonConverter as an argument
     *
     * @pre jsonConverter != null && resourceLoader != null && resourcesList != null
     *
     * @param jsonConverter : the jsonConverter which will parse the JSON file contained in the zip
     * @param resourceLoader : the Loader that will load the data from the zip (such as a JSON file or images)
     * @param resourcesList : the ArrayList that will contain the loaded resources, such as images
     */
    public ZipLoaderImp(JsonConverter<T> jsonConverter, Loader<R> resourceLoader, ArrayList<R> resourcesList){

        assert jsonConverter != null && resourceLoader != null && resourcesList != null:
                "ZipLoaderImp#constructor: precondition violated";

        this.jsonConverter = jsonConverter;
        this.resourceLoader = resourceLoader;
        this.resourcesList = resourcesList;

    }

    /**
     * Load object from an input stream containing a zip file
     *
     * @pre stream != null
     *
     * @param stream: the stream containing the zip file
     * @return the T object loaded
     */
    public T load(InputStream stream){

        // Precondition
        assert stream != null: "ZipLoader#load: precondition violated";

        // Casting stream into ZipInputStream to use specific methods
        ZipInputStream zis = new ZipInputStream(stream);

        // Initializing entry convertion
        JsonLoaderImp json_loader = new JsonLoaderImp();
        ZipEntry entry;
        String ext;
        JSONObject jsonContent = new JSONObject();

        // Iterating over zip entries to load json and resources
        do {
            try {
                // Get the next zip entry
                entry = zis.getNextEntry();

                // if it's not a directory
                if(!entry.isDirectory()){

                    // get its extension
                    ext = this.extension(entry);

                    // If json file, load it with jsn loader, else it is a resource (load it with resource loader)
                    if ("json".equals(ext)){
                        jsonContent = json_loader.load(zis);
                    } else {
                        this.resourcesList.add(this.resourceLoader.load(zis));//TODO ajouter le nom à l'image chargée en le récupérant avec entry
                    }
                }

            }catch (Exception e){
                entry = null;
            }
        }while (entry != null);
        // Create a new object from json file
        return this.jsonConverter.convertFromJson(jsonContent);
    }


    /**
     * A private method used to return the extension of the ZipEntry
     *
     * @pre entry != null
     *
     * @param entry: the zip entry to check the extension
     * @return the extension of the file
     */
    private String extension(ZipEntry entry){
        assert entry != null;
        String entryName = entry.getName();

        // Split the name with slashes : /foo/bar/file.txt -> ["foo", "bar", "file.txt"]
        String[] slash_split = entryName.split("/");
        // Split the file name with dot : file.txt -> ["file", "txt"]
        String[] dot_split = slash_split[slash_split.length-1].split("\\.");

        // Return the extension
        return dot_split[dot_split.length-1];
    }
}
