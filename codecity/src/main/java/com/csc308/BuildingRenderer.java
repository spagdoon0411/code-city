package com.csc308;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JComponent;

/* Displays Buildings onto screen
 *  By: Eric Berber 
 */
public class BuildingRenderer extends JComponent {
    // Change consts as needed
    private static int BUILDING_OFFSET_X = 50;
    private static int BUILDING_OFFSET_Y = 200;
    private static int RENDER_START_X = 100;
    private static int RENDER_START_Y = 100;
    public Map<String, ArrayList<Building>> buildings;
    public BuildingRenderer(Map<String, ArrayList<Building>> buildings){
        this.buildings = buildings;
    }   

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int posX = RENDER_START_X;
        int posY = RENDER_START_Y;
        for(String key : buildings.keySet()){
            for(Building building : buildings.get(key)){

                g.setColor(Color.MAGENTA); 
                g.drawRect(posX, posY - building.getHeight(), building.getWidth(), building.getHeight());
                g.fillRect(posX, posY - building.getHeight(), building.getWidth(), building.getHeight());

                g.setColor(Color.black);
                g.drawString(building.getClassName(), posX , posY + 20); //Arbritrary Y offsets
                g.drawString("F:" + Integer.toString(building.getMethods()), posX , posY + 40);
                g.drawString("M:" + Integer.toString(building.getFields()), posX , posY + 60);

                posX += building.getWidth() + BUILDING_OFFSET_X;
            }
            posY += BUILDING_OFFSET_Y;
            posX = RENDER_START_X;
        }

    }
}
