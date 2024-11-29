package main.world;


import main.BulletHellLogic;
import main.entities.EntityBase;
import main.swing.GamePanel;

public class CollisionChecker {
	GamePanel gp = BulletHellLogic.gPanel;
	
	public void checkTile(EntityBase entity) {
		
	   	int entityLeftWorldX = entity.offsetX + entity.x;
	    int entityRightWorldX = entity.offsetX + entity.x + entity.width;
	    int entityTopWorldY = entity.offsetY + entity.y;
	    int entityBottomWorldY = entity.offsetY + entity.y + entity.height;
	    	
	    int entityLeftCol = entityLeftWorldX/BulletHellLogic.tileSize;
	    int entityRightCol = entityRightWorldX/BulletHellLogic.tileSize;
	    int entityTopRow = entityTopWorldY/BulletHellLogic.tileSize;
	    int entityBottomRow = entityBottomWorldY/BulletHellLogic.tileSize;
	    
	    int tileNum1, tileNum2;
	    
	    switch(entity.direction) {
	    case "up":
	    	entityTopRow = (int) ((entityTopWorldY - entity.speed)/BulletHellLogic.tileSize);
	    	tileNum1 = BulletHellLogic.tileM.mapTileNum[entityLeftCol][entityTopRow];
	    	tileNum2 = BulletHellLogic.tileM.mapTileNum[entityRightCol][entityTopRow];
	    	if(BulletHellLogic.tileM.tile[tileNum1].collision == true || BulletHellLogic.tileM.tile[tileNum2].collision == true) {
	    		entity.collisionOn = true;
	    	}
	    	break;
	    case "down":
	    	entityBottomRow = (int) ((entityBottomWorldY - entity.speed)/BulletHellLogic.tileSize);
	    	tileNum1 = BulletHellLogic.tileM.mapTileNum[entityLeftCol][entityBottomRow];
	    	tileNum2 = BulletHellLogic.tileM.mapTileNum[entityRightCol][entityBottomRow];
	    	if(BulletHellLogic.tileM.tile[tileNum1].collision == true || BulletHellLogic.tileM.tile[tileNum2].collision == true) {
	    		entity.collisionOn = true;
	    	}
	    	break;
	    case "left":
	    	entityLeftCol = (int) ((entityLeftWorldX - entity.speed)/BulletHellLogic.tileSize);
	    	tileNum1 = BulletHellLogic.tileM.mapTileNum[entityLeftCol][entityTopRow];
	    	tileNum2 = BulletHellLogic.tileM.mapTileNum[entityLeftCol][entityBottomRow];
	    	if(BulletHellLogic.tileM.tile[tileNum1].collision == true || BulletHellLogic.tileM.tile[tileNum2].collision == true) {
	    		entity.collisionOn = true;
	    	}
	    	break;
	    case "right":
	    	entityRightCol = (int) ((entityRightWorldX - entity.speed)/BulletHellLogic.tileSize);
	    	tileNum1 = BulletHellLogic.tileM.mapTileNum[entityRightCol][entityTopRow];
	    	tileNum2 = BulletHellLogic.tileM.mapTileNum[entityRightCol][entityBottomRow];
	    	if(BulletHellLogic.tileM.tile[tileNum1].collision == true || BulletHellLogic.tileM.tile[tileNum2].collision == true) {
	    		entity.collisionOn = true;
	    	}
	    	break;
	    
	    	
	    }
	    }
	 

}
