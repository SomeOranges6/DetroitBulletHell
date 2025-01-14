package main.gameplay;

import main.BulletHellLogic;
import main.MathUtil;
import main.entities.EntityBase;
import main.entities.interfaces.IUpdatable;
import main.world.LevelManager;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Player extends EntityBase implements IUpdatable, KeyListener{

	public boolean upPressed, downPressed, leftPressed, rightPressed, shootPressed;

	/**Used for locking your shooting direction **/
	public boolean hasPressedLook, lookLock;

    private final SpriteManager spriteManager; // Sprite 

	Character character;

	public static final int screenX = BulletHellLogic.screenWidth/2 - (LevelManager.tileSize/2);
	public static final int screenY = BulletHellLogic.screenHeight/2 - (LevelManager.tileSize/2);

	ArrayList<Weapon> weapons = new ArrayList<>();
	Weapon currentWeapon;
	int lastWeaponIndex = 0;
	
    public Player(int x, int y, Character character) {

		super(x,y, LevelManager.originalTileSize, LevelManager.originalTileSize);
		this.mX = x;
		this.mY = y;

		this.character = character;
		health = character.health();
		speed = character.speed();

    	weapons.addAll(List.of(character.weaponList()));
		for (Weapon weapon : weapons) {
			weapon.setShooter(this);
		}
        spriteManager = new SpriteManager("/assets/playerSpriteSheets/Character1.png");
		currentWeapon = weapons.getFirst();
    }

    @Override
    public void render(Graphics2D g) {
        int playerX = screenX; // Already centered
        int playerY = screenY;
        //Rendering the player is not handled here as it needs to be between layers 2 and 3 of the map
    }
    
    @Override
    public void onUpdate() {
        handleInput();

        // Check for collision on the X-axis before updating position
        if (!MathUtil.checkForCollision(this, BulletHellLogic.collidablesGeneral, true)) {
            mX += vX;
        }

        // Check for collision on the Y-axis before updating position
        if (!MathUtil.checkForCollision(this, BulletHellLogic.collidablesGeneral, false)) {
            mY += vY;
        }

        // Update the player's position
        x = (int) mX;
        y = (int) mY;
        
        
    }


	public void handleInput(){
		vX = 0;
		vY = 0;

		if(upPressed){
			vY -= speed;
			if(!lookLock){
				facingAngle = Math.toRadians(270);
			}
		}
		if(downPressed){
			vY = speed;
			if(!lookLock){
				facingAngle = Math.toRadians(90);
			}
		}
		if(leftPressed){
			vX -= speed;
			if(!lookLock){
				facingAngle = Math.toRadians(180);
			}
		}
		if(rightPressed){
			vX = speed;
			if(!lookLock){
				facingAngle = Math.toRadians(0);
			}
		}
		
		
		// Adjust speed for diagonal movement
        if (vX != 0 && vY != 0) {
            double normalizationFactor = Math.sqrt(2);
            vX /= normalizationFactor;
            vY /= normalizationFactor;
        }
		
		if(shootPressed){
			currentWeapon.onShoot();
		}
		
		spriteManager.setMoving(vX != 0 || vY != 0);
	}
	
    public SpriteManager getSpriteManager() {
        return spriteManager;
    }

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = true;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = true;
		}
		if(code == KeyEvent.VK_Q) {
			 lookLock = !lookLock;
		}
		//player uses an ability
		if (code == KeyEvent.VK_C) {
			character.onAbilityUse(this); 
		}
		//player will attack
		if (code == KeyEvent.VK_E) {
			shootPressed = true;
		}
		//player will switch weapon
		else if (code == KeyEvent.VK_R) {
			int weaponIndex = lastWeaponIndex + 1 >= weapons.size() ? 0 : ++lastWeaponIndex;
			currentWeapon = weapons.get(weaponIndex);
			lastWeaponIndex = weaponIndex;

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		if (code == KeyEvent.VK_E) {
			shootPressed = false;
		}
	}
}