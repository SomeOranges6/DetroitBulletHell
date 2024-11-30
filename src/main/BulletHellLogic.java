package main;

import main.entities.EntityBase;
import main.entities.interfaces.IUpdatable;
import main.gameplay.Player;
import main.swing.GamePanel;
import main.world.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class BulletHellLogic {

	public static ArrayList<EntityBase> entitiesToRender;
	public static ArrayList<IUpdatable> entitiestoUpdate;

	/**What the player's projectiles can collide with outside of the general list, i.e enemies**/
	public static ArrayList<Rectangle> collidablesPlayerProjectile;

	/**What most things can collide with, i.e walls**/
	public static ArrayList<Rectangle> collidablesGeneral;

	/**Used for simple timers across other classes **/
	public static int tick = 0;
	static Random rand = new Random();
	public static Player player;
	
	// Global variables
	public static final int originalTileSize = 32;
	/**Testing variable for adjusting the scale of the world **/
	public static final int scale = 2;

	/**How big each tile is after the scale is applied**/
    public static final int tileSize = originalTileSize * scale;
   	/**How much tiles are shown at once in the screen **/
    public static final int screenWidth = tileSize * 16;
    public static final int screenHeight = tileSize * 12;

	/**Future variable used for pausing **/
    private static boolean running = true;

    //WORLD SETTINGS
	/**How much tiles wide each world is
	 * Temporary, after prototype needs to be replaced with room system **/
    public static final int maxWorldCol = 20;
    public static final int maxWorldRow = 20;

	/**The JPanel the game  runs in **/
    public static GamePanel gPanel = new GamePanel();

	/**Handles building and rendering the map itself **/
    public static TileManager tileM = new TileManager();

	/**The main timer responsible for updating game logic, ticks once every 1/20 of a second **/
	public static Timer centralTick = new Timer(50, new CentralClock());
	public static final String centralActionCommand = "central";

	/**The timer responsible for rendering, ticks once every 1/60th of a second
	 * i.e 60 FPS **/
	public static Timer renderTick = new Timer(1000/60, new CentralClock());
	public static final String renderActionCommand = "render";

	public static void main(String[] args) {
		SwingUtilities.invokeLater(BulletHellLogic::new);
	}

	BulletHellLogic(){
		centralTick.setActionCommand(centralActionCommand);
		renderTick.setActionCommand(renderActionCommand);
		renderSetup();
        startGame();
	}

	private static void renderSetup(){
		//set up frame
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Game Prototype");
		//set up panel
		window.add(gPanel);
		//fit the panel to the size of the windows
		window.pack();
		//more panel stuff
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

	private static void startGame() {
		centralTick.start();
	}

	/**Handles the actual game loop itself, calling all entities that need to be updated **/
	static class CentralClock implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//updates the tick value for misc simple timers
			if(e.getActionCommand().equals(centralActionCommand)) {
				if (tick++ == Integer.MAX_VALUE) {
					tick = 0;
				}

			}
			if(e.getActionCommand().equals(renderActionCommand)) {
				gPanel.onUpdate();
			}
		}
	}

	public static void spawnEntity(EntityBase entity){
		if(entity instanceof  IUpdatable){
			entitiestoUpdate.add((IUpdatable) entity);
		}
		entitiesToRender.add(entity);
	}

	public static void removeEntity(EntityBase entity){
		if(entity instanceof  IUpdatable){
			entitiestoUpdate.remove((IUpdatable) entity);
		}
		entitiesToRender.remove(entity);
	}

	public static void addCollidable(Rectangle rectangle){
		collidablesGeneral.add(rectangle);
	}

	public static void removeCollidable(Rectangle rectangle){
		collidablesGeneral.add(rectangle);
	}

}
