package main.entities.projectiles;

import main.BulletHellLogic;
import main.MathUtil;
import main.entities.EntityBase;
import main.entities.ProjectileBase;
import main.gameplay.Player;

import java.awt.*;

public class ShotgunProjectile extends ProjectileBase {

    public ShotgunProjectile(int x, int y) {
        super(x, y, 8, 8);
        speed = 40.0;
    }

    //TODO: once enemy spawn is working, change the hitbox list to be the enemy-inclusive list
    /** Despawns the projectile if it has collided with a wall **/
    @Override
    public void onUpdate() {
        super.onUpdate();
        Rectangle collidedObject = MathUtil.checkForCollidedEntity(this, BulletHellLogic.collidablesPlayerProjectile);
        if(collidedObject != null && !collidedObject.equals(shooter)) {
            if(collidedObject instanceof EntityBase entity){
                entity.health -= 5;
            }
            onDead();
        }
    }

    /**Renders a green square if within map bounds **/
    @Override
    public void render(Graphics2D g) {
        boolean boundsCheck =
                x + width > BulletHellLogic.player.x - Player.screenX &&
                x - width < BulletHellLogic.player.x + Player.screenX &&
                y + height > BulletHellLogic.player.y - Player.screenY &&
                y - height < BulletHellLogic.player.y + Player.screenY;

        if (boundsCheck) {
            g.setColor(Color.GREEN);
            g.fillRect(x - BulletHellLogic.player.x + Player.screenX, y - BulletHellLogic.player.y + Player.screenY, width, height);
        }
    }

    @Override
    public void setShooter(EntityBase shooter) {
        facingAngle = shooter.facingAngle;
        x = shooter.x;
        y = shooter.y;
    }
}
