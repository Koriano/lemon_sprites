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
     *
     * @return the current snapshot
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

        // pre condition
        assert millis >= 0 && millis <= totalDuration : "Precondition violated";


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
                currentSnapshot = sequence.getCurrentSnapshot(millis);
            }

            // new duration calculation
            currentDuration = newCurrentDuration;

        }


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

        return this.sequences;
    }
}
