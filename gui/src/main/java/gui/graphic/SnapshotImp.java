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
     * @param layers The array of SnapshotLayerImp instances that compose this snapshot.
     * @pre layers != null && layers.length > 0
     */
    public SnapshotImp(SnapshotLayer[] layers) {
        if (layers != null && layers.length > 0) {
            this.snapshotLayers = layers;
        }
    }

    /**
     * This method returns the layers of the snapshot
     *
     * @pre this.snapshotLayers.size() > 0
     * @return the layers of the snapshot
     */
    @Override
    public SnapshotLayer[] getSnapshotLayers() {
        return this.snapshotLayers;
    }
}
