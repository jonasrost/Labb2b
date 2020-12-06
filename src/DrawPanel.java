import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.List;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

/***
 * This panel represent the animated part of the view with the car images.
 */
public class DrawPanel extends JPanel{

    private CarModel carM;

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, CarModel cm) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        this.carM = cm;
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        List<BufferedImage> images = carM.getObjectImages();

        for (int i = 0; i < images.size(); i++) {
            BufferedImage component = images.get(i);
            g.drawImage(component, carM.getObjectPoints().get(i).x, carM.getObjectPoints().get(i).y, null);
        }
    }
}
