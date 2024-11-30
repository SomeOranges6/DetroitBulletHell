package main.entities;

import main.BulletHellLogic;
import main.entities.interfaces.IUpdatable;
import main.gameplay.Player;

import java.awt.*;
import java.util.ArrayList;

import static main.BulletHellLogic.player;

public class EnemyBase extends EntityBase implements IUpdatable {


    static ArrayList<Rectangle> collidablesEnemy = BulletHellLogic.collidablesGeneral;

    static {
        collidablesEnemy.addFirst(player);
    }
	
    public EnemyBase(int x, int y){
        super(x,y);
        damageable = true;
    }
    
    @Override
    public void onUpdate() {
        move();
    }

    @Override
    public void render(Graphics2D g) {

    }

    public void attack() {

    }

    public void move(){

    }
    
    /**
     * Gives the position of the player
     * @return The x,y coordinate pair**/
    public int[] targetPos() {
    	return new int[] {player.x, player.y};
    }
    
}
