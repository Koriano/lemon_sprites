package gui.graphic;


/**
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 *
 * A class representing a snapshot layer that contains an image laying at a given location
 */

public interface SnapshotLayer {

  /**
   * This method returns the x coordinate of the layer
   *
   * @return the x coordinate of the layer
   */

  public int getX();


  /**
   * This method returns the y coordinate of the layer
   *
   * @return the y coordinate of the layer
   */

  public int getY();


  /**
   * This method returns the layer image
   *
   * @pre this.image != null
   *
   * @return the layer image
   */

  public Image getImage();

}