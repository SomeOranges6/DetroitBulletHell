package main.world.levels;

import java.awt.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

import main.entities.EnemyBase;
import main.entities.EntityBase;
import main.gameplay.Player;


/**Part of the map system, if an entity enters this radius, an event will occur, be it spawning enemies, or damaging the entity  **/
public class Trigger extends Rectangle {

	/**Whether or not the trigger despawns upon activation **/
    boolean persistent;
    
    Predicate<EntityBase> condition;
    Consumer<EntityBase> action;
    
    public int triggerType;
    
    public Trigger(int triggerType, Predicate<EntityBase> condition, Consumer<EntityBase> action) {
    	
    }
    
    public void handleAction(EntityBase entity){
    	//check for valid type
    	
    	boolean typeCheck = switch(triggerType) {
    		case 0:
    			if(entity instanceof Player)
    				yield true;
    			
  
    		case 1:
    			if(entity instanceof EnemyBase)
    				yield true;
    		
    		case 2: yield true;
    			
    		default:
    			yield false;
    		
    		
    	};
    	
    	if(condition.test(entity)) {
    		action.accept(entity);
    	}
    }
    
    
    public static enum TriggerTypes{
    	player,
    	enemy,
    	any
    }
    
    
}
