package main.entities.projectiles;

import main.entities.EntityBase;
import main.entities.ProjectileBase;

import java.awt.*;

public class TestProjectile extends ProjectileBase {

    public TestProjectile(EntityBase shooter) {
        super(shooter);
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
        g.fillRect(x, y, width, height);
    }
}
