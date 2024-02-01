import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    final static int frameWidth = 800;
    final static int frameHeight = 600;

    final static int canvasWidth = 9000;
    final static int canvasHeight = 9000;

    BuildingRenderer renderer;

    public View(String title, BuildingRenderer br) {
        super(title);
        this.renderer = br;
    }

    public void produce() {
        this.setSize(new Dimension(frameWidth, frameHeight));

        this.renderer.setVisible(true);
        renderer.setPreferredSize(new Dimension(canvasWidth, canvasHeight));

        JScrollPane scrollPane = new JScrollPane(renderer);
        scrollPane.setPreferredSize(new Dimension(frameWidth, frameHeight));
        this.add(scrollPane);
        scrollPane.setAutoscrolls(true);

        scrollPane.getVerticalScrollBar().setUnitIncrement(5);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(5);
        this.setVisible(true);
    }
}
