package main.world;

import java.util.ArrayList;

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

    private static final ArrayList<Trigger> triggers = new ArrayList<>();
    private static final ArrayList<Trigger>
            triggerAddCache = new ArrayList<>(),
            triggerRemoveCache = new ArrayList<>();

    public static void addTriggerCache(Trigger trigger){
        triggerAddCache.add(trigger);
    }

    public static void removeTriggerCache(Trigger trigger){
        triggerRemoveCache.add(trigger);
    }


}
