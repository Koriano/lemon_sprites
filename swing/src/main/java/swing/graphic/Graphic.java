package swing.graphic;
import javax.swing.*;


/**
 * Class to implement graphic interface
 *
 * @author Margaux SCHNELZAUER
 */

public class Graphic extends JFrame implements gui.graphic.Graphic {

    private JPanel contentPanel;

    /**
     * Constuctor of the class
     */
    public Graphic(){
        // creation of a panel to set the image
        this.contentPanel = new JPanel();
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


        for (SnapshotLayer layer : snapshot.getSnapshotLayers()) {

            // get the parameters
            int x = layer.getX();
            int y = layer.getY();
            Image image = layer.getImage();
            JLabel labelImage = new JLabel(image);
            this.contentPanel.add(labelImage);
            this.contentPanel.setLocation(x,y);

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
