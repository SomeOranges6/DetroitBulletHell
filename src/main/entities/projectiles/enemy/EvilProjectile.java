package main.entities.projectiles.enemy;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import main.BulletHellLogic;
import main.MathUtil;
import main.entities.EnemyBase;
import main.entities.EntityBase;
import main.entities.ProjectileBase;
import main.gameplay.Player;

public class EvilProjectile extends ProjectileBase {

    public EvilProjectile(int x, int y) {
        super(x, y, 8, 8);
        speed = 20.0;
    }
    //TODO: once enemy spawn is working, change the hitbox list to be the enemy-inclusive list
    /** Despawns the projectile if it has collided with a wall **/
    @Override
    public void onUpdate() {
        super.onUpdate();
        Rectangle collidedObject = MathUtil.checkForCollidedEntity(this, EnemyBase.collidablesEnemy);
        if(collidedObject != null) {
            if(collidedObject instanceof EntityBase entity && !entity.equals(shooter)){
                entity.health -= 2;
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
                g.setColor(Color.RED);
                g.fillRect(x - BulletHellLogic.player.x + Player.screenX, y - BulletHellLogic.player.y + Player.screenY, width, height);
        }
    }
}