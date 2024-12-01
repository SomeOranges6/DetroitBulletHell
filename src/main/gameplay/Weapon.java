package main.gameplay;

import main.entities.EntityBase;

import java.awt.image.BufferedImage;

/**Mostly just a data holder, represents the weapons the player uses
 * Must set shooter in order to work**/
public class Weapon {

    EntityBase shooter;
    int damage, firerate;

    BufferedImage texture;

    int maxAmmo, ammo;

    public Weapon(int damage, int firerate, int maxAmmo, BufferedImage texture){
        this.damage = damage;
        this.firerate = firerate;
        this.maxAmmo = maxAmmo;
        this.texture = texture;
    }

    public void setShooter(EntityBase shooter) {
        this.shooter = shooter;
    }

    public void onShoot(){

    }


}
