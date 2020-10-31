package sprites.model;

import gui.graphic.Snapshot;

/**
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 *
 * A movie is composed of a list of sequences
 */
public interface Movie {

    /**
     * Get the snapshot at the current time passed
     *
     * @pre millis >= 0 && millis <= math.sum(this.sequences.getDuration())
     *
     * @param millis: the current time
     * @return
     */
    public Snapshot getCurrentSnapshot(long millis);
}
