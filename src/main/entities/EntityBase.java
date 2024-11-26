package main.entities;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class EntityBase extends Rectangle {

    /** x and y coords for anything related with movement
     * use the normal x and y from Rectangle otherwise **/
	protected double mX, mY;

    /**The velocity on the x and y axis**/
    protected double vX,vY;

    protected int health;
    /**An identifier for this entity, every entity must have a unique ID
     *  **/
    protected int id;

    protected boolean damageable;

    protected BufferedImage texture;

    /**
     * What this entity does once it is killed or despawned
     */
    public void onDead (){

    }

    /**
     * Called whenever something needs to check collision with this entity
     *
     * @param collidedObj The object which needs to check the collision
     * @return whether the entity **/
    public boolean checkHitbox(Rectangle collidedObj){
        return collidedObj.intersects(this);
    }

    public abstract void render(Graphics2D g);




}
