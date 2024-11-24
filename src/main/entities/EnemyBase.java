package main.entities;

import main.entities.interfaces.IUpdatable;

import java.awt.*;

public class EnemyBase extends EntityBase implements IUpdatable {

    public EnemyBase(int x, int y){
        damageable = true;
    }
    @Override
    public void onUpdate() {
        attack();
        move();
    }

    @Override
    public void render(Graphics2D g) {

    }

    public void attack() {

    }

    public void move(){

    }
}
