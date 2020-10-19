package android;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import util.io.Loader;
import gui.graphic.Image;


/**
 * The implementation of the ImageImp interface on Android
 *
 * @author Margaux SCHNELZAUER
 * @see Loader
 */
public class ImageLoaderImp implements Loader<Image> {


    /**
     * Load object from an input stream
     *
     * @pre stream != null
     *
     * @param stream: the stream containing the file
     * @return the object loaded
     */
    @Override
    public Image load(InputStream stream) {
        //pre-condition 
        assert stream != null;

        return new ImageImp(BitmapFactory.decodeStream(stream));
    };
}




