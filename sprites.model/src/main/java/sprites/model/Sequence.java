package sprites.model;

import gui.graphic.Image;
import gui.graphic.Snapshot;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 *
 * A Sequence is represented by a background image, a list of sprites and a list of actions.
 */
public interface Sequence {

    /**
     * Get the snapshot at a current time
     *
     * @pre millis >= 0 && millis <= this.duration
     * @post currentSnapshot != null
     *
     * @param millis: the current time to get the current snapshot
     *
     * @return the current snapshot
     */
    public Snapshot getCurrentSnapshot(long millis);


    /**
     * Get the background of the sequence
     *
     * @pre this.background != null && !"".equals(this.background)
     *
     * @return the background of the sequence
     */
    public Image getBackground();


    /**
     * Returns the total duration of the sequence
     *
     * @pre this.duration > 0
     *
     * @return the total duration of the sequence
     */
    public long getDuration();


    /**
     * Returns the list of the sprites composing the sequence
     *
     * @return the list of sprites
     */
    public ArrayList<Sprite> getSprites();


    /**
     * Returns the map of actions composing the sequence
     *
     * @return the map of actions
     */
    public HashMap<String,ArrayList<SpriteAction>> getActions();
}
