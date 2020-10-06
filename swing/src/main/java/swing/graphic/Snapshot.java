package swing.graphic;

import gui.graphic.SnapshotLayer;

/**
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 * A snapshot that is composed of several snapshot layers.
 * @see gui.graphic.Snapshot
 */
public class Snapshot implements gui.graphic.Snapshot {

    /**
     * The array containing every SnapshotLayer composing this snapshot
     */
    private swing.graphic.SnapshotLayer[] snapshotLayers;

    /**
     * The constructor of the swing.graphic.Snapshot class.
     * It receives an array of snapshots layers that compose this snapshot
     * @param layers The array of SnapshotLayer instances that compose this snapshot.
     * @pre layers != null && layers.length > 0
     */
    public Snapshot(swing.graphic.SnapshotLayer[] layers) {
        if (layers != null && layers.length > 0) {
            this.snapshotLayers = layers;
        }
    }

    /**
     * This method returns the layers of the snapshot
     *
     * @return the layers of the snapshot
     * @pre this.snapshotLayers.size() > 0
     */
    @Override
    public SnapshotLayer[] getSnapshotLayers() {
        return this.snapshotLayers;
    }
}
