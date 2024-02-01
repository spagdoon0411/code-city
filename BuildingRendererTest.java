import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class BuildingRendererTest {

    @Test
    void testBuildingRenderer() {

        Map<String, java.util.List<Building>> buildings = new HashMap<String, List<Building>>();

        ArrayList<Building> b1 = new ArrayList<Building>(Arrays.asList(
                new Building("Class1", 7, 16),
                new Building("Class2", 34, 34),
                new Building("Class3", 4, 3),
                new Building("Class4", 1, 12)
        ));

        buildings.put("Package1", b1);

        ArrayList<Building> b2 = new ArrayList<Building>(Arrays.asList(
                new Building("Class5", 12, 1),
                new Building("Class6", 23, 3),
                new Building("Class7", 14, 6),
                new Building("Class8", 3, 27),
                new Building("Class8", 14, 18)
        ));

        buildings.put("Package2", b2);

        JFrame frame = new JFrame("Code City");
        frame.setSize(new Dimension(1920, 1280));
        BuildingRenderer renderer = new BuildingRenderer(buildings);
        frame.add(renderer);
        frame.setVisible(true);

        // Avoid stopping test until frame is closed
        while (frame.isVisible())
        {
            try {
                Thread.sleep(50);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
