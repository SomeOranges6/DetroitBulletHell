package main.entities.enemies;

import java.awt.Color;
import java.awt.Graphics2D;

import main.BulletHellLogic;
import main.MathUtil;
import main.entities.EnemyBase;
import main.entities.projectiles.EnemyProjectile;
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
		EnemyProjectile enemyProjectile = new EnemyProjectile(x, y);
		this.lookAtPlayer();
		if(BulletHellLogic.tick % 20 == 0) {
			enemyProjectile.setShooter(this);
			BulletHellLogic.spawnEntity(enemyProjectile);
		}
	}

	/**Finds the location of the player and moves towards it**/
	@Override
	public void move() {
		this.lookAtPlayer();
		
		/*
		 * vX = slope;
      	   vY = slope;
		 */
		
		mX += vX;
		mY += vY;

		x = (int) mX;
		y = (int) mY;
		//acording to all known laws of aviation, bees should not be able to fly. They of course, fly anyway. This is because bees dont give a shit about what scientists think. Yellow black yellow black yellow black, oooh black and yellow, lets shake it up a little. Barry breakfast is ready. Coming mom. Barry how many times have I told you not to fly down the stairs?! Im sorry, im just so excited. hi its me jerry seinfeld and ihave no respect for myself
	}

	
	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.RED);
		g.fillRect(x - BulletHellLogic.player.x + Player.screenX, y - BulletHellLogic.player.y + Player.screenY, width, height);
	}
}