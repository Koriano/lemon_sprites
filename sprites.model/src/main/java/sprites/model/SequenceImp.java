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


        for (int k=0; k<lengthSprite; k++){
            // get the name of the sprite
            String spriteName = spritesList.get(k).getName();

            // search the sprites action
            for (int i=0; i<lengthAction; i++){

               SpriteAction action = actionsList.get(i);

                if (action.getSprite().equals(spriteName)) {
                    // search the good time interval
                    if (millis < action.getEndTime() && millis > action.getStartTime()) {

                        int previousX;
                        int previousY;

                        // layers parameters
                        int X;
                        int Y;
                        Image image;

                        //search the previous X and Y
                        // the action is the first of the list
                        if (i == 0) {
                            previousX = 0;
                            previousY = 0;
                        }

                        else {
                            SpriteAction previouscAction = actionsList.get(i - 1);

                            // check if the previous action is for the same sprite
                            if (action.getSprite().equals(previouscAction.getSprite())) {
                                // check if the action have a X and Y coordinate
                                if (previouscAction.getEndX() != -1 && previouscAction.getEndY() != -1) {
                                    previousX = previouscAction.getEndX();
                                    previousY = previouscAction.getEndY();
                                }

                                else {
                                    previousX = 0;
                                    previousY = 0;
                                }

                            }

                            else {
                                previousX = 0;
                                previousY = 0;
                            }
                        }

                        // current coordinate calculation
                        int deltaX = action.getEndX() - previousX;
                        int deltaY = action.getEndY() - previousY;
                        long deltaT = action.getEndTime() - action.getStartTime();

                        X = (int) ((deltaX / deltaT) * millis + previousX);
                        Y = (int) ((deltaY / deltaT) * millis + previousY);


                        // search the sprite image
                        image = spritesList.get(k).getCurrentImage(millis);

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
