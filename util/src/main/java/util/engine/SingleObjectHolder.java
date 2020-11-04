package util.engine;

/**
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 *
 * A SingleObjectHolder that contains a single instance of an object
 */
public interface SingleObjectHolder<T> {

    /**
     * Remove the old instance and set the new instance
     *
     * @pre object != null
     *
     * @param object: the object to replace the instance
     */
    public void setObject(T object);

    /**
     * Get the instance of the object and remove it
     */
    public T getObject();
}
