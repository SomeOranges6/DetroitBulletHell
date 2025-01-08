package main.gameplay;

import main.entities.EntityBase;
import main.entities.interfaces.IUpdatable;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Player extends EntityBase implements IUpdatable, KeyListener {

<<<<<<< Updated upstream
	Character character;

	ArrayList<Weapon> weapons = new ArrayList<>();
	Weapon currentWeapon;
	int lastWeaponIndex = 0;
	
    public Player(int x, int y, Character character) {
		//TODO: remember actual player width
		super(x,y, )
    	health = character.health();
    	this.character = character; 
    	this.x = x;
    	this.y = y;
=======
    public boolean upPressed, downPressed, leftPressed, rightPressed, shootPressed, lookPressed;

    /** Used for locking your shooting direction **/
    public boolean hasPressedLook, lookLock;

    private final SpriteManager spriteManager; // Sprite manager instance
    Character character;

    public static final int screenX = BulletHellLogic.screenWidth / 2 - (LevelManager.tileSize / 2);
    public static final int screenY = BulletHellLogic.screenHeight / 2 - (LevelManager.tileSize / 2);

    ArrayList<Weapon> weapons = new ArrayList<>();
    Weapon currentWeapon;
    int lastWeaponIndex = 0;

    public Player(int x, int y, Character character) {
        super(x, y, LevelManager.originalTileSize, LevelManager.originalTileSize);
        this.mX = x;
        this.mY = y;

        this.character = character;
        health = character.health();
        speed = character.speed();

        weapons.addAll(List.of(character.weaponList()));
        for (Weapon weapon : weapons) {
            weapon.setShooter(this);
        }
        currentWeapon = weapons.get(0); // Fixed initialization of current weapon

        // Initialize SpriteManager with the character's sprite sheet
        spriteManager = new SpriteManager("/assets/Character1.png");
>>>>>>> Stashed changes
    }

    @Override
    public void render(Graphics2D g) {
<<<<<<< Updated upstream
    	
=======
        int playerX = screenX; // Already centered
        int playerY = screenY;
        //Rendering the player is not handled here as it needs to be between layers 2 and 3 of the map
>>>>>>> Stashed changes
    }

    @Override
    public void onUpdate() {
<<<<<<< Updated upstream

    }

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Player player = new Player(x, y, character);
		
		int playerMovement = e.getKeyCode();
		//player moves up
		if (playerMovement == KeyEvent.VK_W) {
			mY -= vY;
			player.y = (int)mY;
		}
		//player moves left
		if (playerMovement == KeyEvent.VK_A) {
			mX -= vX;
			player.x = (int) mX;
		}
		//player moves down
		if (playerMovement == KeyEvent.VK_S) {
			mY += vY;
			player.y = (int)mY;
		}
		//player moves right
		if (playerMovement == KeyEvent.VK_D) {
			mX += vX;
			player.x = (int) mX;
		}
		//player uses an ability
		if (playerMovement == KeyEvent.VK_Q) {
			character.onAbilityUse(this); 
		}
		//player will attack
		if (playerMovement == KeyEvent.VK_E) {
			currentWeapon.onShoot();
		}
		//player will switch weapon
		else if (playerMovement == KeyEvent.VK_R) {
			int weaponIndex = lastWeaponIndex + 1 > weapons.size() ? 0 : lastWeaponIndex++;
			currentWeapon = weapons.get(weaponIndex);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}
=======
        handleInput();

        if (!MathUtil.checkForCollision(this, BulletHellLogic.collidablesGeneral, true)) {
            mX += vX;
        }
        if (!MathUtil.checkForCollision(this, BulletHellLogic.collidablesGeneral, false)) {
            mY += vY;
        }
        x = (int) mX;
        y = (int) mY;
    }
   

    public void handleInput() {
        vX = 0;
        vY = 0;

        if (upPressed) {
            vY -= speed;
            if (!lookLock) {
                facingAngle = Math.toRadians(270);
            }
        }
        if (downPressed) {
            vY = speed;
            if (!lookLock) {
                facingAngle = Math.toRadians(90);
            }
        }
        if (leftPressed) {
            vX -= speed;
            if (!lookLock) {
                facingAngle = Math.toRadians(180);
            }
        }
        if (rightPressed) {
            vX = speed;
            if (!lookLock) {
                facingAngle = Math.toRadians(0);
            }
        }

        // Update moving state
        spriteManager.setMoving(vX != 0 || vY != 0);
    }
    
    public SpriteManager getSpriteManager() {
        return spriteManager;
    }


    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_Q) {
            lookPressed = true;
        }
        // Player uses an ability
        if (code == KeyEvent.VK_C) {
            character.onAbilityUse(this);
        }
        // Player will attack
        if (code == KeyEvent.VK_E) {
            shootPressed = true;
        }
        // Player will switch weapon
        else if (code == KeyEvent.VK_R) {
            int weaponIndex = (lastWeaponIndex + 1) % weapons.size();
            currentWeapon = weapons.get(weaponIndex);
            lastWeaponIndex = weaponIndex;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_E) {
            shootPressed = false;
        }
    }
}
>>>>>>> Stashed changes
