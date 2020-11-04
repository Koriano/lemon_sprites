package gui.graphic;

/**
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 *
 * A generic image (having a name and dimensions) independant from the graphic system
 */

public interface Image {

  /**
   * Set the image name
   *
   * @pre name != null && !"".equals(name)
   *
   * @param name: the image name
   */

  public void setName(String name);

  /**
   * Return the image height
   *
   * @pre this.height >= 0
   *
   * @return The image height
   */

  public int getHeight();

  /**
   * Return the image width
   *
   * @pre this.width >= 0
   *
   * @return The image width
   */

  public int getWidth();

  /**
   * Return the image name
   *
   * @pre this.name != null && !"".equals(this.name)
   *
   * @return The image name
   */
   
  public String getName();

}