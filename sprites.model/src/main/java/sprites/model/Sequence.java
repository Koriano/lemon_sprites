package sprites.model;

import gui.graphic.Image;
import gui.graphic.Snapshot;

/**
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 *
 * A Sequence is represented by a background image, a list of sprites and a list of actions.
 */
public interface Sequence {

    /**
     * Updates every Sprite depending on the time passed
     *
     * @pre millis >= 0 && millis <= this.duration
     *
     * @param millis: the time at which update the sprites
     */
    public void updateSprites(long millis);

    /**
     * Get the snapshot at a current time
     *
     * @pre millis >= 0 && millis <= this.duration
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
}
