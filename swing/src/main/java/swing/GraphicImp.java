package swing;

import gui.graphic.Graphic;
import gui.graphic.Snapshot;
import gui.graphic.SnapshotLayer;

import javax.swing.*;

/**
 * Class to implement graphic interface
 *
 * @author Margaux SCHNELZAUER
 * @see gui.graphic.Graphic
 *
 * @inv this.height > 0 && this.width > 0
 */

public class GraphicImp extends JFrame implements Graphic {

    private JLayeredPane contentPanel;

    /**
     * Constuctor of the class
     */
    public GraphicImp(){
        // creation of a panel to set the image
        this.contentPanel = new JLayeredPane();

        this.setContentPane(this.contentPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * This method displays a snapshot on a graphic interface.
     *
     * @pre snapshot != null
     *
     * @param snapshot : a snapshot composed of snapshot layers
     */
    @Override
    public void displaySnapshot(Snapshot snapshot) {

        if(snapshot != null) {
            SnapshotLayer[] layers = snapshot.getSnapshotLayers();
            int length = layers.length;

            this.setSize(layers[0].getImage().getWidth(), layers[0].getImage().getHeight());

            for (int i = 0; i < length; i++) {

                // get the parameters
                int x = layers[i].getX();
                int y = layers[i].getY();
                ImageImp image = (ImageImp) layers[i].getImage();

                JLabel labelImage = new JLabel(new ImageIcon(image.getLoadedImage()));
                labelImage.setBounds(x, y, image.getWidth(), image.getHeight());

                this.contentPanel.add(labelImage, i, 1);
            }
        }
    }

    /**
     * Return the window height
     *
     * @pre this.height > 0
     * @return The window height
     */
    @Override
    public int getHeight(){
        int h = 0;

        if(super.getHeight() > 0) {
            h = super.getHeight();
        }
        else{
            System.out.println("The window height can't be negative");
        }

        return h;
    }


    /**
     * Return the window width
     *
     * @pre this.width > 0
     * @return The window width
     */
    @Override
    public int getWidth(){
        int w = 0;

        if(super.getWidth() > 0) {
            w = super.getWidth();
        }
        else{
            System.out.println("The window width can't be negative");
        }

        return w;
    }
}
