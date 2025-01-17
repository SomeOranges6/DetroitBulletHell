package main;

import main.entities.EntityBase;
import main.entities.interfaces.IUpdatable;
import main.gameplay.CharacterList;
import main.gameplay.Player;
import main.swing.*;

import main.world.LevelManager;
import main.world.levels.TestLevel;
import main.entities.enemies.BossEnemy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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

	/**Future variable used for pausing **/
    private static boolean running = true;

    //WORLD SETTINGS
	/**How much tiles wide each world is **/
    public static final int maxWorldCol = 150;
    public static final int maxWorldRow = 150;

	/**How much tiles are shown at once in the screen **/
    public static final int screenWidth = 1920 - 420;
    public static final int screenHeight = 1080 - 250;

	public static final Dimension screenDim = new Dimension(BulletHellLogic.screenWidth, BulletHellLogic.screenHeight);

	/**The JFrame the game uses **/
	public static JFrame window = new JFrame();

	public static CardLayout layout = new CardLayout();
	/**Contains all other panels, used for card layout */
	public static JPanel mainPanel = new JPanel(layout);

	/**The JPanels the game use **/
    public static IntroScreen iPanel; //intro panel
    public static HowToPlay hPanel; //tutorial panel
    public static GamePanel gPanel;
	public static WinScreen wScreen;
	public static DeathScreen dScreen;

	/**Handles building and rendering the map itself **/
    public static LevelManager levelManager;
    public static SpriteLoader spriteLoader;

	public static TestLevel testLevel;

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

	static SwitchScreenAction action = new SwitchScreenAction();

	public static void main(String[] args) {
		SwingUtilities.invokeLater(BulletHellLogic::new);
	}

	BulletHellLogic(){
		//Set up spriteloader
		spriteLoader = new SpriteLoader();
		spriteLoader.loadSprites();

		centralTick.setActionCommand(centralActionCommand);
		renderTick.setActionCommand(renderActionCommand);
		renderSetup();
	}

	private static void renderSetup(){
		//set up frame
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Detroit");

		//set up panel
		iPanel = new IntroScreen();
		hPanel = new HowToPlay();
		gPanel = new GamePanel();
		wScreen = new WinScreen();
		dScreen = new DeathScreen();

		mainPanel.add(wScreen, "winScreen");
		mainPanel.add(dScreen, "deathScreen");
		mainPanel.add(gPanel, "gamePanel");
		mainPanel.add(hPanel, "howPanel");

		//setting up main panel
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		gd.setFullScreenWindow(window);

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		mainPanel.setPreferredSize(dimension);

		//setting up title screen
		iPanel.setVisible(true);
		mainPanel.add(iPanel, "introPanel");
		layout.show(mainPanel, "introPanel");

		//setting up misc screen keybinds, uses a weird system else everything explodes
		mainPanel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_U, 0), "switchScreen");
		mainPanel.getActionMap().put("switchScreen", action);
		mainPanel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_J, 0), "switchScreen");
		mainPanel.getActionMap().put("switchScreen", action);

		window.add(mainPanel);
		//fit the panel to the size of the windows
		window.pack();
		//more panel stuff
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

	public static void startGame() {
		testLevel  = new TestLevel();
		centralTick.start();
		renderTick.start();
		entitiesToUpdate = new ArrayList<>();
		entitiesToRender = new ArrayList<>();
		levelManager = new LevelManager(testLevel);
		player = new Player(500,500, CharacterList.johnTest);
		entitiesToUpdate.add(player);
		entitiesToRender.add(player);
	}

	/**Handles the actual game loop itself, calling all entities that need to be updated **/
	static class CentralClock implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// Check if the game is running
			if (!BossEnemy.isGameRunning) {
				centralTick.stop();
				renderTick.stop();
				currentScreen = Screen.WIN;
				layout.show(mainPanel, "winScreen");
				mainPanel.revalidate();
				mainPanel.repaint();
				return;
			}
			if (player.health < 0) {
				centralTick.stop();
				renderTick.stop();
				currentScreen = Screen.DEATH;
				layout.show(mainPanel, "deathScreen");
				mainPanel.revalidate();
				mainPanel.repaint();
				return;
			}

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

	public enum Screen {
		INTRO,
		HOWTO,
		WIN,
		DEATH,
		GAME
	}

	public static Enum<Screen> currentScreen = Screen.INTRO;


	static class SwitchScreenAction extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!currentScreen.equals(Screen.GAME)) {
				if (e.getActionCommand().equals("u")) {

					switch (currentScreen) {
						case Screen.INTRO -> {
							layout.show(mainPanel, "howPanel");
							currentScreen = Screen.HOWTO;
						}
						case Screen.HOWTO, Screen.DEATH -> {
							layout.show(mainPanel, "gamePanel");
							currentScreen = Screen.GAME;
							startGame();
							mainPanel.addKeyListener(player);
						}
                        default -> {
						}
					}

					mainPanel.revalidate();
					mainPanel.repaint();


				} else if (e.getActionCommand().equals("j")) {
					if(currentScreen.equals(Screen.DEATH) || currentScreen.equals(Screen.WIN)){
						System.exit(0);
					}
				}
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
