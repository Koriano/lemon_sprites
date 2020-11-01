package sprites.model;

import gui.graphic.Image;
import gui.graphic.Snapshot;

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
     * Updates every Sprite depending on the time passed
     *
     * @pre millis >= 0 && millis <= this.duration
     *
     * @param millis: the time at which update the sprites
     */
    @Override
    public void updateSprites(long millis) {

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
    public String getBackground() {
        return null;
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
        return 0;
    }




    /**
     * Returns the list of the sprites composing the sequence
     *
     * @return the list of sprites
     */
    @Override
    public ArrayList<Sprite> getSprites() {
        return null;
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
