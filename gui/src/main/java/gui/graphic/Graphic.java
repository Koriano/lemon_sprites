package gui.graphic;
/** 
@author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY

A class to display snapshots on a graphic interface 

@inv this.heigth > 0 && this.width > 0
*/



public interface Graphic {

  /**
  This method displays a snapshot on a graphic interface.

   @pre snapshot != null
   */
   
  public void displaySnapshot(Snapshot snapshot);



  /**
  Return the window heigth

  @pre this.heigth > 0
  @return The window heigth
   */

  public int getHeigth();



  /**
  Return the window width

  @pre this.width > 0
  @return The window width 
   */

  public int getWidth(); 
  

}