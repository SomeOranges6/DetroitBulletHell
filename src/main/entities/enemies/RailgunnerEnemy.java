package main.entities.enemies;

import main.BulletHellLogic;
import main.entities.EnemyBase;
import main.entities.projectiles.enemy.EvilProjectile;
import main.entities.projectiles.enemy.RailgunEvilProjectile;
import main.gameplay.Player;
import main.gameplay.SpriteManager;
import main.swing.SpriteLoader;

import java.awt.*;

public class RailgunnerEnemy extends EnemyBase {
    private SpriteManager spriteManager;

    public RailgunnerEnemy(int x, int y) {
        super(x, y, 30, 30);
        speed = 12;
        health = 20;
		spriteManager = new SpriteManager(SpriteLoader.railgunnerSprite);
    }

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
        // Use the sprite manager to draw the animated sprite
        spriteManager.drawSprite(
            g,
            facingAngle,
            x - BulletHellLogic.player.x + Player.screenX,
            y - BulletHellLogic.player.y + Player.screenY
        );
    }
}