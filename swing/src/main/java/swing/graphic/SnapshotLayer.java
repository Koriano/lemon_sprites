package swing.graphic;


/**
 * @author Alexandre HAMON, Mathis RACINNE-DIVET, Margaux SCHNELZAUER-HENRY
 * A class representing a snapshot layer that contains an image laying at a given location.
 * @see gui.graphic.SnapshotLayer
 */
public class SnapshotLayer implements gui.graphic.SnapshotLayer {

    /**
     * The image composing the SnapshotLayer
     */
    private swing.graphic.Image image;

    /**
     * The X coordinate of the image
     */
    private int x;

    /**
     * The Y coordinate of the image
     */
    private int y;

    /**
     * The constructor of the SnapshotLayer.
     * It takes an Image, with its coordinaites
     * @param img The image composing the SnapshotLayer
     * @param xCoord The X coordinate of the image
     * @param yCoord The Y coordinate of the image
     * @pre img != null && xCoord >= 0 && yCoord >= 0
     */
    public SnapshotLayer(swing.graphic.Image img, int xCoord, int yCoord) {
        if (img != null && xCoord >= 0 && yCoord >= 0) {
            this.image = img;
            this.x = xCoord;
            this.y = yCoord;
        }
    }

    /**
     * This method returns the x coordinate of the layer
     *
     * @return the x coordinate of the layer
     * @pre this.x >= 0
     **/
    @Override
    public int getX() {
        return this.x;
    }

    /**
     * This method returns the y coordinate of the layer
     *
     * @return the y coordinate of the layer
     * @pre this.y >= 0
     **/
    @Override
    public int getY() {
        return this.y;
    }

    /**
     * This method returns the layer image
     *
     * @return the layer image
     * @pre this.image != null
     */
    @Override
    public swing.graphic.Image getImage() {
        return this.image;
    }
}
