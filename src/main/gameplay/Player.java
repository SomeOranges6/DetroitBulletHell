package main.gameplay;

import main.BulletHellLogic;
import main.entities.EntityBase;
import main.entities.interfaces.IUpdatable;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Player extends EntityBase implements IUpdatable, KeyListener{

	Character character;

	public int screenX = BulletHellLogic.screenWidth/2 - (BulletHellLogic.tileSize/2);
	public int screenY = BulletHellLogic.screenHeight/2 - (BulletHellLogic.tileSize/2);

	ArrayList<Weapon> weapons = new ArrayList<>();
	Weapon currentWeapon;
	int lastWeaponIndex = 0;
	
    public Player(int x, int y, Character character) {
		super(x,y,BulletHellLogic.originalTileSize, BulletHellLogic.originalTileSize);
    	health = character.health();
    	this.character = character; 
    	this.x = x;
    	this.y = y;

    }

    @Override
    public void render(Graphics2D g) {
    	
    }

    @Override
    public void onUpdate() {

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