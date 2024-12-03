package main;

import main.entities.EntityBase;
import main.entities.interfaces.IUpdatable;
import main.gameplay.CharacterList;
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

	/*Do not under any circumstance outside the player being spawned, add or remove from those lists directly
	  Use the cache instead*/
	private static final ArrayList<EntityBase> entitiesToRender = new ArrayList<>();
	private static final ArrayList<IUpdatable> entitiesToUpdate = new ArrayList<>();

	public static ArrayList<EntityBase>
			renderableAddCache= new ArrayList<>(),
			renderableRemoveCache = new ArrayList<>();

	public static ArrayList<IUpdatable>
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
	

	/**Future variable used for pausing **/
    private static boolean running = true;

    //WORLD SETTINGS
	/**How much tiles wide each world is.
	 * Temporary, after prototype needs to be replaced with room system **/
    public static final int maxWorldCol = 20;
    public static final int maxWorldRow = 20;
    
    public static final int screenWidth = TileManager.tileSize * 16;
    public static final int screenHeight = TileManager.tileSize * 12;


	/**The JPanel the game  runs in **/
    public static GamePanel gPanel;

	/**Handles building and rendering the map itself **/
    public static TileManager tileM;

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
		tileM = new TileManager();
		player = new Player(100,100, CharacterList.johnTest);
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

				entitiesToUpdate.removeAll(updatableRemoveCache);
				updatableRemoveCache.clear();
				entitiesToUpdate.addAll(updatableAddCache);
				updatableAddCache.clear();
				

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
