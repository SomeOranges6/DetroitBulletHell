package main.entities.projectiles.enemy;

import main.BulletHellLogic;
import main.MathUtil;
import main.entities.EnemyBase;
import main.entities.EntityBase;
import main.entities.ProjectileBase;
import main.gameplay.Player;

import java.awt.*;
import java.util.ArrayList;

public class RailgunEvilProjectile extends ProjectileBase {
    static final ArrayList<Rectangle> player = new ArrayList<>();

    static {
        player.add(BulletHellLogic.player);
    }
    public RailgunEvilProjectile(int x, int y) {
        super(x, y, 12, 12);
        speed = 50;
    }
    //TODO: once enemy spawn is working, change the hitbox list to be the enemy-inclusive list
    /** Despawns the projectile if it has collided with a wall **/
    @Override
    public void onUpdate() {
        super.onUpdate();
        Rectangle collidedObject = MathUtil.checkForCollidedEntity(this, player);
        if(collidedObject != null) {
            if(collidedObject instanceof EntityBase entity && !entity.equals(shooter)){
                entity.health -= 6;
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
            g.setColor(Color.MAGENTA);
            g.fillRect(x - BulletHellLogic.player.x + Player.screenX, y - BulletHellLogic.player.y + Player.screenY, width, height);
        }
    }
}
