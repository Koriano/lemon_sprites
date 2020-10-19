package android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;

import gui.graphic.Graphic;
import gui.graphic.Snapshot;
import gui.graphic.SnapshotLayer;

/**
 * @author Alexandre HAMON, Margaux SCHNELZAUER-HENRY
 *
 * A class to display snapshots on a graphic interface
 * @see Graphic
 *
 * @inv this.height > 0 && this.width > 0
 */

public class GraphicImp extends BitmapDrawable implements Graphic {

    private Canvas canvas;

    private int width, height;

    public GraphicImp(){
        this.canvas = null;
    }

    /**
     * This method displays a snapshot on a graphic interface.
     *
     * @param snapshot : a snapshot composed of snapshot layers
     * @pre snapshot != null
     */
    @Override
    public void displaySnapshot(Snapshot snapshot) {
        //Precondition
        assert snapshot != null: "Precondition violated";

        int h = 0;
        int w = 0;
        ImageImp img;

        // For each layer
        for(SnapshotLayer layer: snapshot.getSnapshotLayers()){

            // Get image
            img = (ImageImp) layer.getImage();

            // Save larger image dimensions
            if(img.getWidth() > w){
                w = img.getWidth();
            }

            if(img.getHeight() > h){
                h = img.getHeight();
            }

            // Add bitmap to canvas
            this.canvas.drawBitmap(img.getLoadedImage(), layer.getX(), layer.getY(), new Paint());
        }

        // Save best dimensions to attributes
        this.width = w;
        this.height = h;

        // Call drawing with canvas
        this.draw(this.canvas);
    }

    /**
     * Return the window height
     *
     * @return The window height
     * @pre this.height > 0
     */
    @Override
    public int getHeight() {
        assert this.height > 0 : "Precondition violated";
        return this.height;
    }

    /**
     * Return the window width
     *
     * @return The window width
     * @pre this.width > 0
     */
    @Override
    public int getWidth() {
        assert this.width > 0 : "Precondition violated";
        return this.width;
    }
}
