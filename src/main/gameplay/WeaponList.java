package main.gameplay;

import main.BulletHellLogic;
import main.entities.ProjectileBase;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.function.BiConsumer;

public class WeaponList {

    public static ProjectileBase testProjectile =  new ProjectileBase(10, 10){
        @Override
        public void render(Graphics2D g) {
            super.render(g);
            g.fillRect(x, y, width, height);
        }
    };

   public static Weapon stockWeapon = new Weapon(10,10,100, null){
        @Override
        public void onShoot() {
            if(BulletHellLogic.tick % 20 == 0) {
                ProjectileBase stockProjectile = testProjectile;
                stockProjectile.setShooter(shooter);
                stockProjectile.damage = damage;
                BulletHellLogic.spawnEntity(stockProjectile);
            }
        }
    };
}
