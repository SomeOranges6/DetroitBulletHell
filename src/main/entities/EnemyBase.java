package main.entities;

import main.entities.interfaces.IUpdatable;
import main.gameplay.Player;

import java.awt.*;

public class EnemyBase extends EntityBase implements IUpdatable {
	
	Player player;
	
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
