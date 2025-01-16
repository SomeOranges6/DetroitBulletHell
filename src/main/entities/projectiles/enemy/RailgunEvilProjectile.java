package main.entities.projectiles.enemy;

import main.BulletHellLogic;
import main.MathUtil;
import main.entities.EnemyBase;
import main.entities.EntityBase;
import main.entities.ProjectileBase;
import main.gameplay.Player;
import main.swing.SpriteLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class RailgunEvilProjectile extends ProjectileBase {
	  private BufferedImage sprite;
    static final ArrayList<Rectangle> player = new ArrayList<>();

    static {
        player.add(BulletHellLogic.player);
    }
    public RailgunEvilProjectile(int x, int y) {
        super(x, y, 12, 12);
        speed = 50;
        damage = 6;
        
        try {
            sprite = ImageIO.read(getClass().getResource("/assets/bulletSprites/EnemyBullet.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load sprite for TestProjectile.");
        }
    }
    //TODO: once enemy spawn is working, change the hitbox list to be the enemy-inclusive list
    /** Despawns the projectile if it has collided with a wall **/
    @Override
    public void onUpdate() {
        super.onUpdate();
        Rectangle collidedObject = MathUtil.checkForCollidedEntity(this, player);
        if(collidedObject != null) {
            if(collidedObject instanceof EntityBase entity && !entity.equals(shooter)){
                entity.health -= damage;
            }
            onDead();
        }
    }

    /**Renders a blue square if within map bounds **/
    @Override
    public void render(Graphics2D g) {
        boolean boundsCheck =
                x + width > BulletHellLogic.player.x - Player.screenX &&
                x - width < BulletHellLogic.player.x + Player.screenX &&
                y + height > BulletHellLogic.player.y - Player.screenY &&
                y - height < BulletHellLogic.player.y + Player.screenY;

        if (boundsCheck) {
            if (SpriteLoader.railgunProjectileSprite != null) {
                // Draw the sprite
                g.drawImage(
                		SpriteLoader.railgunProjectileSprite,
                        x - BulletHellLogic.player.x + Player.screenX,
                        y - BulletHellLogic.player.y + Player.screenY,
                        width +20,
                        height+20,
                        null
                );
            } else {
                // Fallback to the blue square if the sprite fails to load
                g.setColor(Color.BLUE);
                g.fillRect(
                        x - BulletHellLogic.player.x + Player.screenX,
                        y - BulletHellLogic.player.y + Player.screenY,
                        width,
                        height
                );
            }
        }
    }
}
