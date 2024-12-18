package main.world.levels;

import java.awt.*;


/**Part of the map system, if an entity enters this radius, an event will occur, be it spawning enemies, or damaging the entity  **/
public class Trigger extends Rectangle {

	/**Whether or not the trigger despawns upon activation **/
    boolean persistent;
    
 
    
    public void handleAction(){

    }
    
    public static enum TriggerTypes{
    	player,
    	enemy,
    	any
    }
}
