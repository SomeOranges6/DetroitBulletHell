package main.swing;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

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
	public static BufferedImage evilShotgunSprite;
	
	public void loadSprites() {
		try {
			playerSprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/playerSpriteSheets/Character1.png")));

			enemySprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/enemySprites/Enemy5.png")));
			shotgunnerSprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/enemySprites/Enemy4.png")));
			heavySprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/enemySprites/Enemy2.png")));
			railgunnerSprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/enemySprites/Enemy3.png")));
			bossSprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/enemySprites/Boss.png")));

			normalProjectileSprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/bulletSprites/Bullet1.png")));
			shotgunProjectileSprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/bulletSprites/Bullet3.png")));
			landmineProjectileSprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/bulletSprites/Landmine.png")));

			railgunProjectileSprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/bulletSprites/Bullet4.png")));
			evilProjectileSprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/bulletSprites/EnemyBullet.png")));
			evilShotgunSprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/bulletSprites/EnemyBullet.png")));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
        
	}
	
}


