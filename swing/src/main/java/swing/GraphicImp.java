package swing;

import gui.graphic.Graphic;
import gui.graphic.Snapshot;
import gui.graphic.SnapshotLayer;

import javax.swing.*;


/**
 * Class to implement graphic interface
 *
 * @author Margaux SCHNELZAUER
 */

public class GraphicImp extends JFrame implements Graphic {

    private JLayeredPane contentPanel;

    /**
     * Constuctor of the class
     */
    public GraphicImp(){
        // creation of a panel to set the image
        this.contentPanel = new JLayeredPane();
//        this.contentPanel.setLayout(null);

        this.setSize(1000, 1000);
        this.setLocationRelativeTo(null);
        this.setContentPane(this.contentPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Display a snapshot on a panel
     *
     * @param snapshot : a snapshot composed of snapshot layers
     */
    @Override
    public void displaySnapshot(Snapshot snapshot) {

        SnapshotLayer[] layers = snapshot.getSnapshotLayers();
        int length = layers.length;

        this.setSize(layers[0].getImage().getWidth(), layers[0].getImage().getHeight());

        for (int i=0; i<length; i++) {

            // get the parameters
            int x = layers[i].getX();
            int y = layers[i].getY();
            ImageImp image = (ImageImp) layers[i].getImage();

            JLabel labelImage = new JLabel(image.getLoadedImage());
            labelImage.setBounds(x, y, image.getWidth(), image.getHeight());

            this.contentPanel.add(labelImage, i, 1);
        }
    }

    /**
     * Give the window height
     *
     * @return window height
     */
    @Override
    public int getHeight(){
        return super.getHeight();
    }


    /**
     * Give the window width
     *
     * @return window width
     */
    @Override
    public int getWidth(){
        return super.getWidth();
    }
}
