package main.swing;

import javax.swing.*;
import main.BulletHellLogic;
import main.entities.EntityBase;
import java.awt.*;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {

    // Constructor
    public GamePanel() {
        // Set full screen
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(BulletHellLogic.player);
        this.setFocusable(true);
    }

    public void onUpdate() {
        repaint();
    }

    // Paint graphics
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Render the map and player
        BulletHellLogic.levelManager.draw(g2, BulletHellLogic.player.spriteManager);

        // Render entities
        for (EntityBase entity : BulletHellLogic.getEntitiesToRender()) {
            entity.render(g2);
        }

        g2.dispose();
    }
}
