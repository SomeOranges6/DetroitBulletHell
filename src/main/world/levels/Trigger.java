package main.world.levels;

import java.awt.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

import main.entities.EnemyBase;
import main.entities.EntityBase;
import main.gameplay.Player;


/**Part of the map system, uses a very broad system, where any possible condition and action can be passed onto it **/
public class Trigger extends Rectangle {

	/**Whether or not the trigger despawns upon activation **/
    boolean persistent;
    
    Predicate<EntityBase> condition;
    Consumer<EntityBase> action;
    
    public int triggerType;

    public Trigger(int triggerType, Predicate<EntityBase> condition, Consumer<EntityBase> action) {
    	this(triggerType, condition, action, false);
    }
	public Trigger(int triggerType, Predicate<EntityBase> condition, Consumer<EntityBase> action, boolean persistent) {
		this.triggerType = triggerType;
		this.condition = condition;
		this.action = action;
		this.persistent = persistent;
	}

	public void handleAction(EntityBase entity){
    	//check for valid type and passed condition
    	if(typeCheck(entity) && condition.test(entity)) {
    		action.accept(entity);
    	}
    }
    
    
    public enum TriggerTypes{
    	player,
    	enemy,
    	any
    }

	public boolean typeCheck(EntityBase entity){
		return switch(triggerType) {
			case 0:
				if(entity instanceof Player)
					yield true;
			case 1:
				if(entity instanceof EnemyBase)
					yield true;

			case 2: yield true;
			default: yield false;
		};
	}
    
    
}
