package main.world.levels;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import main.world.tiles.Tile;

public class LevelBase {
	
	/**The list of all possible tiles this map can have **/
    public Tile[] tile;
    /**The texture for the map**/
    BufferedImage tileSheet;
    public HashMap<String, RoomBase> rooms;

    public String layout;


}
