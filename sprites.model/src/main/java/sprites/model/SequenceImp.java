package sprites.model;

import gui.graphic.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Margaux SCHNELZAUER
 *
 * A class to implement Sequence interface
 *
 * @see sprites.model.Sequence
 */
public class SequenceImp implements Sequence{

    /**
     * The sequence duration
     */
    private long duration;


    /**
     * The sequence background
     */
    private Image background;


    /**
     * A list of sprites composing the sequence
     */
    private ArrayList<Sprite> sprites;


    /**
     * A list of actions related to the sprites in the sequence
     */
    private HashMap<String,ArrayList<SpriteAction>> actions;


    /**
     * The constructor of the class
     *
     * @param background : the background of the sequence
     * @param sprites : the list of the sprites composing the sequence
     * @param actions : the liste of the actions related to the sprites in the sequence
     * @param duration : the duration of the sequence
     */
    public SequenceImp(Image background, ArrayList<Sprite> sprites, HashMap<String,ArrayList<SpriteAction>> actions, long duration){

        this.actions = actions;
        this.background = background;
        this.sprites = sprites;
        this.duration = duration;

    }



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
    @Override
    public Snapshot getCurrentSnapshot(long millis) {
        // pre condition
        assert millis >= 0 : "Precondition violated";

        // get the sprites and actions array
        HashMap<String,ArrayList<SpriteAction>> actionsMap = this.actions;

        ArrayList<Sprite> spritesList = this.sprites;
        int lengthSprite = spritesList.size();


        // create an array of snapshot layer
        ArrayList<SnapshotLayer> layers = new ArrayList<>();
        layers.add(new SnapshotLayerImp(this.background, 0, 0));


        // For each sprites
        for (int k=0; k<lengthSprite; k++) {

            // get the name of the sprite
            Sprite sprite = spritesList.get(k);
            String spriteName = sprite.getName();

            ArrayList<SpriteAction> actionsList = actionsMap.get(spriteName);

            boolean spriteUpdated = false;
            int i = 0;

            // search the sprites action
            while (!spriteUpdated && i < actionsList.size()) {
                SpriteAction action = actionsList.get(i);

                // If the start time and end time are equals.
                if (action.getStartTime() == action.getEndTime() && !action.isConsumed() && millis >= action.getStartTime()) {
                    action.setConsumed(true);
                    sprite = action.updateSprite(sprite, action.getStartTime());
                    spriteUpdated = true;

                } else if (action.getStartTime() != action.getEndTime() && millis > action.getStartTime()) {

                    if (!action.isConsumed() && (i == 0 || i == actionsList.size() - 1)) {
                        if (i == 0 && millis >= actionsList.get(i + 1).getStartTime()) {
                            action.setConsumed(true);
                            action = actionsList.get(i + 1);
                        }

                        //update the sprite
                        sprite = action.updateSprite(sprite, millis);
                        spriteUpdated = true;

                    } else if (!action.isConsumed() && millis < action.getEndTime() && millis < actionsList.get(i + 1).getStartTime()) {
                        sprite = action.updateSprite(sprite, millis);
                        spriteUpdated = true;

                    }// If the current time is greater than the next action
                    else if (!action.isConsumed() && millis >= actionsList.get(i + 1).getStartTime()) {
                        action.setConsumed(true);
                        action = actionsList.get(i + 1);
                        sprite = action.updateSprite(sprite, millis);
                        spriteUpdated = true;
                    }
                }

                if (spriteUpdated) {
                    // get the new coordinate
                    int X = sprite.getX();
                    int Y = sprite.getY();

                    // get the sprite image
                    Image image = sprite.getCurrentImage(millis);

                    // check if the action is visible
                    if (action.isVisible()){
                        // create and add a snapshot layer
                        SnapshotLayer snapshotLayer = new SnapshotLayerImp(image, X, Y);
                        layers.add(snapshotLayer);
                    }
                }
                i++;
            }
        }

        // create a snapshot
        SnapshotLayerImp[] snapLayer = new SnapshotLayerImp[layers.size()];
        Snapshot currentSnapshot = new SnapshotImp(layers.toArray(snapLayer));

        invariant();
        assert currentSnapshot != null : "Post condition";

        return currentSnapshot;
    }



    /**
     * Get the background of the sequence
     *
     * @return the background of the sequence
     */
    @Override
    public Image getBackground() {
        invariant();
        return this.background;
    }


    /**
     * Returns the total duration of the sequence
     *
     * @return the total duration of the sequence
     */
    @Override
    public long getDuration() {
        invariant();
        return this.duration;
    }


    /**
     * Returns the list of the sprites composing the sequence
     *
     * @return the list of sprites
     */
    @Override
    public ArrayList<Sprite> getSprites() {
        invariant();
        return this.sprites;
    }


    /**
     * Returns the map of actions composing the sequence
     *
     * @return the map of actions
     */
    @Override
    public HashMap<String,ArrayList<SpriteAction>> getActions() {
        invariant();
        return this.actions;
    }


    /**
     * Check if the invariant condition is verified
     */
    private void invariant(){
        assert this.background != null : "Invariant violated";
        assert ((this.sprites.size() > 0 && this.actions.size() > 0) || ( this.sprites.size() == 0 && this.actions.size() == 0)) : "Invariant violated" ;
        assert this.duration > 0 : "invariant violated";
    }
}
