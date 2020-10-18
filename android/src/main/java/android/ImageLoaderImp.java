package android;



import java.io.BufferedInputStream;
import java.io.InputStream;
import util.io.Loader;


/**
 * The implementation of the ImageImp interface
 *
 * @author Margaux SCHNELZAUER
 * @see
 */
public class ImageLoaderImp implements Loader<ImageImp> {


    @Override
    public ImageImp load(InputStream stream) {
        assert stream != null;
        ImageImp image = null;

        try {
            BufferedInputStream loadedImage = new BufferedInputStream(stream);
            image = new ImageImp(loadedImage);

        } catch(Exception e) {
            e.printStackTrace();
        }
        return image;
    };
}




