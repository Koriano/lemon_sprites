package sprites.model;

import gui.graphic.Snapshot;

import java.util.ArrayList;

/**
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 *
 * A movie is composed of a list of sequences
 */
public interface Movie {

    /**
     * Get the snapshot at the current time passed
     *
     * @pre millis >= 0
     *
     * @param millis: the current time
     *
     * @return the current snapshot
     */
    public Snapshot getCurrentSnapshot(long millis);

    /**
     * Get the sequences composing the movie
     *
     * @pre this.sequences != null && this.sequences.size() > 0
     *
     * @return the sequences composing the movie
     */
    public ArrayList<Sequence> getSequences();
}
