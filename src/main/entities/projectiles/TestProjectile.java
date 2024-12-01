package main.entities.projectiles;

import main.BulletHellLogic;
import main.MathUtil;
import main.entities.ProjectileBase;
import main.gameplay.Player;

import java.awt.*;

public class TestProjectile extends ProjectileBase {

    public TestProjectile(int x, int y, int width, int height) {
        super(x, y);
        speed = 2.0;

        vX = Math.cos(facingAngle) * speed;
        vY = Math.sin(facingAngle) * speed;
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
        if (x + BulletHellLogic.tileSize > BulletHellLogic.player.x - Player.screenX &&
                x - BulletHellLogic.tileSize < BulletHellLogic.player.x + Player.screenX &&
                y + BulletHellLogic.tileSize > BulletHellLogic.player.y - Player.screenY &&
                y - BulletHellLogic.tileSize < BulletHellLogic.player.y + Player.screenY) {
                g.fillRect(x + MathUtil.getXRenderOffset(), y + MathUtil.getYRenderOffset(), width, height);
        }
    }
}
