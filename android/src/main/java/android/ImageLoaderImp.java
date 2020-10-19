package android;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import util.io.Loader;
import gui.graphic.Image;


/**
 * The implementation of the ImageImp interface
 *
 * @author Margaux SCHNELZAUER
 * @see
 */
public class ImageLoaderImp implements Loader<Image> {


    @Override
    public Image load(InputStream stream) {
        assert stream != null;

        return new ImageImp(BitmapFactory.decodeStream(stream));
    };
}




