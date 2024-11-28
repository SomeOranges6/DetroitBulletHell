package main.entities;

import main.entities.interfaces.IUpdatable;

import java.awt.*;

public class ProjectileBase extends EntityBase implements IUpdatable {

    int damage;

    public ProjectileBase(int x, int y){
       super(x,y, 10,10);

    }
    @Override
    public void render(Graphics2D g) {

    }


    @Override
    public void onUpdate() {
        mX += vX;
        mY += vY;
    }
}
