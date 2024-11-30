package main.swing;

import javax.swing.*;

import main.BulletHellLogic;
import main.entities.EntityBase;

import java.awt.*;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {

    // Constructor
    public GamePanel() {
    	Dimension dimension = new Dimension(BulletHellLogic.screenWidth, BulletHellLogic.screenHeight);
        this.setPreferredSize(dimension);
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(BulletHellLogic.player);
        this.setFocusable(true);
    }

    public void onUpdate(){
        repaint();
    }

    // Paint graphics
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Render the map
        BulletHellLogic.tileM.draw(g2);

        for(EntityBase entity : BulletHellLogic.entitiesToRender){
            entity.render(g2);
        }
        g2.dispose();
    }
}
