package main;

import main.entities.EntityBase;
import main.entities.interfaces.IUpdatable;
import main.gameplay.CharacterList;
import main.gameplay.Player;
import main.swing.GamePanel;
import main.world.LevelManager;
import main.world.levels.TestLevel;
import main.gameplay.SpriteManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class BulletHellLogic {

	/*Do not under any circumstance outside the player being spawned, add or remove from those lists directly
	  Use the cache instead*/
	private static ArrayList<EntityBase> entitiesToRender;
	private static ArrayList<IUpdatable> entitiesToUpdate;

	public static final ArrayList<EntityBase>
			renderableAddCache= new ArrayList<>(),
			renderableRemoveCache = new ArrayList<>();

	public static final ArrayList<IUpdatable>
			updatableAddCache  = new ArrayList<>(),
			updatableRemoveCache = new ArrayList<>();


	/**What the player's projectiles can collide with outside of the general list, i.e enemies**/
	public static final ArrayList<Rectangle> collidablesPlayerProjectile = new ArrayList<>();
	public static  ArrayList<Rectangle>
			collidableAddCache= new ArrayList<>(),
			collidableRemoveCache = new ArrayList<>();

	/**What most things can collide with, i.e walls**/
	public static final ArrayList<Rectangle> collidablesGeneral = new ArrayList<>();

	/**Used for simple timers across other classes **/
	public static int tick = 0;
	static Random rand = new Random();
	public static Player player;
	
	// SpriteManager for the player
	public static SpriteManager playerSpriteManager;
	

	/**Future variable used for pausing **/
    private static boolean running = true;

    //WORLD SETTINGS
	/**How much tiles wide each world is **/
    public static final int maxWorldCol = 150;
    public static final int maxWorldRow = 150;

	/**How much tiles are shown at once in the screen **/
    public static final int screenWidth = LevelManager.tileSize * 28;
    public static final int screenHeight = LevelManager.tileSize * 20;


	/**The JPanel the game  runs in **/
    public static GamePanel gPanel;

	/**Handles building and rendering the map itself **/
    public static LevelManager levelManager;

	public static TestLevel testLevel = new TestLevel();

	/*TODO: The central tick timer does not need to tick anywhere near this quick for the game logic to work
	 *  By using interpolation of the coordinates when rendering, it can still
	 *  appear smooth, while only ticking 1/3 as often, which massively improves performance*/
	/**The main timer responsible for updating game logic, ticks once every 1/20 of a second **/
	public static Timer centralTick = new Timer(1000/60, new CentralClock());
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
		startGame();
		renderSetup();
		centralTick.start();
		renderTick.start();
	}

	private static void renderSetup(){
		//set up frame
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Game Prototype");
		//set up panel
		gPanel = new GamePanel();
		window.add(gPanel);
		//fit the panel to the size of the windows
		window.pack();
		//more panel stuff
		window.setLocationRelativeTo(null);
		window.setVisible(true);

	}

	private static void startGame() {
		entitiesToUpdate = new ArrayList<>();
		entitiesToRender = new ArrayList<>();
		levelManager = new LevelManager(testLevel);
		player = new Player(500,500, CharacterList.johnTest);
		
		// Initialize the player's SpriteManager
		playerSpriteManager = player.getSpriteManager();
		/** **/
		entitiesToUpdate.add(player);
		entitiesToRender.add(player);
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

				levelManager.updateLevel();

				entitiesToUpdate.removeAll(updatableRemoveCache);
				updatableRemoveCache.clear();
				entitiesToUpdate.addAll(updatableAddCache);
				updatableAddCache.clear();

				collidablesPlayerProjectile.removeAll(collidableRemoveCache);
				collidableRemoveCache.clear();
				collidablesPlayerProjectile.addAll(collidableAddCache);
				collidableAddCache.clear();

				for(IUpdatable updatable : entitiesToUpdate){
					updatable.onUpdate();
				}
			}
			if(e.getActionCommand().equals(renderActionCommand)) {

				entitiesToRender.removeAll(renderableRemoveCache);
				renderableRemoveCache.clear();
				entitiesToRender.addAll(renderableAddCache);
				renderableAddCache.clear();

				gPanel.onUpdate();
			}
		}
	}

	/**Adds entities to the cache to be spawned next tick**/
	public static void spawnEntity(EntityBase entity){
		if(entity instanceof  IUpdatable){
			updatableAddCache.add((IUpdatable) entity);
		}
		if(entity.collidable)
			collidableAddCache.add(entity);
		renderableAddCache.add(entity);
	}



	/**Adds entities to the cache to be removed next tick**/
	public static void removeEntity(EntityBase entity){
		if(entity instanceof  IUpdatable){
			updatableRemoveCache.add((IUpdatable) entity);
		}
		collidableRemoveCache.add(entity);
		renderableRemoveCache.add(entity);
	}

	public static void addCollidable(Rectangle rectangle){
		collidablesGeneral.add(rectangle);
		collidablesPlayerProjectile.add(rectangle);
	} 

	public static void removeCollidable(Rectangle rectangle){
		collidablesGeneral.add(rectangle);
	}
	
	/**Used for adding the hitboxes of entities **/
	public static void addCollidableCache(Rectangle rectangle) {
		collidableAddCache.add(rectangle);
	}
	/**Used for removing the hitboxes of entities **/
	public static void removeCollidableCache(Rectangle rectangle) {
		collidableRemoveCache.add(rectangle);
	} 

	public static ArrayList<EntityBase> getEntitiesToRender() {
		return entitiesToRender;
	}

	public static ArrayList<IUpdatable> getEntitiesToUpdate() {
		return entitiesToUpdate;
	}
}
