import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import javax.swing.*;

/* Displays Buildings onto screen
 *  By: Eric Berber 
 */
public class BuildingRenderer extends JComponent {

    private static int BUILDING_OFFSET_X = 50;
    private static int BUILDING_OFFSET_Y = 200;
    private static int RENDER_START_X = 100;
    private static int RENDER_START_Y = 300;

    // Factor used in transparency coloring calculations
    private int alphaNormalize;

    public Map<String, List<Building>> buildings;
    public BuildingRenderer(Map<String, List<Building>> buildings){
        this.buildings = buildings;

        alphaNormalize = 1;
        // Determines the factor by which to normalize products by to
        // obtain transparency coloring
        this.buildings.values()
                .stream()
                .flatMap(Collection::stream)
                .mapToInt(b -> b.getMethods() + b.getFields())
                .max().ifPresent(max -> alphaNormalize = max);
    }

    /**
     * Takes a building and determines what alpha value it should have
     * (buildings with both many fields and many methods are "more important"
     * and therefore darker)
     */
    private int getBuildingAlpha(Building b) {
        return (int) (((float) (b.getFields() + b.getMethods())/((float) alphaNormalize)) * 255);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

//        Font font;
//        try {
//            font = Font.createFont(Font.TRUETYPE_FONT, new File("./fonts/JetBrainsMono-Italic-VariableFont_wght.ttf"));
//        } catch (FontFormatException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        font.deriveFont(Font.PLAIN, 12F);
//        g.setFont(font);

        int posX = RENDER_START_X;
        int posY = RENDER_START_Y;
        for(String key : buildings.keySet()){
            for(Building building : buildings.get(key)){

                int alpha = this.getBuildingAlpha(building);
                g.setColor(new Color(242, 151, 39, alpha));
                g.drawRect(posX, posY - building.getHeight(), building.getWidth(), building.getHeight());
                g.fillRect(posX, posY - building.getHeight(), building.getWidth(), building.getHeight());

                g.setColor(Color.black);
                g.drawString(building.getClassName(), posX , posY + 20); //Arbritrary Y offsets
                g.drawString("F:" + building.getMethods(), posX , posY + 40);
                g.drawString("M:" + building.getFields(), posX , posY + 60);

                posX += building.getWidth() + BUILDING_OFFSET_X;
            }
            posY += BUILDING_OFFSET_Y;
            posX = RENDER_START_X;
        }

    }
}
