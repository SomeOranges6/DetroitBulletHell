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

	/**Finds the direction the player is at and moves toward it **/
	@Override
	public void move() {
		mX += vX;
		mY += vY;

		x = (int) mX;
		y = (int) mY;
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
        if (enemyPlayerTriangle[1] > 0){ //if the enemy is above the player
            playerAngle = Math.atan((double) enemyPlayerTriangle[0] / enemyPlayerTriangle[1]);
        }

		else if (enemyPlayerTriangle[0] > 0) { //if enemy is to the right of the player
            playerAngle = Math.atan((double) enemyPlayerTriangle[1] / enemyPlayerTriangle[0]);
        }
        facingAngle = Math.toRadians(playerAngle);
		vX = Math.cos(facingAngle) * speed;
		vY = Math.sin(facingAngle) * speed;
    }
	
	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.RED);
		g.fillRect(x - BulletHellLogic.player.x + Player.screenX, y - BulletHellLogic.player.y + Player.screenY, width, height);
	}
}