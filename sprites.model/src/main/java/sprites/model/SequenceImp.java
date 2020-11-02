package sprites.model;

import gui.graphic.*;

import java.util.ArrayList;
import java.util.HashMap;

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
    private ArrayList<SpriteAction> actions;


    /**
     * The constructor of the class
     *
     * @param background
     * @param sprites
     * @param actions
     * @param duration
     */
    public SequenceImp(Image background, ArrayList<Sprite> sprites, ArrayList<SpriteAction> actions, long duration){

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
        ArrayList<SpriteAction> actionsList = this.actions;
        int lengthAction = actionsList.size();

        ArrayList<Sprite> spritesList = this.sprites;
        int lengthSprite = spritesList.size();


        // create an array of snapshot layer
        SnapshotLayer[] layers = new SnapshotLayer[lengthSprite+1];
        layers[0] = new SnapshotLayerImp(this.background, 0, 0);


        for (int k=1; k<lengthSprite+1; k++){
            // get the name of the sprite
            Sprite sprite = spritesList.get(k-1);
            String spriteName = sprite.getName();

            // search the sprites action
            for (int i=0; i<lengthAction; i++){

               SpriteAction action = actionsList.get(i);

                if (action.getSprite().equals(spriteName)) {
                    // search the good time interval

                    if (millis < action.getEndTime() && millis > action.getStartTime()) {
                        //update the sprite
                        sprite = action.updateSprite(sprite, millis);

                        // get the new coordinate
                        int X = sprite.getX();
                        int Y = sprite.getY();

                        // search the sprite image
                        Image image = spritesList.get(k-1).getCurrentImage(millis);

                        // create and add a snapshot layer
                        SnapshotLayer snapshotLayer = new SnapshotLayerImp(image, X, Y);
                        layers[i] = snapshotLayer;
                    }
                }
            }
        }

        // create a snapshot
        Snapshot currentSnapshot = new SnapshotImp(layers);

        return currentSnapshot;
    }



    /**
     * Get the background of the sequence
     *
     * @pre this.background != null && !"".equals(this.background)
     *
     * @return the background of the sequence
     */
    @Override
    public Image getBackground() {
        // pre condition
        assert this.background != null  : "Precondition violated";

        return this.background;
    }


    /**
     * Returns the total duration of the sequence
     *
     * @pre this.duration > 0
     *
     * @return the total duration of the sequence
     */
    @Override
    public long getDuration() {
        // pre condition
        assert this.duration > 0 : "Precondition violated";

        return this.duration;
    }




    /**
     * Returns the list of the sprites composing the sequence
     *
     * @return the list of sprites
     */
    @Override
    public ArrayList<Sprite> getSprites() {
        return this.sprites;
    }




    /**
     * Returns the list of actions composing the sequence
     *
     * @return the list of actions
     */
    @Override
    public ArrayList<SpriteAction> getActions() {
        return this.actions;
    }
}
