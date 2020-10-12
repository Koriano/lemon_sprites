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

    private ArrayList<Sprite> spritesList;
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
     * Return the image at the given time
     *
     * @pre millis >= 0
     * @post result != null
     *
     * @param millis: the time at which you want the sprite image (in milliseconds)
     * @return the image at the given time
     */
    @Override
    public Snapshot getCurrentSnapshot(long millis) {

        assert millis >= 0 : "Precondition violated";

        ArrayList<Sprite> spritesList = this.spritesList;
        int length = spritesList.size();
        SnapshotLayer[] layers = new SnapshotLayer[length];


        for (int i = 0; i < length; i++){

            Image image = spritesList.get(i).getCurrentImage(millis);
            int x = spritesList.get(i).getX();
            int y = spritesList.get(i).getY();

            SnapshotLayer snapshotLayer = new SnapshotLayerImp(image, x, y);
            layers[i] = snapshotLayer;
        }

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
