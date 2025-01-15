package main.entities;

import main.entities.interfaces.IUpdatable;

import java.awt.*;

public class ProjectileBase extends EntityBase implements IUpdatable {

    public int damage;
    public EntityBase shooter;
    public ProjectileBase(int x, int y, int width, int height) {
        super(x,y,width,height);
        collidable = false;
    }

    @Override
    public void render(Graphics2D g) {

    }


    @Override
    public void onUpdate() {
        mX += vX;
        mY += vY;

        x = (int) mX;
        y = (int) mY;
    }

    /**Sets the bullet to be at the coordinates of the entity that shot it **/
    public void setShooter(EntityBase shooter){
        this.shooter = shooter;
        facingAngle = shooter.facingAngle;

        vX = Math.cos(facingAngle) * speed;
        vY = Math.sin(facingAngle) * speed;

        //Makes projectiles point toward where the entity that shot them is looking
        x = shooter.x;
        y = shooter.y;
    }
}
