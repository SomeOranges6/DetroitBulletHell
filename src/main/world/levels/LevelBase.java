package main.world.levels;

import java.util.ArrayList;

import main.world.tiles.Tile;

public class LevelBase {
	
	/**The list of all possible tiles this map can have*/
    public Tile[] tile;
    /**The filepath for the texture of the map*/
    public String tileSheetPath;
    /**The list of all tiles this room has, by id **/
    public int[][] mapTileNum;
    public int height, width;
    public ArrayList<Trigger> triggers;
    
    


}
