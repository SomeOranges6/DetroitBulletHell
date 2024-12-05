package main.entities.projectiles;

import main.BulletHellLogic;
import main.MathUtil;
import main.entities.ProjectileBase;
import main.gameplay.Player;

import java.awt.*;

public class TestProjectile extends ProjectileBase {

    public TestProjectile(int x, int y) {
        super(x, y, 8, 8);
        speed = 20.0;

        /*Makes projectiles point toward where the entity that shot them is looking **/
        vX = Math.cos(facingAngle) * speed;
        vY = Math.sin(facingAngle) * speed;
    }

    /** **/
    @Override
    public void onUpdate() {
        super.onUpdate();
        Rectangle collidedObject = MathUtil.checkForCollision(this, BulletHellLogic.collidablesGeneral);
        if(collidedObject != null) {
            onDead();
        }
    }

    /**Renders a blue square if within map bounds **/
    @Override
    public void render(Graphics2D g) {
        if (x + width > BulletHellLogic.player.x - Player.screenX &&
                x - width < BulletHellLogic.player.x + Player.screenX &&
                y + height > BulletHellLogic.player.y - Player.screenY &&
                y - height < BulletHellLogic.player.y + Player.screenY) {
                    g.setColor(Color.BLUE);
                    g.fillRect(x - BulletHellLogic.player.x + Player.screenX, y - BulletHellLogic.player.y + Player.screenY, width, height);
        }
    }
}
