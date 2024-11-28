package Games;


import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class EntityBase extends Rectangle {

    /** x and y coords for anything related with movement
     * use the normal x and y from Rectangle otherwise **/
    double mX, mY;

    /**The velocity on the x and y axis**/
    double vX,vY;

    public int speed;

    /** Stuff for sprite direction and counters */
    public String direction;
	public int spriteCounter = 0;
	public int SpriteNum = 1;
	
	// stuff for position character in middle

	public int worldX, worldY;  
	
	
    /** Stuff for collisions */
	public Rectangle solidArea;
	public boolean collisionOn = false;

    int health;
    /**An identifier for this entity, every entity must have a unique ID
     *  **/
    int id;

    boolean damageable;

    BufferedImage texture;

    /**
     * What this entity does once it is killed or despawned
     */
    public void onDead (){

    }
    
    //Collision with tile checker
  

    /**
     * Called whenever something needs to check collision with this entity
     *
     * @param collidedObj The object which needs to check the collision
     * @return whether the entity **/
    public boolean checkHitbox(Rectangle collidedObj){
        return collidedObj.intersects(this);
    }
} 
