package android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;

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

public class GraphicImp implements Graphic {

    /**
     * SurfaceHolder which holds the canvas
     */
    private SurfaceHolder holder;

    /**
     * The coordinates of the currently displayed snapshot
     */
    private int width, height;

    /**
     * The constructor of the class
     * @param holder the SurfaceHolder which holds the canvas
     * @pre holder != null
     */
    public GraphicImp(SurfaceHolder holder){
        assert holder != null;
        this.holder = holder;
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

        // get the lock on the holder's canvas

        Canvas canvas = this.holder.lockCanvas();

        if (canvas != null) {
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
                canvas.drawBitmap(img.getLoadedImage(), layer.getX(), layer.getY(), new Paint());
            }

            // Save best dimensions to attributes
            this.width = w;
            this.height = h;

            // Release the lock of the canvas, and update the canvas to the holder
            this.holder.unlockCanvasAndPost(canvas);
        }

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
