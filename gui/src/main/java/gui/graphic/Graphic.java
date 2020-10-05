package gui.graphic;
/** 
@author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY

A class to display snapshots on a graphic interface 

@inv this.height > 0 && this.width > 0
*/



public interface Graphic {

  /**
  This method displays a snapshot on a graphic interface.

   @pre snapshot != null
   @param snapshot : a snapshot composed of snapshot layers
   */
   
  public void displaySnapshot(Snapshot snapshot);



  /**
  Return the window height

  @pre this.height > 0
  @return The window height
   */

  public int getHeight();



  /**
  Return the window width

  @pre this.width > 0
  @return The window width 
   */

  public int getWidth(); 
  

}