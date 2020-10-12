package sprites.model;

import gui.graphic.*;

import java.util.ArrayList;

/**
 * A class to implement Sprite Interface
 *
 * @author Margaux SCHNELZAUER
 * @see sprites.model.Scene
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
     */
    public SceneImp(ArrayList<Sprite> spritesList, Image background){
        this.spritesList = spritesList;
        this.background = background;
    }


    /**
     * Return the snapshot at the given time
     *
     * @pre millis >= 0
     * @post result != null
     *
     * @param millis: the time at which you want the sprite image (in milliseconds)
     * @return the image at the given time
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
        SnapshotLayer[] layers = new SnapshotLayer[length];

        // browse the sprites list
        for (int i = 0; i < length; i++){
            // get sprite properties
            Image image = spritesList.get(i).getCurrentImage(millis);
            int x = spritesList.get(i).getX();
            int y = spritesList.get(i).getY();

            // create and add a snapshot layer
            SnapshotLayer snapshotLayer = new SnapshotLayerImp(image, x, y);
            layers[i] = snapshotLayer;
        }

        // create a snapshot
        Snapshot currentSnapshot = new SnapshotImp(layers);

        // post condition
        assert currentSnapshot != null : "Postcondition violated";
        return currentSnapshot;
    }


    /**
     * Give the background of the scene
     *
     * @return the background of the scene
     * @post result != null
     */
    @Override
    public Image getBackground() {

        Image background = this.background;

        // post condition
        assert background != null : "Postcondition violated";

        return background;
    }
}
