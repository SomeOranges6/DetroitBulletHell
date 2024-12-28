package main.world;

import java.util.ArrayList;

import main.BulletHellLogic;
import main.world.tiles.Tile;

public class LevelBase {
	
	/**The list of all possible tiles this map can have*/
    public Tile[] tile;
    /**The filepath for the texture of the map*/
    public String tileSheetPath;
    /**The filepath for the map itself*/
    public String mapPath;
    /**The list of all tiles this room has, by id */
    public int[][] mapTileNum;
    public int height, width;

    protected static final ArrayList<Trigger> triggers = new ArrayList<>();
    protected static final ArrayList<Trigger>
            triggerAddCache = new ArrayList<>(),
            triggerRemoveCache = new ArrayList<>();

    public static void addTriggerCache(Trigger trigger){
        triggerAddCache.add(trigger);
        BulletHellLogic.renderableAddCache.add(trigger);
    }

    public static void removeTriggerCache(Trigger trigger){
        triggerRemoveCache.add(trigger);
        BulletHellLogic.renderableRemoveCache.add(trigger);
    }

    public void updateTriggers(){

        triggers.removeAll(triggerRemoveCache);
        triggerRemoveCache.clear();
        triggers.addAll(triggerAddCache);
        triggerAddCache.clear();

        for(Trigger trigger : triggers){
            trigger.handleAction();
        }

    }
}
