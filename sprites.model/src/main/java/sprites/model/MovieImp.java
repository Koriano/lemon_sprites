package sprites.model;

import gui.graphic.Snapshot;

import java.util.ArrayList;

public class MovieImp implements Movie{

    /**
     * The sequences composing the movie
     */
    private ArrayList<Sequence> sequences;


    /**
     * The constructor of the class
     *
     * @param sequences
     */
    public MovieImp(ArrayList<Sequence> sequences){

        this.sequences = sequences;
    }


    /**
     * Get the snapshot at the current time passed
     *
     * @pre millis >= 0 && millis <= math.sum(this.sequences.getDuration())
     *
     * @param millis: the current time
     * @return
     */
    @Override
    public Snapshot getCurrentSnapshot(long millis) {
        return null;
    }


    /**
     * Get the sequences composing the movie
     *
     * @pre this.sequences != null && this.sequences.size() > 0
     *
     * @return the sequences composing the movie
     */
    @Override
    public ArrayList<Sequence> getSequences() {
        return null;
    }
}
