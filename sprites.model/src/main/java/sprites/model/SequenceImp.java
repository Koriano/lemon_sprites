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
    private HashMap<String, SpriteAction> actions;


    /**
     * The constructor of the class
     *
     * @param background
     * @param sprites
     * @param actions
     * @param duration
     */
    public SequenceImp(Image background, ArrayList<Sprite> sprites, HashMap<String, SpriteAction> actions, long duration){

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

        

        return null;
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
        return null;
    }
}
