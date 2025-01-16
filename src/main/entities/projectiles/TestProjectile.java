package main.entities.projectiles;

import main.BulletHellLogic;
import main.MathUtil;
import main.entities.EntityBase;
import main.entities.ProjectileBase;
import main.gameplay.Player;
import main.swing.SpriteLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TestProjectile extends ProjectileBase {
    public TestProjectile(int x, int y) {
        super(x, y, 8, 8);
        speed = 40.0;
    }

    /** Despawns the projectile if it has collided with a wall **/
    @Override
    public void onUpdate() {
        super.onUpdate();
        Rectangle collidedObject = MathUtil.checkForCollidedEntity(this, BulletHellLogic.collidablesPlayerProjectile);
        if (collidedObject != null && !collidedObject.equals(shooter)) {
            if (collidedObject instanceof EntityBase entity) {
                entity.health -= damage;
            }
            onDead();
        }
    }

    /** Renders the projectile as a sprite if within map bounds **/
    @Override
    public void render(Graphics2D g) {
        boolean boundsCheck =
                x + width > BulletHellLogic.player.x - Player.screenX &&
                x - width < BulletHellLogic.player.x + Player.screenX &&
                y + height > BulletHellLogic.player.y - Player.screenY &&
                y - height < BulletHellLogic.player.y + Player.screenY;

        if (boundsCheck) {
            if (SpriteLoader.normalProjectileSprite != null) {
                // Draw the sprite
                g.drawImage(
                        SpriteLoader.normalProjectileSprite,
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
