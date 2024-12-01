package main.entities;

import main.BulletHellLogic;
import main.entities.interfaces.IUpdatable;

import javax.swing.text.html.parser.Entity;
import java.awt.*;

public class ProjectileBase extends EntityBase implements IUpdatable {

    public int damage;

    public ProjectileBase(EntityBase shooter){
        super(shooter.x,shooter.y, 10,10);
        facingAngle = shooter.facingAngle;

        vX = Math.cos(facingAngle) * speed;
        vY = Math.sin(facingAngle) * speed;
        speed = 20;

        mX = shooter.x;
        mY = shooter.y;
    }

    public ProjectileBase(int width, int height) {
        super(0,0,0,0);
    }

    @Override
    public void render(Graphics2D g) {
        g.fillRect(x, y, 12, 12);
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
