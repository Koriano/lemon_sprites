package gui.graphic;

/**
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 *
 * A class representing a snapshot which is composed of multiple images
 *
 * @inv this.snapshotLayers.size() > 0
 */

public interface Snapshot {

  /**
   * This method returns the layers of the snapshot
   *
   * @pre this.snapshotLayers.size() > 0
   * @return the layers of the snapshot
   */
  
  public SnapshotLayer[] getSnapshotLayers();
  
}