package gui.graphic;

/**
@author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY

A class representing a snapshot layer that contains an image laying at a given location 

@inv this.x >= 0 && this.y >= 0 && this.image != null
*/


public interface SnapshotLayer {

  /**
  This method returns the x coordinate of the layer

  @pre this.x >= 0
  @return the x coordinate of the layer 
  **/

  public int getX();


  /**
  This method returns the y coordinate of the layer

  @pre this.y >= 0
  @return the y coordinate of the layer 
  **/

  public int getY();



  /**
  This method returns the layer image

  @pre this.image != null
  @return the layer image
   */

  public Image getImage();

}