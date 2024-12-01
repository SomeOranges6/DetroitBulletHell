package main.gameplay;

import main.BulletHellLogic;
import main.entities.ProjectileBase;
import main.entities.projectiles.TestProjectile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.function.BiConsumer;

public class WeaponList {

   public static Weapon stockWeapon = new Weapon(10,10,100, null){
        @Override
        public void onShoot() {
            if(BulletHellLogic.tick % 20 == 0) {
                ProjectileBase stockProjectile = new TestProjectile(shooter.x, shooter.y);
                stockProjectile.setShooter(shooter);
                stockProjectile.damage = damage;
                stockProjectile.speed = 2.0;
                BulletHellLogic.spawnEntity(stockProjectile);
            }
        }
    };
}
