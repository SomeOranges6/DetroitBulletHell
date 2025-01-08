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

    public void onUpdate() {
        repaint();
    }

    // Paint graphics
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Render the map and player
        BulletHellLogic.levelManager.draw(g2, BulletHellLogic.playerSpriteManager);

        // Render entities
        for (EntityBase entity : BulletHellLogic.getEntitiesToRender()) {
            entity.render(g2);
        }

        // Draw coordinates
        g2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        g2.setColor(Color.RED);
        g2.drawString("X coord: " + BulletHellLogic.player.x, 10, 30);
        g2.drawString("Y coord: " + BulletHellLogic.player.y, 10, 50);

        g2.dispose();
    }
}
