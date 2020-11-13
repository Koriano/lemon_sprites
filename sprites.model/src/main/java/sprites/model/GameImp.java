package sprites.model;

import gui.graphic.Snapshot;
import gui.graphic.SnapshotImp;
import gui.graphic.SnapshotLayer;
import gui.graphic.SnapshotLayerImp;

import java.util.ArrayList;

/**
 * A class which implements the {@link Game} interface.
 *
 * @author Alexandre HAMON
 *
 * @inv this.playerSprite != null && this.view_width > 0 && this.sequences != null && this.sequences.size() > 0 && this.currentSequence >= 0
 */
public class GameImp implements Game{

    /**
     * The sprite that the player controls
     */
    private Sprite playerSprite;

    /**
     * The view width
     */
    private int view_width;

    /**
     * The list of sequences of the game
     */
    private ArrayList<Sequence> sequences;

    /**
     * The current sequence of the game
     */
    private int currentSequence;

    /**
     * The action of the player sprite
     */
    private DirectionAction directionAction;

    /**
     * Constructor of the GameImp class
     *
     * @param playerSprite the sprite controlled by the player
     * @param sequences the list of sequences in the game
     * @param width the view width
     *
     * @pre playerSprite != null && sequences != null && sequences.size() > 0 && view_width > 0
     */
    public GameImp(Sprite playerSprite, ArrayList<Sequence> sequences, int width){
        assert playerSprite != null && sequences != null && sequences.size() > 0 && width > 0: "Precondition violated";

        this.playerSprite = playerSprite;
        this.sequences = sequences;
        this.view_width = width;
        this.directionAction = new DirectionAction(null, 0, 0);

        // Player starts at sequence 0
        this.currentSequence = 0;

        invariant();
    }

    /**
     * A method to get the current snapshot, depending on the current time
     *
     * @param millis the current time
     *
     * @return the current snapshot
     *
     * @pre millis >= 0
     */
    @Override
    public Snapshot getCurrentSnapshot(long millis){

        // Precondition
        assert millis >= 0: "Precondition violated";

        // Start by updating the playerSprite
        this.updatePlayerSprite(millis);

        // Get current sequence
        Sequence sequence = this.sequences.get(this.currentSequence);

        // Get snapshot from the current sequence
        long duration = sequence.getDuration();
        Snapshot snapshot = sequence.getCurrentSnapshot(millis%duration);

        // Calculate the difference depending on the player position
        int playerWidth = this.playerSprite.getCurrentImage(millis).getWidth();
        int diff = this.playerSprite.getX() + playerWidth/2 - this.view_width/2;

        if(diff < 0){
            diff = 0;
        }
        else if(diff > sequence.getBackground().getWidth() - this.view_width){
            diff = sequence.getBackground().getWidth() - this.view_width;
        }

        // Add player sprite to snapshot layers and apply diff to every layer
        SnapshotLayer[] layers = snapshot.getSnapshotLayers();
        SnapshotLayer[] newLayers = new SnapshotLayer[layers.length+1];

        for (int i=0; i<layers.length; i++){
            newLayers[i] = new SnapshotLayerImp(layers[i].getImage(), layers[i].getX() - diff, layers[i].getY());
        }

        newLayers[newLayers.length-1] = new SnapshotLayerImp(this.playerSprite.getCurrentImage(millis), this.playerSprite.getX() - diff, this.playerSprite.getY());

        return new SnapshotImp(newLayers);
    }


    /**
     * Defines the action to be applied to the player sprite on the getCurrentSnapshot calls.
     * @param dirAction the action to be applied to the player sprite on the getCurrentSnapshot calls.
     */
    public void setPlayerAction(DirectionAction dirAction) {
        assert dirAction != null: "Precondition violated";

        this.directionAction = dirAction;
    }


    /**
     * Updates the player sprite
     * @param millis the current time
     * @pre millis >= 0
     */
    private void updatePlayerSprite(long millis) {
        assert millis >= 0: "Precondition violated";

        // Apply the action to the player sprite
        this.playerSprite = this.directionAction.updateSprite(this.playerSprite, millis);
        int backgroundWidthLimit = this.sequences.get(this.currentSequence).getBackground().getWidth() - this.playerSprite.getCurrentImage(millis).getWidth();

        // Check if the player sprite does not go out of the view vertical boundaries
        if (this.playerSprite.getY() < 0) {
            this.playerSprite.setY(0);

        } else {
            correctPlayerVerticalPosition(millis);
        }

        // Check if the player sprite does not go out of the view horizontal boundaries
        if (this.playerSprite.getX() < 0) {
            if (this.currentSequence == 0) {
                this.playerSprite.setX(0);

            } else {
                // Get the width of the previous sequence background
                backgroundWidthLimit = this.sequences.get(--this.currentSequence).getBackground().getWidth() - this.playerSprite.getCurrentImage(millis).getWidth();
                this.playerSprite.setX(backgroundWidthLimit);
                correctPlayerVerticalPosition(millis);
            }
        } else if (this.playerSprite.getX() > backgroundWidthLimit) {
            if (this.currentSequence == this.sequences.size()-1) {
                this.playerSprite.setX(backgroundWidthLimit);

            } else {
                this.currentSequence++;
                this.playerSprite.setX(0);
                correctPlayerVerticalPosition(millis);
            }
        }
    }

    /**
     * Checks whether the player is not out of the background height after a change of sequence.
     * @param millis the current time.
     */
    private void correctPlayerVerticalPosition(long millis) {
        int backgroundHeightLimit = this.sequences.get(this.currentSequence).getBackground().getHeight() - this.playerSprite.getCurrentImage(millis).getHeight();

        if (this.playerSprite.getY() > backgroundHeightLimit) {
            this.playerSprite.setY(backgroundHeightLimit);
        }
    }

    /**
     * A method to set the player sprite
     *
     * @param playerSprite : the new player Sprite
     * @pre playerSprite != null
     */
    @Override
    public void setPlayerSprite(Sprite playerSprite) {
        assert playerSprite != null: "Precondition violated";

        this.playerSprite = playerSprite;

        invariant();
    }

    /**
     * A method to set the view width
     *
     * @param width : the view width
     * @pre width > 0
     */
    @Override
    public void setWidth(int width) {
        assert width > 0: "Precondition violated";

        this.view_width = width;

        invariant();
    }

    /**
     * A method to set the list of sequences of the game
     *
     * @param sequences : the list of the sequences of the game
     * @pre sequences != null && sequences.size() > 0
     */
    @Override
    public void setSequences(ArrayList<Sequence> sequences) {
        assert sequences != null && sequences.size() > 0: "Precondition violated";

        this.sequences = sequences;

        invariant();
    }

    /**
     * Returns the player sprite
     *
     * @return the player sprite
     */
    @Override
    public Sprite getPlayerSprite() {
        invariant();
        return this.playerSprite;
    }

    /**
     * Return the width of the view
     *
     * @return the width of the view
     */
    @Override
    public int getWidth() {
        invariant();
        return this.view_width;
    }

    /**
     * Return the list of the sequences of the game
     *
     * @return the list of the sequences of the game
     */
    @Override
    public ArrayList<Sequence> getSequences() {
        invariant();
        return this.sequences;
    }

    /**
     * Invariant of the class
     */
    private void invariant(){
        assert this.playerSprite != null && this.view_width > 0 && this.sequences != null && this.sequences.size() > 0 && this.currentSequence >= 0: "Class invariant violated";
    }
}
