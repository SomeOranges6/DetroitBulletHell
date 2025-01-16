package main.entities.enemies;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.BulletHellLogic;
import main.MathUtil;
import main.entities.EnemyBase;
import main.entities.projectiles.enemy.EvilProjectile;
import main.gameplay.Player;
import main.gameplay.SpriteManager;

public class EnemyB extends EnemyBase { // Fast
    private SpriteManager spriteManager;
    int hypot = 0;

    public EnemyB(int x, int y) {
        super(x, y, 30, 30);
        speed = 7;
        health = 50;

		spriteManager = new SpriteManager("/assets/enemySprites/Enemy1.png");
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        move();
        if (health <= 0) onDead();
        if (BulletHellLogic.tick % 20 == 0) {
            attack();
        }
    }

    /**
     * Allows an enemy to attack the player
     */
    @Override
    public void attack() {
        EvilProjectile enemyProjectile = new EvilProjectile(x, y);
        this.lookAtPlayer();
        if (BulletHellLogic.tick % 20 == 0) {
            enemyProjectile.setShooter(this);
            BulletHellLogic.spawnEntity(enemyProjectile);
        }
    }

    /** Finds the location of the player and moves towards it **/
    @Override
    public void move() {
        this.lookAtPlayer();

        mX += vX;
        mY += vY;

        x = (int) mX;
        y = (int) mY;
    }

    /**
     * Finds the location of the player and aims for it
     */
    public void lookAtPlayer() {
        // Defines the locations of the enemy and player as coordinate pairs
        int[] enemyLocation = {x, y};
        int[] playerLocation = {BulletHellLogic.player.x, BulletHellLogic.player.y};

        // Defines a right triangle with a base and height corresponding to the difference between the locations of the player and enemy on both axes
        int[] enemyPlayerTriangle = MathUtil.distFrom(playerLocation, enemyLocation);
        int[] playerEnemyTriangle = MathUtil.distFrom(enemyLocation, playerLocation);

        double playerAngle = Math.atan2(enemyPlayerTriangle[1], enemyPlayerTriangle[0]);
        facingAngle = playerAngle;

        // Calculates the hypotenuse of the enemy-player triangle
        int sideA = playerEnemyTriangle[1];
        int sideB = playerEnemyTriangle[0];
        hypot = (int) (Math.sqrt(Math.pow(sideA, 2) + Math.pow(sideB, 2)));

        if (Math.abs(hypot) > 100) {
            vX = Math.cos(facingAngle) * speed;
            vY = Math.sin(facingAngle) * speed;
        }
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
