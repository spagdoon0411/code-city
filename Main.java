import javax.swing.*;
import java.util.*;
import java.util.List;

public class Main {

    final static int FRAME_WIDTH = 800;
    final static int FRAME_HEIGHT = 600;

    final static int CANVAS_WIDTH = 9000;

    final static int CANVAS_HEIGHT = 9000;

    public static void main(String[] args)
    {

        String spantest = "https://github.com/spagdoon0411/code-city-test-remote";
        // String remoteUrl = JOptionPane.showInputDialog("GitHub repository:");

        String spanDownload = "/Users/spandansuthar/Desktop";
        // String downloadPath = JOptionPane.showInputDialog("Absolute download path:");

        // FileDownloader fd = new FileDownloader(remoteUrl, downloadPath);
        FileDownloader fd = new FileDownloader(spantest, spanDownload);

        Map<String, List<Building>> buildings = new HashMap<>();

        View v = new View(
                "Code City",
                new BuildingRenderer(buildings),
                FRAME_WIDTH,
                FRAME_HEIGHT,
                CANVAS_WIDTH,
                CANVAS_HEIGHT
        );

        v.produce();
    }
}
