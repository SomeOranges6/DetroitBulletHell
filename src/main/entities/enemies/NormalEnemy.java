package main.entities.enemies;

import java.awt.Color;
import java.awt.Graphics2D;

import main.BulletHellLogic;
import main.MathUtil;
import main.entities.EnemyBase;
import main.entities.projectiles.enemy.EvilProjectile;
import main.gameplay.Player;
import main.gameplay.SpriteManager;
import main.swing.SpriteLoader;

public class NormalEnemy extends EnemyBase {
    private final SpriteManager spriteManager;

	public NormalEnemy(int x, int y) {
		super(x, y, 30, 30);
		speed = 10;
		health = 10;
		
		spriteManager = new SpriteManager(SpriteLoader.enemySprite);
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		if(health <= 0) onDead();
		if(BulletHellLogic.tick % 20 == 0) {
			attack();
		}
	}
	
	/**
	 * Spawns projectiles at a one second interval
	 */
	@Override
	public void attack() {
		EvilProjectile enemyProjectile = new EvilProjectile(x, y);
		this.lookAtPlayer();
		enemyProjectile.setShooter(this);
		BulletHellLogic.spawnEntity(enemyProjectile);

	}

	@Override
	public void move() {
		super.move();
		x = (int) mX;
		y = (int) mY;
		//acording to all known laws of aviation, bees should not be able to fly. They of course, fly anyway. This is because bees dont give a shit about what scientists think. Yellow black yellow black yellow black, oooh black and yellow, lets shake it up a little. Barry breakfast is ready. Coming mom. Barry how many times have I told you not to fly down the stairs?! Im sorry, im just so excited. hi its me jerry seinfeld and ihave no respect for myself
	}

	
	@Override
	public void render(Graphics2D g) {
        // Use the sprite manager to draw the animated sprite
        spriteManager.drawSprite(
            g,
            facingAngle,
            x - BulletHellLogic.player.x + Player.screenX,
            y - BulletHellLogic.player.y + Player.screenY
        );
    }
}