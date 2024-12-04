package main.entities;

import main.entities.interfaces.IUpdatable;

import java.awt.*;

public class ProjectileBase extends EntityBase implements IUpdatable {

    public int damage;

    public ProjectileBase(int x, int y, int width, int height) {
        super(x,y,width,height);
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


    public void setShooter(EntityBase shooter){
        facingAngle = shooter.facingAngle;

        vX = Math.cos(facingAngle) * speed;
        vY = Math.sin(facingAngle) * speed;

        x = shooter.x;
        y = shooter.y;
    }
}
