import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Main {

    final static int frameWidth = 1920;
    final static int frameHeight = 1080;

    public static void main(String[] args)
    {

        // https://github.com/spagdoon0411/code-city-test-remote
        String remoteUrl = JOptionPane.showInputDialog("GitHub repository:");

        // C:\Users\Spandan\Downloads\codecitytestdownload
        String downloadPath = JOptionPane.showInputDialog("Absolute download path:");

        FileDownloader fd = new FileDownloader(remoteUrl, downloadPath);

        JFrame frame = new JFrame("Code City");
        frame.setSize(new Dimension(frameWidth, frameHeight));

        Map<String, List<Building>> buildings = new HashMap<String, List<Building>>();

        BuildingRenderer renderer = new BuildingRenderer(buildings);
        frame.add(renderer);
        frame.setVisible(true);
    }
}
