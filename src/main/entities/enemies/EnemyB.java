package main.entities.enemies;

import java.awt.Color;
import java.awt.Graphics2D;

import main.BulletHellLogic;
import main.MathUtil;
import main.entities.EnemyBase;
import main.entities.projectiles.enemy.EvilProjectile;
import main.gameplay.Player;

public class EnemyB extends EnemyBase { //Fast
	int hypot = 0;

	public EnemyB(int x, int y) {
		super(x, y, 30, 30);
		speed = 7;
		health = 50;
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
		EvilProjectile enemyProjectile = new EvilProjectile(x, y);
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
	
	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fillRect(x - BulletHellLogic.player.x + Player.screenX, y - BulletHellLogic.player.y + Player.screenY, width, height);
	}
}