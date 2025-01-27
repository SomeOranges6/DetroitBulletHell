package main.entities;

import main.BulletHellLogic;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class EntityBase extends Rectangle {
	
    /** x and y coords for anything related with movement
     * use the normal x and y from Rectangle otherwise **/
	public double mX, mY;

    /**The velocity on the x and y axis**/
    public double vX,vY;

    public double speed;

    public int health;

    /** Stuff for sprite direction and counters */
    public String spriteDirection;
	public int spriteCounter = 0;
	public int SpriteNum = 1;

    /**An identifier for this entity, every entity must have a unique ID
     *  **/
    public int id;

    public boolean damageable;

    public boolean collidable = true;

    public BufferedImage texture;

    /**Which angle this entity is pointing to, used for rendering the sprite and projectiles
     * Measured in radians, use the methods in the Math class to convert it to degrees**/
    public double facingAngle;

    public EntityBase(int x, int y, int width, int height){
        super(x, y, width, height);
        mX = x;
        mY = y;
    }

    public EntityBase(int x, int y){
        super(x, y);
    }

    /**
     * What this entity does once it is killed or despawned
     */
    public void onDead (){
        BulletHellLogic.removeEntity(this);
    }

    public abstract void render(Graphics2D g);
}
