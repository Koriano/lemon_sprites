package graphic;
/** 
@author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY

A class to display snapshots on a graphic interface 

@inv this.windowDimension.x >0 && this.windowDimension.y >0 
*/



public interface Graphic {

  /**
  This method displays a snapshot on a graphic interface. 
   */
   
  public void displaySnapshot();

  /**
  Return the window dimensions

  @pre this.windowDimension.x >0 && this.windowDimension.y >0 
  @return The window dimension 
   */

  public Dimension getDimension(); 
  

}