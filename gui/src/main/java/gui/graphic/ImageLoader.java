package graphic;
import java.io.InputStream; 

/**
@author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY

A class to load an image from an input stream

*/

public interface ImageLoader {

    /** 
    Method to load an image 

    @pre stream != null 
    @return the loaded image
    */

    public static Image loadImage(InputStream stream); 
    
}