package Games;

import javax.swing.*;
import tiles.TileManager;
import java.awt.*;

public class GamePanel extends JPanel {

    // Global variables
    final int originalTileSize = 32;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    private boolean running = true;

    // FPS
    final int FPS = 60;
    final int delay = 1000 / FPS; // Delay in milliseconds

    // Declare variables for loading tiles and handling input
    TileManager tileM = new TileManager(this);
    KeyHandler KeyH = new KeyHandler();

    // Default player position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 12*scale;

    // Constructor
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyH);
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

    // Update game state
    public void update() {
        if (KeyH.upPressed) {
            playerY -= playerSpeed;
        } else if (KeyH.downPressed) {
            playerY += playerSpeed;
        } else if (KeyH.leftPressed) {
            playerX -= playerSpeed;
        } else if (KeyH.rightPressed) {
            playerX += playerSpeed;
        }
    }

    // Paint graphics
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Call TileManager draw method
        tileM.draw(g2);

        // Draw the player
        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize/2, tileSize/2);

        g2.dispose();
    }
}
