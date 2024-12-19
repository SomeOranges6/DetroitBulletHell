package main.world;

import main.BulletHellLogic;
import main.gameplay.Player;
import main.swing.GamePanel;
import main.world.levels.LevelBase;
import main.world.tiles.Tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;

import javax.imageio.ImageIO;

/**Handles the building and rendering of maps, will be replaced by room system **/
public class TileManager {

	
    public int mapNum = 1;
    BufferedImage tileSheet;
    LevelBase level;
 // Global variables
 	public static final int originalTileSize = 32;
 	/**Testing variable for adjusting the scale of the world **/
 	public static final int scale = 2;

 	/**How big each tile is after the scale is applied**/
     public static final int tileSize = originalTileSize * scale;
    	/**How much tiles are shown at once in the screen **/
   
    public TileManager(LevelBase level) {
    	// 8x8 grid, 64 tiles
        loadMap();
        handleTileEffects();
    }


    public void changeMap(int newMapNum) {
        if (mapNum != newMapNum) {
            mapNum = newMapNum;
            loadMap(); // Reload the map
        }
    }

    public void loadMap() {
        try {
        	//TODO: move this
            InputStream is = getClass().getResourceAsStream(level.tileSheetPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;

            while (col < level.height) { // Iterate over rows
                String line = br.readLine();
                String[] numbers = line.split(",");
                for (int row = 0; row < level.height;  row++) { // Iterate over columns
                	level.mapTileNum[row][col] = Integer.parseInt(numbers[row]);
                }
                col++; // Move to the next row
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void handleTileEffects(){
        for (int worldRow = 0; worldRow < level.width; worldRow++) {
            for (int worldCol = 0; worldCol < level.height; worldCol++) {
                int tileNum = level.mapTileNum[worldRow][worldCol];
                if(level.tile[tileNum].collision) {
	                Rectangle hitbox = new Rectangle(worldRow * tileSize, worldCol * tileSize, tileSize, tileSize);
	                BulletHellLogic.addCollidable(hitbox);
                }
            }
        }
    }

    public void draw(Graphics2D g2) {
        for (int worldRow = 0; worldRow < level.width; worldRow++) {
            for (int worldCol = 0; worldCol < level.height; worldCol++) {
                int tileNum = level.mapTileNum[worldCol][worldRow];

                int worldX = worldCol * tileSize;
                int worldY = worldRow * tileSize;

                int screenX = worldX - BulletHellLogic.player.x + Player.screenX;
                int screenY = worldY - BulletHellLogic.player.y + Player.screenY;

                if (worldX + tileSize > BulletHellLogic.player.x - Player.screenX &&
                    worldX - tileSize < BulletHellLogic.player.x + Player.screenX &&
                    worldY + tileSize > BulletHellLogic.player.y - Player.screenY &&
                    worldY - tileSize < BulletHellLogic.player.y + Player.screenY) {
                    g2.drawImage(level.tile[tileNum].image, screenX, screenY, tileSize, tileSize, null);
                }
            }
            //debug option to visualize collisions
            /*for(Rectangle rect : BulletHellLogic.collidablesGeneral) {
            	g2.setColor(Color.PINK);
            	g2.fillRect(rect.x - BulletHellLogic.player.x + Player.screenX, rect.y - BulletHellLogic.player.y + Player.screenY, rect.width, rect.height);
            }*/
        }
   }
}
