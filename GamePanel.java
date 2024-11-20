package Games;

import javax.swing.*;

import tiles.TileManager;

import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

	//global variables
    final int originalTileSize = 32;
    final int scale = 2;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    
    //FPS
    final int FPS = 60;
    //declare variable for loading tiles and the game thread
    TileManager tileM = new TileManager(this);
    Thread gameThread;
    //have the panel equal to the size of previously mentioned variables
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }
    //start game thread
    public void startGameThread() {
    	gameThread = new Thread(this);
    	gameThread.start();
    }
    
    //game loop
	@Override
	public void run() {
		//set draw interval to equal to 1 second/60 seconds.
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;

		while(gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			//main loop
			if (delta >= 1) {
				update();
				repaint();
				delta--;
				
			}
		}
	}	
	//stuff for repainting, 
	public void update() {
		//movement cycles, entities, and whatnot goes in here
	}
	public void paintComponent(Graphics g) {
		//drawing stuff
		super.paintComponent(g);
		//load graphics
		Graphics2D g2 = (Graphics2D)g;
		//call tile manager draw method
		tileM.draw(g2);
		
		
		
	}
	
}