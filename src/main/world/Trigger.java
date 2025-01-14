package main.world;

import main.BulletHellLogic;
import main.entities.EntityBase;
import main.gameplay.Player;

import java.awt.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**Part of the map system, uses a very broad system, where any possible check and action can be passed onto it **/
public class Trigger extends EntityBase {

    /**Whether or not the trigger despawns upon activation **/
    boolean persistent;

    public Function<int[], EntityBase> actionCheck;
    public Consumer<EntityBase> action;

    public Trigger(Function<int[], EntityBase> check, Consumer<EntityBase> action) {
        this(check, action, false);
    }
    public Trigger(Function<int[], EntityBase> check, Consumer<EntityBase> action, boolean persistent) {
        super(0,0);

        this.actionCheck = check;
        this.action = action;
        this.persistent = persistent;
    }

    public void handleAction() {
        EntityBase checkResult = actionCheck.apply(new int[]{x,y});
        if (checkResult != null) {
            action.accept(checkResult);
            if(!persistent){
                LevelBase.removeTriggerCache(this);
            }
        }
    }


    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.ORANGE);
        g.fillRect(x - BulletHellLogic.player.x + Player.screenX, y - BulletHellLogic.player.y + Player.screenY,20,20);
    }
}
