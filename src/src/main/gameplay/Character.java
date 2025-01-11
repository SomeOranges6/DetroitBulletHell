package main.gameplay;

import java.awt.image.BufferedImage;

/** Stores all the specifics of the player character, i.e
 * how much health it has, what weapons it can use, what abilities does it have
 * and the texture**/
public record Character(int health, int speed, Weapon[] weaponList, BufferedImage texture) {

    /**Defines the behavior of the ability when used **/
    public void onAbilityUse(Player player){

    }

}