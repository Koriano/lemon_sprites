package sprites.model;

import util.engine.SingleObjectHolder;

/**
 * A class that implements the {@link SingleObjectHolder} interface to hold a single object of a specified type
 *
 * @param <T>: the type of object to be held
 */
public class SingleObjectHolderImp<T> implements SingleObjectHolder<T> {

    /**
     * The single object saved
     */
    private T object;

    /**
     * Constructor of SingleObjectHolderImp (initializing the object to hold to null)
     */
    public SingleObjectHolderImp(){
        this.object = null;
    }

    /**
     * Constructor of SingleObjectHolderImp (initializing with given object)
     *
     * @param object: the object to hold
     */
    public SingleObjectHolderImp(T object){
        this.object = object;
    }

    /**
     * Remove the old instance and set the new instance
     *
     * @param object : the object to replace the instance
     */
    @Override
    public void setObject(T object) {
        // Replace by new object
        this.object = object;
    }

    /**
     * Get the instance of the object and remove it
     */
    @Override
    public T getObject() {

        // Get
        T obj = this.object;

        // Remove
        this.object = null;

        // Return
        return obj;
    }
}
