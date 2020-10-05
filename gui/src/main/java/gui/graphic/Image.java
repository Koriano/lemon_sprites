package gui.graphic;

/**
@author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY

A generic image (having a name and dimensions) independant from the graphic system

@inv this.heigth > 0 && this.width > 0 && !(this.name.equals(""))
 */

public interface Image {

  /**
  Return the image heigth

  @pre this.heigth > 0
  @return The image heigth
   */

  public int getHeigth();



  /**
  Return the image width

  @pre this.width > 0
  @return The image width 
   */

  public int getWidth(); 



  /**
  Return the image name 

  @pre !(this.name.equals(""))
  @return The image name   
   */
   
  public String getName();

}