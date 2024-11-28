package main.gameplay;

import main.BulletHellLogic;
import main.entities.ProjectileBase;

import java.awt.image.BufferedImage;
import java.util.function.BiConsumer;

public class WeaponList {

    ProjectileBase testProjectile;
    Weapon stockWeapon = new Weapon(null, (damage, firerate) -> {
        if(BulletHellLogic.tick % firerate == 0){
            BulletHellLogic.spawnEntity(new ProjectileBase());
        }
    });
}
