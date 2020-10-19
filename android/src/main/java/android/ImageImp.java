package android;

import android.graphics.Bitmap;

import gui.graphic.Image;

/**
 * @author Alexandre HAMON
 *
 * An android image.
 * @see gui.graphic.Image
 *
 * @inv this.height >= 0 && this.width >= 0 && !(this.name.equals(""))
 */
public class ImageImp implements Image {

    /**
     * The name of the image
     */
    private String name;

    /**
     * The data of the image
     */
    private Bitmap loaded_image;

    /**
     * Constructor of the image class
     *
     * @pre loaded_image != null
     * @param loaded_image: the image laoded with its data
     */
    public ImageImp(Bitmap loaded_image){
        assert loaded_image != null: "Precondition violated";

        this.loaded_image = loaded_image;
    }

    /**
     * Set the image name
     *
     * @pre name != null && !"".equals(name)
     * @param name: the image name
     */
    @Override
    public void setName(String name) {
        assert name != null && !"".equals(name): "Precondition violated";
        this.name = name;
    }

    /**
     * Return the image height
     *
     * @pre this.loaded_image.getHeight() >= 0
     * @return The image height
     */
    @Override
    public int getHeight() {
        assert this.loaded_image.getHeight() >= 0: "Precondition violated";
        return this.loaded_image.getHeight();
    }

    /**
     * Return the image width
     *
     * @pre this.loaded_image.getWidth() >= 0
     * @return The image width
     */
    @Override
    public int getWidth() {
        assert this.loaded_image.getWidth() >= 0: "Precondition violated";
        return this.loaded_image.getWidth();
    }

    /**
     * Return the image name
     *
     * @pre !(this.name.equals(""))
     * @return The image name
     */
    @Override
    public String getName() {
        assert !(this.name.equals("")) : "Precondition violated";
        return this.name;
    }

    /**
     * Return the loaded image.
     *
     * @pre this.loaded_image != null
     * @return the loaded image
     */
    public Bitmap getLoadedImage(){
        assert this.loaded_image != null: "Precondition violated";
        return this.loaded_image;
    }
}
