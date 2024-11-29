package main;

import main.entities.EntityBase;
import main.entities.interfaces.IUpdatable;
import main.gameplay.Player;
import main.swing.GamePanel;
import main.world.CollisionChecker;
import main.world.TileManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class BulletHellLogic {

	public static  ArrayList<EntityBase> entitiesToRender;
	public static  ArrayList<IUpdatable> entitiestoUpdate;
	public static int tick = 0;
	static Random rand = new Random();
	public static Player player;
	
	// Global variables
	public static final int originalTileSize = 32;
	public static final int scale = 2;

    public static final int tileSize = originalTileSize * scale;
    public static final int maxScreenCol = 16;
    public static final int maxScreenRow = 12;
    public static final int screenWidth = tileSize * maxScreenCol;
    public static final int screenHeight = tileSize * maxScreenRow;
    private static boolean running = true;

    //WORLD SETTINGS
    public final int maxWorldCol = 20;
    public final int maxWorldRow = 20;
    public final int worldHight = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    
    public static GamePanel gPanel = new GamePanel();
    public static TileManager tileM = new TileManager();
    public static CollisionChecker cChecker = new CollisionChecker();

	public static Timer centralTick = new Timer(50, new CentralClock());

	public static void main(String[] args) {
		SwingUtilities.invokeLater(BulletHellLogic::new);
	}

	BulletHellLogic(){	
		//set up frame
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Game Prototype");
        //set up panel
        window.add(gPanel);
        //fit the panel to the size of the windowssssssssssssss
        window.pack();
        //more panel stuff
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gPanel.startGame();
	}

	static class CentralClock implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(tick++ == Integer.MAX_VALUE){
				tick = 0;
			}
		}
	}

	public static void spawnEntity(EntityBase entity){
		if(entity instanceof  IUpdatable){

		}
	}
}
