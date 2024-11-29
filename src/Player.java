package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player extends EntityBase{
	GamePanel gp;
	KeyHandler keyH;
	public final int screenX;
	public final int screenY;
	
	int counter2 = 0;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		//variables to keep player in the center
		screenX = BulletHellLogic.screenWidth/2 - (BulletHellLogic.tileSize/2);
		screenY = BulletHellLogic.screenHeight/2 - (BulletHellLogic.tileSize/2);
		
		solidArea = new Rectangle(0, 0, 48, 48);
		solidArea.x = 8;
		solidArea.y = 16;
		solidArea.width = 32;
		solidArea.height = 32;
		
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		x = BulletHellLogic.tileSize * 8;
		y = BulletHellLogic.tileSize * 6;

		speed = 4*BulletHellLogic.scale;
		direction = "down";
		
	}
	public void getPlayerImage() {
		//put sprite stuff here
	}
	public void update() {
	    // Base movement: set direction based on key presses
	    if (keyH.upPressed && keyH.leftPressed) {
	        direction = "upleft";
	    } else if (keyH.upPressed && keyH.rightPressed) {
	        direction = "upright";
	    } else if (keyH.downPressed && keyH.leftPressed) {
	        direction = "downleft";
	    } else if (keyH.downPressed && keyH.rightPressed) {
	        direction = "downright";
	    } else if (keyH.upPressed) {
	        direction = "up";
	    } else if (keyH.downPressed) {
	        direction = "down";
	    } else if (keyH.leftPressed) {
	        direction = "left";
	    } else if (keyH.rightPressed) {
	        direction = "right";
	    } else {
	        direction = ""; // No movement
	    }

	    // Collision detection
	    collisionOn = false;
	    BulletHellLogic.cChecker.checkTile(this);

	    // If no collision, move the player
	    if (!collisionOn) {
	        switch (direction) {
	            case "up":
	                worldY -= speed;
	                break;
	            case "down":
	                worldY += speed;
	                break;
	            case "left":
	                worldX -= speed;
	                break;
	            case "right":
	                worldX += speed;
	                break;
	            case "upleft":
	                worldY -= speed / 2;
	                worldX -= speed / 2;
	                break;
	            case "upright":
	                worldY -= speed / 2;
	                worldX += speed / 2;
	                break;
	            case "downleft":
	                worldY += speed / 2;
	                worldX -= speed / 2;
	                break;
	            case "downright":
	                worldY += speed / 2;
	                worldX += speed / 2;
	                break;
	            default:
	                // No movement
	                break;
	        }
	    }
	}

	public void draw(Graphics2D g2) {
        // Draw the player
        g2.setColor(Color.white);
        g2.fillRect(screenX, screenY, BulletHellLogic.tileSize/2, BulletHellLogic.tileSize/2);
	}

}
