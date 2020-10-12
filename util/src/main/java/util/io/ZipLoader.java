package util.io;

import java.io.InputStream;

/**
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 *
 * A generic Loader of a generic type from an input stream containing a zip file
 */
public interface ZipLoader<T,R> extends Loader<T> {

    /**
     * Load object from an input stream containing a zip file
     *
     * @pre stream != null
     *
     * @param stream: the stream containing the zip file
     * @return the T object loaded
     */
    public T load(InputStream stream);
}
