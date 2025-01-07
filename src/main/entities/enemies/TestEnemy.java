package main.entities.enemies;

import java.awt.Color;
import java.awt.Graphics2D;

import main.BulletHellLogic;
import main.MathUtil;
import main.entities.EnemyBase;
import main.entities.projectiles.TestProjectile;
import main.gameplay.Player;

public class TestEnemy extends EnemyBase {

	public TestEnemy(int x, int y) {
		super(x, y, 30, 30);
		speed = 10;
		health = 20;
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		move();
		if(health <= 0) onDead();
		if(BulletHellLogic.tick % 20 == 0) {
			attack();
		}
	}
	
	/**
	 * allows an enemy to attack the player
	 */
	@Override
	public void attack() {
		TestProjectile testProjectile = new TestProjectile(x, y);
		this.lookAtPlayer();
		if(BulletHellLogic.tick % 20 == 0) {
			testProjectile.setShooter(this);
			BulletHellLogic.spawnEntity(testProjectile);
		}
	}

	/**Finds the location of the player and moves towards it**/
	@Override
	public void move() {
		this.lookAtPlayer();
		
      	vX = (double) this.slope;
      	vY = (double) this.slope;
		
		mX += vX;
		mY += vY;

		x = (int) mX;
		y = (int) mY;
		//acording to all known laws of aviation, bees should not be able to fly. They of course, fly anyway. This is because bees dont give a shit about what scientists think. Yellow black yellow black yellow black, oooh black and yellow, lets shake it up a little. Barry breakfast is ready. Coming mom. Barry how many times have I told you not to fly down the stairs?! Im sorry, im just so excited. hi its me jerry seinfeld and ihave no respect for myself
	}
	
	/**
	 * Finds the location of the player and aims for it
	 */
	public void lookAtPlayer() {
		//defines the locations of the enemy and player as coordinate pairs
		int [] enemyLocation = {x, y};
		int [] playerLocation = {BulletHellLogic.player.x, BulletHellLogic.player.y};
		
		//defines a right triangle with a base and height corresponding to the difference between the locations of the player and enemy on both axes
		int [] enemyPlayerTriangle = MathUtil.distFrom(enemyLocation, playerLocation);

        double playerAngle = 0;
        if (enemyPlayerTriangle[0] == 0){ //if the player and the enemy have equal x values 
        	if (enemyPlayerTriangle[0] > 0) playerAngle = 90;
        	else if (enemyPlayerTriangle[0] < 0) playerAngle = 270;
        }
        
        else if (enemyPlayerTriangle[1] == 0) { //if the player and the enemy have equal y values
        	if (enemyPlayerTriangle[1] > 0) playerAngle = 0;
        	else if (enemyPlayerTriangle[1] < 0) playerAngle = 180;
        }

		else {
			 playerAngle = Math.atan((double) enemyPlayerTriangle[1] / enemyPlayerTriangle[0]);
        }
        facingAngle = playerAngle;
        
      //calculates the slope of the line from the enemy to the player
      	int rise = enemyPlayerTriangle[1];
      	int run = enemyPlayerTriangle[0];
      	int slope = rise / run;
        
        /* what
		vX = Math.cos(facingAngle) * speed;
		vY = Math.sin(facingAngle) * speed;
		*/
    }
	
	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.RED);
		g.fillRect(x - BulletHellLogic.player.x + Player.screenX, y - BulletHellLogic.player.y + Player.screenY, width, height);
	}
}