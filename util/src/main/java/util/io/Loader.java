package util.io;

import java.io.InputStream;

/**
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 *
 * A generic Loader of a generic type from an input stream
 *
 * @param <T>: the type of object to be loaded
 */
public interface Loader<T> {

    /**
     * Load object from an input stream
     *
     * @pre stream != null
     *
     * @param stream: the stream containing the file
     * @return the object loaded
     */
    public T load(InputStream stream);
}
