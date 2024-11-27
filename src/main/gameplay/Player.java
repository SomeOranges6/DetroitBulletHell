package main.gameplay;

import main.entities.EntityBase;
import main.entities.interfaces.IUpdatable;
import java.awt.*;
import java.awt.event.*;

public class Player extends EntityBase implements IUpdatable, KeyListener{
	Character character;
	int x;
	int y;
	
    public Player(int x, int y, Character character) {
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
		else if (playerMovement == KeyEvent.VK_A) {
			mX -= vX; 
			player.x = (int) mX;
		}
		//player moves down
		else if (playerMovement == KeyEvent.VK_S) {
			mY += vY; 
			player.y = (int)mY;
		}
		//player moves right
		else if (playerMovement == KeyEvent.VK_D) {
			mY += vX; 
			player.x = (int) mX;
		}
		//player uses an ability
		else if (playerMovement == KeyEvent.VK_Q) {
			character.onAbilityUse(this); 
		}
		//player will attack (no method for this currently)
		else if (playerMovement == KeyEvent.VK_E) {
			//null
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}