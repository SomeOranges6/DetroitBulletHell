package main.entities.projectiles;

import main.BulletHellLogic;
import main.MathUtil;
import main.entities.EntityBase;
import main.entities.ProjectileBase;
import main.gameplay.Player;
import main.swing.SpriteLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Landmine extends ProjectileBase {
    public Landmine(int x, int y) {
        super(x, y, 32, 32);
    }

    //TODO: once enemy spawn is working, change the hitbox list to be the enemy-inclusive list
    /** Despawns the projectile if it has collided with a wall **/
    @Override
    public void onUpdate() {
        super.onUpdate();
        Rectangle collidedObject = MathUtil.checkForCollidedEntity(this, BulletHellLogic.collidablesPlayerProjectile);
        if(collidedObject != null && !collidedObject.equals(shooter)) {
            for (int i = -9; i < 11; i++) {
                ProjectileBase yesProjectile = new TestProjectile(x, y);
                yesProjectile.damage /= 3;
                //yesProjectile.setShooter(shooter);

                double spreadAngle = yesProjectile.facingAngle - Math.toRadians(18) * i;
                yesProjectile.vX = Math.cos(spreadAngle) * yesProjectile.speed;
                yesProjectile.vY = Math.sin(spreadAngle) * yesProjectile.speed;
                BulletHellLogic.spawnEntity(yesProjectile);
            }
            onDead();
        }
    }

    /**Renders an orange square if within map bounds **/
    @Override
    public void render(Graphics2D g) {
        boolean boundsCheck =
                x + width > BulletHellLogic.player.x - Player.screenX &&
                x - width < BulletHellLogic.player.x + Player.screenX &&
                y + height > BulletHellLogic.player.y - Player.screenY &&
                y - height < BulletHellLogic.player.y + Player.screenY;

        if (boundsCheck) {
            if (SpriteLoader.landmineProjectileSprite != null) {
                // Draw the sprite
                g.drawImage(
                		SpriteLoader.landmineProjectileSprite,
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