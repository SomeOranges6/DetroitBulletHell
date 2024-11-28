package Games;

import javax.swing.*;
import tiles.TileManager;
import java.awt.*;

public class GamePanel extends JPanel {

    // Global variables
    final int originalTileSize = 32;
    final int scale = 2;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    private boolean running = true;

    //WORLD SETTINGS
    public final int maxWorldCol = 20;
    public final int maxWorldRow = 20;
    public final int worldHight = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    
    
    
    // FPS
    final int FPS = 60;
    final int delay = 1000 / FPS; // Delay in milliseconds

    // Declare variables for loading tiles and handling input
    TileManager tileM = new TileManager(this);
    CollisionChecker cChecker = new CollisionChecker(this);
    KeyHandler KeyH = new KeyHandler();
    public Player player = new Player(this, KeyH);

    // Default player position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 6*scale;

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
    	player.update();
    }

    // Paint graphics
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Call TileManager draw method
        tileM.draw(g2);
        player.draw(g2);


        g2.dispose();
    }
}
