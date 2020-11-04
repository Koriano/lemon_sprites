package sprites.model;

import gui.graphic.*;
import java.util.ArrayList;

/**
 * A class to implement Sprite Interface
 *
 * @author Margaux SCHNELZAUER
 * @see sprites.model.Scene
 *
 * @inv this.spritesList.size() > 0 && this.background != null
 */

public class SceneImp implements Scene{

    /**
     * The array containing the sprites composing the scene
     */
    private ArrayList<Sprite> spritesList;

    /**
     * The background of the scene
     */
    private Image background;


    /**
     * Constructor of the scene
     *
     * @param spritesList : a list of snapshot
     * @param background : the background of the scene
     *
     * @pre spritesList.size() > 0 && background != null
     */
    public SceneImp(ArrayList<Sprite> spritesList, Image background){

        assert spritesList.size() > 0 && background != null: "Precondition violated";

        this.spritesList = spritesList;
        this.background = background;

        invariant();
    }

    /**
     * Invariant of the class
     */
    private void invariant(){
        assert this.spritesList.size() > 0 && this.background != null: "Invariant violated";
    }


    /**
     * Method to get the snapshot at the time t=millis
     *
     * @pre millis >= 0
     * @post result != null
     *
     * @param millis: the time at which you want the sprite image (in milliseconds)
     * @return the Snapshot corresponding at the time millis
     */
    @Override
    public Snapshot getCurrentSnapshot(long millis) {

        // pre condition
        assert millis >= 0 : "Precondition violated";

        // get the sprites list
        ArrayList<Sprite> spritesList = this.spritesList;

        // get the size of the list
        int length = spritesList.size();

        // create an array of snapshot layer
        SnapshotLayer[] layers = new SnapshotLayer[length+1];
        layers[0] = new SnapshotLayerImp(this.background, 0, 0);

        // browse the sprites list
        for (int i = 1; i < length+1; i++){
            // get sprite properties
            Image image = spritesList.get(i-1).getCurrentImage(millis);
            int x = spritesList.get(i-1).getX();
            int y = spritesList.get(i-1).getY();

            // create and add a snapshot layer
            SnapshotLayer snapshotLayer = new SnapshotLayerImp(image, x, y);
            layers[i] = snapshotLayer;
        }

        // create a snapshot
        Snapshot currentSnapshot = new SnapshotImp(layers);

        invariant();

        // post condition
        assert currentSnapshot != null : "Postcondition violated";
        return currentSnapshot;
    }



    /**
     * Get the background of the scene
     *
     * @post result != null
     *
     * @return the Image background of the scene
     */
    @Override
    public Image getBackground() {

        Image background = this.background;

        invariant();

        // post condition
        assert background != null : "Postcondition violated";

        return background;
    }

    

    /**
     * Get the list of the sprites
     *
     * @return the list of the scene sprites
     */
    @Override
    public ArrayList<Sprite> getSprites() {
        invariant();
        return this.spritesList;
    }
}
