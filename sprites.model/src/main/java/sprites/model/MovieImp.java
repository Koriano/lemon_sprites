package sprites.model;

import gui.graphic.Snapshot;
import java.util.ArrayList;


/**
 * @author Margaux SCHNELZAUER
 *
 * Movie class implements the movie interface
 *
 * @inv this.sequences.size() > 0
 * @see sprites.model.Movie
 */
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
     * @param millis: the current time
     *
     * @return the current snapshot, or null if millis is greater than the movie's length
     */
    @Override
    public Snapshot getCurrentSnapshot(long millis) {

        ArrayList<Sequence> seqList = this.sequences;
        int length = seqList.size();
        long totalDuration = 0;

        // total duration calculation
        for (int i=0; i<length; i++){
            // total duration calculation
            totalDuration += seqList.get(i).getDuration();
        }


        // Check if the movie is ended
        if (millis > totalDuration) {
            return null;
        }


        // search the current sequence
        long currentDuration = 0;
        long newCurrentDuration;
        Snapshot currentSnapshot = null;


        for (int i=0; i<length; i++){

            Sequence sequence = seqList.get(i);
            newCurrentDuration = currentDuration + sequence.getDuration();

            // check if the time is into the start and the end of the sequences(i)
            if (millis >= currentDuration && millis <= newCurrentDuration){
                // get the current snapshot from the current sequence
                currentSnapshot = sequence.getCurrentSnapshot(millis - currentDuration);
            }

            // new duration calculation
            currentDuration = newCurrentDuration;
        }
        // check the invariant condition
        invariant();

        return currentSnapshot;
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
        // pre condition
        assert this.sequences != null && this.sequences.size() > 0: "Precondition violated";
        invariant();

        return this.sequences;
    }

    /**
     * Check if the invariant condition is verified
     */
    private void invariant() {
        assert this.sequences.size() > 0 : "Invariant violated";
    }
}
