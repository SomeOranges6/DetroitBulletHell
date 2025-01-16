package main.entities.enemies;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.BulletHellLogic;
import main.MathUtil;
import main.entities.EnemyBase;
import main.entities.projectiles.enemy.EvilProjectile;
import main.gameplay.Player;
import main.swing.SpriteLoader;

public class BossEnemy extends EnemyBase {
    public static boolean isGameRunning = true; // Game state variable

    public BossEnemy(int x, int y) {
        super(x, y, 100, 100); // Boss size
        speed = 2; // Very slow
        health = 100; // High health

        // Load the boss sprite
        
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        move();
        if (health <= 0) {
            onDead();
        }
        if (BulletHellLogic.tick % 1 == 0) { // Slower attack interval
            attack();
        }
    }

    /**
     * Allows the boss to attack the player
     */
    @Override
    public void attack() {
        EvilProjectile bossProjectile = new EvilProjectile(x+30, y+30);
        bossProjectile.setShooter(this);
        this.lookAtPlayer();
        BulletHellLogic.spawnEntity(bossProjectile);
    }

    /**
     * Finds the location of the player and moves towards it slowly
     */
    @Override
    public void move() {
        this.lookAtPlayer();

        mX += vX;
        mY += vY;

        x = (int) mX;
        y = (int) mY;
    }

    /**
     * Ends the game when the boss dies
     */
    @Override
    public void onDead() {
        isGameRunning = false; // Ends the game
        super.onDead();
    }

    @Override
    public void render(Graphics2D g) {
        if (SpriteLoader.bossSprite != null) {
            g.drawImage(
            		SpriteLoader.bossSprite,
                x - BulletHellLogic.player.x + Player.screenX,
                y - BulletHellLogic.player.y + Player.screenY,
                width,
                height,
                null
            );
        } else {
            // Fallback if sprite fails to load
            g.setColor(java.awt.Color.RED);
            g.fillRect(
                x - BulletHellLogic.player.x + Player.screenX,
                y - BulletHellLogic.player.y + Player.screenY,
                width,
                height
            );
        }
    }

    /**
     * Aims the boss towards the player
     */
    public void lookAtPlayer() {
        int[] enemyLocation = {x, y};
        int[] playerLocation = {BulletHellLogic.player.x, BulletHellLogic.player.y};

        int[] enemyPlayerTriangle = MathUtil.distFrom(playerLocation, enemyLocation);

        double playerAngle = Math.atan2(enemyPlayerTriangle[1], enemyPlayerTriangle[0]);
        facingAngle = playerAngle;

        vX = Math.cos(facingAngle) * speed;
        vY = Math.sin(facingAngle) * speed;
    }
}
