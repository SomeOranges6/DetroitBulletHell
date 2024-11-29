package main.swing;

import javax.swing.*;

import main.BulletHellLogic;
import main.world.CollisionChecker;
import main.world.TileManager;

import java.awt.*;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {

    // FPS
    final int FPS = 60;
    final int delay = 1000 / FPS; // Delay in milliseconds

    // Declare variables for loading tiles and handling input

    // Default player position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 6*scale;

    // Constructor
    public GamePanel() {
    	Dimension dimension = new Dimension(BulletHellLogic.screenWidth, BulletHellLogic.screenHeight);
        this.setPreferredSize(dimension);
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(BulletHellLogic.player);
        this.setFocusable(true);
    }

    // Start the game loop using Timer
    public void startGame() {
        Timer timer = new Timer(delay, e -> {
            if (running) {
                update();
                repaint();
            }
        });
        timer.start();
    }

    // Paint graphics
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Call TileManager draw method
        BulletHellLogic.tileM.draw(g2);
        player.draw(g2);


        g2.dispose();
    }
}
