package sprites.model;

import gui.graphic.Image;
import gui.graphic.Snapshot;
import java.util.ArrayList;


public class SceneImp implements Scene{

    private ArrayList<Snapshot> snapshotList;
    private long totalDuration;
    private Image background;


    /**
     * Constructor of the scene
     *
     * @param snapshotList : a list of snapshot
     * @param totalDuration : the duration of the scene
     * @param background : the background of the scene
     */
    public SceneImp(ArrayList<Snapshot> snapshotList, long totalDuration, Image background){
        this.snapshotList = snapshotList;
        this.totalDuration = totalDuration;
        this.background = background;
    }


    /**
     * Return the image at the given time
     *
     *
     * @pre millis >= 0 && millis < this.totalDuration
     * @post result != null
     *
     * @param millis: the time at which you want the sprite image (in milliseconds)
     * @return the image at the given time
     */
    @Override
    public Snapshot getCurrentSnapshot(long millis) {
        int index = (int) (millis/this.totalDuration);

        return this.snapshotList.get(index - 1);
    }


    /**
     * Give the background of the scene
     *
     * @return the background of the scene
     */
    @Override
    public Image getBackground() {
        return this.background;
    }
}
