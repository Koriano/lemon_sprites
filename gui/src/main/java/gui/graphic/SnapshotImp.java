package gui.graphic;

/**
 * @author Mathis RACINNE-DIVET
 *
 * A snapshot that is composed of several snapshot layers.
 * @see gui.graphic.Snapshot
 *
 * @inv this.snapshotLayers.size() > 0
 */

public class SnapshotImp implements Snapshot {

    /**
     * The array containing every SnapshotLayerImp composing this snapshot
     */
    private SnapshotLayer[] snapshotLayers;

    /**
     * The constructor of the gui.graphic.SnapshotImp class.
     * It receives an array of snapshots layers that compose this snapshot
     *
     * @pre layers != null && layers.length > 0
     *
     * @param layers The array of SnapshotLayerImp instances that compose this snapshot.
     */
    public SnapshotImp(SnapshotLayer[] layers) {
        assert layers != null && layers.length > 0: "Precondition violated";

        this.snapshotLayers = layers;

        invariant();
    }

    /**
     * Invariant of the class
     */
    private void invariant(){
        assert this.snapshotLayers.length > 0: "Invariant violated";
    }

    /**
     * This method returns the layers of the snapshot
     *
     * @pre this.snapshotLayers.length > 0
     *
     * @return the layers of the snapshot
     */
    @Override
    public SnapshotLayer[] getSnapshotLayers() {

        assert this.snapshotLayers.length > 0: "Precondition violated";

        invariant();

        return this.snapshotLayers;
    }
}
