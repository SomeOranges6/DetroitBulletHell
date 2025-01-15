package main.entities;

import main.BulletHellLogic;
import main.MathUtil;
import main.entities.interfaces.IUpdatable;
import main.entities.projectiles.TestProjectile;
import main.gameplay.Player;

import java.awt.*;
import java.util.ArrayList;

import static main.BulletHellLogic.player;

public class EnemyBase extends EntityBase implements IUpdatable {

    public static ArrayList<Rectangle> collidablesEnemy = BulletHellLogic.collidablesGeneral;
    int hypot;
    static {
        collidablesEnemy.addFirst(player);
    }
	
    public EnemyBase(int x, int y){
        super(x,y);
        damageable = true;
    }

    public EnemyBase(int x, int y, int width, int height) {
        super(x, y, width, height);
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
    	if (!MathUtil.checkForCollision(this, collidablesEnemy, true)) {
            mX += vX;
        }

        // Check for collision on the Y-axis before updating position
        if (!MathUtil.checkForCollision(this, collidablesEnemy, false)) {
            mY += vY;
        }
    }

    /**
     * Finds the location of the player and aims for it
     */
    public void lookAtPlayer() {
        //defines the locations of the enemy and player as coordinate pairs
        int [] enemyLocation = {x, y};
        int [] playerLocation = {BulletHellLogic.player.x, BulletHellLogic.player.y};

        //defines a right triangle with a base and height corresponding to the difference between the locations of the player and enemy on both axes
        int [] enemyPlayerTriangle = MathUtil.distFrom(playerLocation, enemyLocation);

        double playerAngle = 0;

        playerAngle = Math.atan2(enemyPlayerTriangle[1], enemyPlayerTriangle[0]);
        facingAngle = playerAngle;

        vX = Math.cos(facingAngle) * speed;
        vY = Math.sin(facingAngle) * speed;       


    }

    /**
     * Finds the location of the player and aims for it
     */
    public void lookAtPlayer() {
        //defines the locations of the enemy and player as coordinate pairs
        int [] enemyLocation = {x, y};
        int [] playerLocation = {BulletHellLogic.player.x, BulletHellLogic.player.y};

        //defines a right triangle with a base and height corresponding to the difference between the locations of the player and enemy on both axes
        int [] enemyPlayerTriangle = MathUtil.distFrom(playerLocation, enemyLocation);
        int [] playerEnemyTriangle = MathUtil.distFrom(enemyLocation, playerLocation);

        double playerAngle = 0;

        playerAngle = Math.atan2(enemyPlayerTriangle[1], enemyPlayerTriangle[0]);
        facingAngle = playerAngle;

        //calculates the hypotenuse of the enemy-player triangles
        int sideA = playerEnemyTriangle[1];
        int sideB = playerEnemyTriangle[0];
        hypot = (int) (Math.sqrt(Math.pow(sideA, 2) + Math.pow(sideB, 2)));

        if(Math.abs(hypot) > 100) {
            vX = Math.cos(facingAngle) * speed;
            vY = Math.sin(facingAngle) * speed;
        }


    }
    
    /**
     * Gives the position of the player
     * @return The x,y coordinate pair**/
    public int[] targetPos() {
    	return new int[] {player.x, player.y};
    }
    
}
