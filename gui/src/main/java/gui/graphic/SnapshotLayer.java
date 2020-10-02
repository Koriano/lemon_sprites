package graphic;

/**
@author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY

A class representing a snapshot layer that contains an image laying at a given location 

@inv this.layerLocation.x > 0 && this.layerLocation.y > 0 && this.layerLocation != null && this.image != null
*/


public interface SnapshotLayer {

  /**
  This method returns the location of the layer

  @pre this.layerLocation.x > 0 && this.layerLocation.y > 0 && this.layerLocation != null 
  @return the location of the layer 
  **/

  public Point getLocation();


  /**
  This method returns the layer image

  @pre this.image != null
  @return the layer image
   */

  public Image getImage();

}