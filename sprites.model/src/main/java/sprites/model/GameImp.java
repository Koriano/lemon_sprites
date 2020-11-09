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
 * @inv this.playerSprite != null && this.view_width > 0 && this.sequences != null && this.sequences.size() > 0 && this.currentSequence > 0
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

        // Player starts at sequence 0
        this.currentSequence = 0;

        invariant();
    }

    /**
     * A method to get the current snapshot, depending on the current time
     *
     * @param millis the current time
     * @param directionAction the direction action of the player
     *
     * @return the current snapshot
     *
     * @pre millis >= 0 && directionAction != null
     */
    @Override
    public Snapshot getCurrentSnapshot(long millis, DirectionAction directionAction){

        // Precondition
        assert millis >= 0 && directionAction != null: "Precondition violated";

        // Start by updating the playerSprite
        this.updatePlayerSprite(directionAction);

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
     * Updates the player sprite
     *
     * @param directionAction the direction action containing the speed and the direction
     *
     * @pre directionAction != null
     */
    private void updatePlayerSprite(DirectionAction directionAction){

        String direction = directionAction.getDirection();
        int step = directionAction.getStep();

        int newY;
        int newX;

        switch (direction){
            case "up":

                newY = this.playerSprite.getY() - step;

                // If player goes too high, keep him in the background
                if(newY < 0){
                    this.playerSprite.setY(0);
                }
                else{
                    this.playerSprite.setY(newY);
                }
                break;

            case "down":

                newY = this.playerSprite.getY() + step;
                int sequenceHeight = this.sequences.get(this.currentSequence).getBackground().getHeight();

                // If player goes too low, keep him in the background
                if(newY > sequenceHeight){
                    this.playerSprite.setY(sequenceHeight);
                }
                else {
                    this.playerSprite.setY(newY);
                }
                break;

            case "left":

                newX = this.playerSprite.getX() - step;

                // If at the first sequence and goes too far left, keep player on background
                if(currentSequence == 0 && newX < 0){
                    this.playerSprite.setX(0);
                }
                // Else if not at the first sequence and goes too far left, change current sequence
                else if(newX < 0){
                    this.currentSequence--;
                    this.playerSprite.setY(this.sequences.get(this.currentSequence).getBackground().getWidth());
                }
                // Else move on the sequence
                else{
                    this.playerSprite.setX(newX);
                }
                break;

            case "right":

                newX = this.playerSprite.getX() + step;
                int sequenceWidth = this.sequences.get(this.currentSequence).getBackground().getWidth();

                // If at last sequence and geos too far right, keep player on background
                if(this.currentSequence == this.sequences.size()-1 && newX > sequenceWidth){
                    this.playerSprite.setX(sequenceWidth);
                }
                // Else if not at last sequence and goes too far right, change current sequence
                else if(newX > sequenceWidth){
                    this.currentSequence++;
                    this.playerSprite.setX(0);
                }
                // Else move on the background
                else{
                    this.playerSprite.setX(newX);
                }
                break;
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
        assert this.playerSprite != null && this.view_width > 0 && this.sequences != null && this.sequences.size() > 0 && this.currentSequence > 0: "Class invariant violated";
    }
}
