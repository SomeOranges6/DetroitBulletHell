package main.gameplay;

import java.awt.image.BufferedImage;

public record Weapon(int damage, int firerate, BufferedImage texture) {

    /** Will typically spawn a projectile when shot **/
    public void onShoot(){

    }
}
