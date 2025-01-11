package main.gameplay;

import main.entities.EntityBase;

import java.awt.image.BufferedImage;

/**Mostly just a data holder, represents the weapons the player uses
 * Must set shooter in order to work**/
public class Weapon {

    EntityBase shooter;
    int damage;
    /** inversely proportional to how quickly the gun fires, with the formula being the modulo of the firerate by the central tick,
     * i.e the smaller it is, the faster it fires, minimum value is 1**/
    int firerate;

    BufferedImage texture;

    int maxAmmo, ammo;

    /**@param firerate is inversely proportional to how quickly the gun fires, i.e the smaller it is, the faster it fires, with the minimum value being 1**/
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
