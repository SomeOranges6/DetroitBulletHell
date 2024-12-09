package main.entities.enemies;

import java.awt.Color;
import java.awt.Graphics2D;

import main.BulletHellLogic;
import main.MathUtil;
import main.entities.EnemyBase;
import main.entities.projectiles.TestProjectile;
import main.gameplay.Player;

public class TestEnemy extends EnemyBase {
	Player p = BulletHellLogic.player;

	public TestEnemy(int x, int y) {
		super(x, y);
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		if(BulletHellLogic.tick % 20 == 0) {
			attack();
			move();
		}
	}
	
	/**
	 * allows an enemy to attack the player
	 */
	@Override
	public void attack() {
		TestProjectile testProjectile = new TestProjectile(x, y);
		this.lookAtPlayer();
		testProjectile.setShooter(this);
		BulletHellLogic.spawnEntity(testProjectile);
	}

	/**Finds the direction the player is at and moves toward it **/
	@Override
	public void move() {
		double[] direction = MathUtil.distFrom(new double[]{mX, mY}, new double[]{BulletHellLogic.player.mX, BulletHellLogic.player.mY});
		vX = direction[0];
		vY = direction[1];

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
		int [] playerLocation = {p.x, p.y};
		
		//defines a right triangle with a base and height corresponding to the difference between the locations of the player and enemy on both axis
		int [] enemyPlayerTriangle = MathUtil.distFrom(enemyLocation, playerLocation);
		
		if (enemyPlayerTriangle[0] > 0){
			double playerAngle = Math.arcsin((double) );
		}
		
		else {
			
		}
	}
	
	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.RED);
		g.drawRect(x, y, width, height);
	}
}