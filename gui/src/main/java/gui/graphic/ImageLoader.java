package gui.graphic;

/**
@author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY

A class to load an image from a path

*/

public interface ImageLoader {

    /** 
    Method to load an image 

    @pre path != null && !path.equals("")

    @param path : the image path
    @return the loaded image
    */

    public Image loadImage(String path);
    
}