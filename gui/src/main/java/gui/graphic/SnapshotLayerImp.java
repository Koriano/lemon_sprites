package gui.graphic;


/**
 * @author Mathis RACINNE-DIVET
 *
 * A class representing a snapshot layer that contains an image laying at a given location.
 * @see gui.graphic.SnapshotLayer
 */

public class SnapshotLayerImp implements SnapshotLayer {

    /**
     * The image composing the SnapshotLayerImp
     */
    private Image image;

    /**
     * The X coordinate of the image
     */
    private int x;

    /**
     * The Y coordinate of the image
     */
    private int y;

    /**
     * The constructor of the SnapshotLayerImp.
     * It takes an ImageImp, with its coordinaites
     *
     * @param img The image composing the SnapshotLayerImp
     * @param xCoord The X coordinate of the image
     * @param yCoord The Y coordinate of the image
     *
     * @pre img != null
     */
    public SnapshotLayerImp(Image img, int xCoord, int yCoord) {
        assert img != null: "Precondition violated";
        this.image = img;
        this.x = xCoord;
        this.y = yCoord;
    }

    /**
     * This method returns the x coordinate of the layer
     *
     * @return the x coordinate of the layer
     */
    @Override
    public int getX() {
        return this.x;
    }

    /**
     * This method returns the y coordinate of the layer
     *
     * @return the y coordinate of the layer
     */
    @Override
    public int getY() {
        return this.y;
    }

    /**
     * This method returns the layer image
     *
     * @pre this.image != null
     *
     * @return the layer image
     */
    @Override
    public Image getImage() {
        return this.image;
    }
}
