package sprites.model;

import gui.graphic.Image;
import gui.graphic.Snapshot;

/**
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 *
 * A Scene is described by a list of snapshots depending on the time
 *
 * @inv this.sprites.length > 0 && this.background != null
 */
public interface Scene {

    /**
     * Method to get the snapshot at the time t=millis
     *
     * @pre millis >= 0
     * @post result != null
     *
     * @param millis: the time at which you want the sprite image (in milliseconds)
     * @return the Snapshot corresponding at the time millis
     */
    public Snapshot getCurrentSnapshot(long millis);

    /**
     * Get the background of the scene
     *
     * @post result != null
     *
     * @return the Image background of the scene
     */
    public Image getBackground();
}
