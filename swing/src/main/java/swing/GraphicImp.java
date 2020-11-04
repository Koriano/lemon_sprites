package swing;

import gui.graphic.Graphic;
import gui.graphic.Snapshot;
import gui.graphic.SnapshotLayer;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * @author Margaux SCHNELZAUER, Mathis RACINNE-DIVET
 *
 * Class to implement graphic interface
 *
 * @see gui.graphic.Graphic
 */

public class GraphicImp extends JFrame implements Graphic {

    private JLayeredPane contentPanel;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem importMenuItem;

    /**
     * Constructor of the class
     *
     * @pre menuListener != null
     */
    public GraphicImp(ActionListener menuListener){
        assert menuListener != null;
        // creation of a panel to set the image
        this.contentPanel = new JLayeredPane();

        this.menuBar = new JMenuBar();
        this.fileMenu = new JMenu("File");
        this.importMenuItem = new JMenuItem("Import ZIP");
        this.importMenuItem.addActionListener(menuListener);
        this.fileMenu.add(this.importMenuItem);
        this.menuBar.add(this.fileMenu);

        this.setJMenuBar(this.menuBar);

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

        assert snapshot != null: "Precondition violated";

        this.contentPanel.removeAll();
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

    /**
     * Return the window height
     *
     * @pre super.getHeight() > 0
     *
     * @return The window height
     */
    @Override
    public int getHeight(){

        assert super.getHeight() > 0 : "Precondition violated";

        int h = super.getHeight();

        return h;
    }


    /**
     * Return the window width
     *
     * @pre super.getWidth() > 0
     *
     * @return The window width
     */
    @Override
    public int getWidth(){

        assert super.getWidth() > 0: "Precondition violated";

        int w = super.getWidth();

        return w;
    }
}
