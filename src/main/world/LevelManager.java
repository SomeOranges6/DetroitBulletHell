package main.world;

import main.BulletHellLogic;
import main.gameplay.Player;
import main.world.tiles.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;

/**Handles all the general logic associated with maps, such as rendering, collisions, and triggers **/
public class LevelManager {

    public int mapNum = 1;
    BufferedImage tileSheet;
    LevelBase level;

 	public static final int originalTileSize = 32;
 	/**Testing variable for adjusting the scale of the world **/
 	public static final int scale = 2;

 	/**How big each tile is after the scale is applied**/
     public static final int tileSize = originalTileSize * scale;
   
    public LevelManager(LevelBase level) {
    	loadLevel(level);
    }

    public void loadLevel(LevelBase level) {
        this.level = level;
        getTileImage();
        loadMapStructure();
        handleTileEffects();
    }

    public void loadMapStructure() {
        try {
        	//TODO: move this
            InputStream is = getClass().getResourceAsStream(level.mapPath);
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

    public void getTileImage() {
        try {
            tileSheet = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(level.tileSheetPath)));
            int tileIndex = 0;
            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 7; y++) {
                    level.tile[tileIndex] = new Tile();
                    level.tile[tileIndex].image = tileSheet.getSubimage(x * 32, y * 32, 32, 32);

                    // Set collision properties
                    //TODO: replace with more extendable check later
                    if (tileIndex == 32 || tileIndex == 54) {
                        level.tile[tileIndex].collision = true;
                    }
                    /** // Set changeMap for tile 0
                     if (tileIndex == 0) {
                     tile[tileIndex].changeMap = true;
                     }**/
                    tileIndex++;
                }
            }
        } catch (IOException e) {
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
