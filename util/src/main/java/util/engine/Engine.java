package util.engine;

/**
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 *
 * An engine to get data from a shared structure and update a shared architecture
 */
public interface Engine {

    /**
     * To get shared information from model and update an other (or the same) model
     */
    public void update();
}
