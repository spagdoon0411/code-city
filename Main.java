import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Main {

    public static void main(String[] args)
    {
        String remoteUrl = "https://github.com/spagdoon0411/code-city-test-remote";
        String downloadPath = "C:\\Users\\Spandan\\Downloads\\codecitytestdownload";

        FileDownloader fd = new FileDownloader(remoteUrl, downloadPath);

        JFrame frame = new JFrame("Code City");
        frame.setSize(new Dimension(1920, 1280));

        Map<String, List<Building>> buildings = new HashMap<String, List<Building>>();

        BuildingRenderer renderer = new BuildingRenderer(buildings);
        frame.add(renderer);
        frame.setVisible(true);
    }
}
