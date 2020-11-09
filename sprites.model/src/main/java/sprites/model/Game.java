package sprites.model;

import gui.graphic.Snapshot;

import java.util.ArrayList;

/**
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 *
 * An interface that defines the game, with a specific player sprites, and a list of sequences.
 */
public interface Game {

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
    public Snapshot getCurrentSnapshot(long millis, DirectionAction directionAction);

    /**
     * A method to set the player sprite
     *
     * @param playerSprite: the new player Sprite
     *
     * @pre playerSprite != null
     */
    public void setPlayerSprite(Sprite playerSprite);

    /**
     * A method to set the view width
     *
     * @param width: the view width
     *
     * @pre width > 0
     */
    public void setWidth(int width);

    /**
     * A method to set the list of sequences of the game
     *
     * @param sequences: the list of the sequences of the game
     *
     * @pre sequences != null && sequences.size() > 0
     */
    public void setSequences(ArrayList<Sequence> sequences);

    /**
     * Returns the player sprite
     *
     * @return the player sprite
     */
    public Sprite getPlayerSprite();

    /**
     * Return the width of the view
     *
     * @return the width of the view
     */
    public int getWidth();

    /**
     * Return the list of the sequences of the game
     *
     * @return the list of the sequences of the game
     */
    public ArrayList<Sequence> getSequences();
}
