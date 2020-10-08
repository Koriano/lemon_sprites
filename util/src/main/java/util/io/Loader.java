package util.io;

/**
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 *
 * A generic Loader of a generic type from an input stream
 *
 * @param <T>: the type of object to be loaded
 */
public interface Loader<T> {

    /**
     * Load object from a file
     *
     * @pre path != null && !"".equals(path)
     *
     * @param path: the file path
     * @return the object loaded
     */
    public T load(String path);
}
