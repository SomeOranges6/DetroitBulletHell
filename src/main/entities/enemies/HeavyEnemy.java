package main.entities.enemies;

import java.awt.Color;
import java.awt.Graphics2D;

import main.BulletHellLogic;
import main.MathUtil;
import main.entities.EnemyBase;
import main.entities.projectiles.TestProjectile;
import main.entities.projectiles.enemy.EvilProjectile;
import main.gameplay.Player;

public class HeavyEnemy extends EnemyBase {

	public HeavyEnemy(int x, int y) {
		super(x, y, 45, 45);
		speed = 8;
		health = 40;
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		if(health <= 0) onDead();
		if(BulletHellLogic.tick % 3 == 0) {
			attack();
		}
	}
	
	/**
	 * allows an enemy to attack the player
	 */
	@Override
	public void attack() {
		EvilProjectile testProjectile = new EvilProjectile(x, y);
		testProjectile.damage /= 2;
		this.lookAtPlayer();
		testProjectile.setShooter(this);
		BulletHellLogic.spawnEntity(testProjectile);

	}

	/**Finds the location of the player and moves towards it**/
	@Override
	public void move() {
		super.move();
		
		/*
		 * vX = slope;
      	   vY = slope;
		 */

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