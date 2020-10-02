package graphic;

/**
@author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY

A generic image (having a name and dimensions) independant from the graphic system

@inv this.imageDimension.x >0 && this.imageDimension.y >0 && !(this.name.equals("")) 

 */

public interface Image {

  /**
  Return the image dimensions

  @pre this.imageDimension.x >0 && this.imageDimension.y >0
  @return The image dimension 
  */

  public Dimension getDimensions();


  /**
  Return the image name 

  @pre !(this.name.equals(""))
  @return The image name   
   */
   
  public String getName();

}