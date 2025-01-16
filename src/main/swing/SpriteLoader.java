package main.swing;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.gameplay.SpriteManager;

/**Caches all the images loaded by the game*/
public class SpriteLoader {
	
	public static BufferedImage playerSprite;
	public static BufferedImage enemySprite;
	public static BufferedImage shotgunnerSprite;
	public static BufferedImage heavySprite;
	public static BufferedImage railgunnerSprite;
	public static BufferedImage bossSprite;
	
	public static BufferedImage normalProjectileSprite;
	public static BufferedImage shotgunProjectileSprite;
	public static BufferedImage landmineProjectileSprite;
	
	public static BufferedImage railgunProjectileSprite;
	public static BufferedImage evilProjectileSprite;
	
	public void loadSprites() {
		
		playerSprite = ImageIO.read(getClass().getResource("/assets/playerSpriteSheets/Character1.png"));    
		enemySprite = ImageIO.read(getClass().getResource("/assets/enemySprites/Enemy5.png"));    
		shotgunnerSprite = ImageIO.read(getClass().getResource("/assets/enemySprites/Enemy4.png"));
		heavySprite = ImageIO.read(getClass().getResource("/assets/enemySprites/Enemy2.png"));     
		railgunnerSprite = ImageIO.read(getClass().getResource("/assets/enemySprites/Enemy3.png"));
		bossSprite = ImageIO.read(getClass().getResource("/assets/enemySprites/Boss.png"));
		
		normalProjectileSprite = ImageIO.read(getClass().getResource("/assets/enemySprites/Bullet1.png"));  
		shotgunProjectileSprite = ImageIO.read(getClass().getResource("/assets/bulletSprites/Bullet3.png")); 
	    landmineProjectileSprite = ImageIO.read(getClass().getResource("/assets/enemySprites/Enemy5.png"));
	                             
	    railgunProjectileSprite = ImageIO.read(getClass().getResource("/assets/enemySprites/Enemy5.png")); 
        evilProjectileSprite = ImageIO.read(getClass().getResource("/assets/bulletSprites/EnemyBullet.png"));
        
        
	}
	
}


