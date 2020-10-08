package util.io;

import java.util.ArrayList;

/**
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 *
 * A generic Loader of a generic type from an input stream
 */
public interface ZipLoader {

    /**
     * Load objects of a specific type from a zip file
     *
     * @pre path != null && !"".equals(path) && type != null
     *
     * @param path: the file
     * @param type: the type of object returned
     * @param <T>: the type of object returned
     * @return a list containing every file found in the zip of the specified type
     */
    public <T> ArrayList<T> loadFromZip(String path, T type);
}
