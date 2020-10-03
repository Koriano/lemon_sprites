package graphic;

/**
@author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY

A generic image (having a name and dimensions) independant from the graphic system

@inv this.length > 0 && this.width > 0 && !(this.name.equals("")) 
 */

public interface Image {

  /**
  Return the image length

  @pre this.length > 0
  @return The image length 
   */

  public int getLength();



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