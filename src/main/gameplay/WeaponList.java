package main.gameplay;

import main.BulletHellLogic;
import main.entities.ProjectileBase;
import main.entities.projectiles.MinigunProjectile;
import main.entities.projectiles.ShotgunProjectile;
import main.entities.projectiles.TestProjectile;
import main.entities.projectiles.YesProjectile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.function.BiConsumer;

/**Holds all the weapons that the player, or even some enemies, may use **/
public class WeaponList {

   public static Weapon stockWeapon = new Weapon(10,10,100, null){
        @Override
        public void onShoot() {
            if(BulletHellLogic.tick % firerate == 0) {
                ProjectileBase stockProjectile = new TestProjectile(shooter.x, shooter.y);
                stockProjectile.setShooter(shooter);
                stockProjectile.damage = damage;
                stockProjectile.speed = 2.0;
                BulletHellLogic.spawnEntity(stockProjectile);
            }
        }
    };

    public static Weapon shotgun = new Weapon(4,20,100, null){
        @Override
        public void onShoot() {
            if(BulletHellLogic.tick % firerate == 0) {
                for (int i = -3; i < 4; i++) {
                    ProjectileBase shotgunProjectile = new ShotgunProjectile(shooter.x, shooter.y);
                    shotgunProjectile.setShooter(shooter);
                    shotgunProjectile.damage = damage;

                    double spreadAngle =  shotgunProjectile.facingAngle - Math.toRadians(15) * i;
                    shotgunProjectile.vX = Math.cos(spreadAngle) * shotgunProjectile.speed;
                    shotgunProjectile.vY = Math.sin(spreadAngle) * shotgunProjectile.speed;
                    BulletHellLogic.spawnEntity(shotgunProjectile);
                }
            }
        }
    };
    
    public static Weapon minigun = new Weapon(4,5,250, null){
        @Override
        public void onShoot() {
            if(BulletHellLogic.tick % firerate == 0) {
                ProjectileBase minigunProjectile = new MinigunProjectile(shooter.x, shooter.y);
                minigunProjectile.setShooter(shooter);
                minigunProjectile.damage = damage;
                minigunProjectile.speed = 2.0;
                BulletHellLogic.spawnEntity(minigunProjectile);
            }
        }
    };
    
    public static Weapon yes = new Weapon(4,20,100, null){
        @Override
        public void onShoot() {
            if(BulletHellLogic.tick % firerate == 0) {
                for (int i = -3; i < 4; i++) {
                    ProjectileBase yesProjectile = new YesProjectile(shooter.x, shooter.y);
                    yesProjectile.setShooter(shooter);
                    yesProjectile.damage = damage;

                    double spreadAngle =  yesProjectile.facingAngle - Math.toRadians(15) * i;
                    yesProjectile.vX = Math.cos(spreadAngle) * yesProjectile.speed;
                    yesProjectile.vY = Math.sin(spreadAngle) * yesProjectile.speed;
                    BulletHellLogic.spawnEntity(yesProjectile);
                }
            }
        }
    };
}
