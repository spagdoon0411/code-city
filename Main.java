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
        ArrayList<Building> bruh = new ArrayList<Building>(Arrays.asList(
                new Building("Class1", 7, 16),
                new Building("Class2", 34, 34),
                new Building("Class3", 4, 3),
                new Building("Class4", 1, 12)
        ));
        buildings.put("Package1", bruh);

        ArrayList<Building> bro = new ArrayList<Building>(Arrays.asList(
                new Building("Class5", 12, 1),
                new Building("Class6", 23, 3),
                new Building("Class7", 14, 6),
                new Building("Class8", 3, 27)
        ));
        buildings.put("Package2", bro);

        BuildingRenderer renderer = new BuildingRenderer(buildings);
        frame.add(renderer);
        frame.setVisible(true);
    }
}
