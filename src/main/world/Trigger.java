package main.world;

import main.entities.EntityBase;

import java.awt.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**Part of the map system, uses a very broad system, where any possible check and action can be passed onto it **/
public class Trigger extends Rectangle {

    /**Whether or not the trigger despawns upon activation **/
    boolean persistent;

    Supplier<EntityBase> actionCheck;
    Consumer<EntityBase> action;

    public int triggerType;

    public Trigger(int triggerType, Supplier<EntityBase> check, Consumer<EntityBase> action) {
        this(triggerType, check, action, false);
    }
    public Trigger(int triggerType, Supplier<EntityBase> check, Consumer<EntityBase> action, boolean persistent) {
        this.triggerType = triggerType;
        this.actionCheck = check;
        this.action = action;
        this.persistent = persistent;
    }

    public void handleAction(EntityBase entity) {
        EntityBase checkResult = actionCheck.get();
        if (checkResult != null) {
            action.accept(entity);
        }
    }


}
