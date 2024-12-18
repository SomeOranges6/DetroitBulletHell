package main.world.levels;

import java.util.HashMap;

import main.world.tiles.Tile;

public class LevelBase {
	
	/**The list of all possible tiles this map can have*/
    public Tile[] tile;
    /**The filepath for the texture of the map*/
    String tileSheetPath;
    public HashMap<String, RoomBase> rooms;

    /**Uses room ids to organize a layout*/
    public String layout;


}
