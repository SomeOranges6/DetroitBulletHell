package main.gameplay;

import main.entities.EntityBase;

import java.awt.image.BufferedImage;

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

    public void onShoot(){

    }


}
