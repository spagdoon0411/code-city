import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    int frameWidth;
    int frameHeight;

    int canvasWidth;
    int canvasHeight;

    BuildingRenderer renderer;

    public View(String title,
                BuildingRenderer br,
                int frameWidth,
                int frameHeight,
                int canvasWidth,
                int canvasHeight) {
        super(title);
        this.renderer = br;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
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
