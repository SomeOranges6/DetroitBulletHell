package main.world.levels;

import java.awt.image.BufferedImage;

import main.world.tiles.Tile;

public class LevelBase {
	
	/**The list of all possible tiles this map can have **/
    public Tile[] tile;
    /**The list of all tiles this map has, by id **/
    public int[][] mapTileNum;
    /**The texture for the map**/
    BufferedImage tileSheet;

}
