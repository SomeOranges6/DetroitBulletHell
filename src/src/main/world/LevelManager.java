package main.world;

import main.BulletHellLogic;
import main.gameplay.Player;
import main.gameplay.SpriteManager;
import main.world.tiles.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

/**Handles all the general logic associated with maps, such as rendering, collisions, and triggers **/
public class LevelManager {

    public int mapNum = 1;
    LevelBase level;

    public static final int originalTileSize = 32;
    public static final int scale = 1;
    public static final int tileSize = originalTileSize * scale;

    // List of tilesets and metadata
    private final List<Tileset> tilesets = new ArrayList<>();
    private final Map<Integer, Tile> tileIndexMap = new HashMap<>();

    public LevelManager(LevelBase level) {
    	this.level = level;
    	handleTileEffects();
        loadLevel(level);
    }

    public void loadLevel(LevelBase level) {
        this.level = level;
        loadTilesets();
        loadMapStructure();
        handleTileEffects(); // Add collision effects
    }


    private void loadTilesets() {
        try {
            // Load the combined tileset
            String combinedTilesetPath = "/assets/tilesets/tileset1.png";
            int tileWidth = 32;  // Width of a single tile
            int tileHeight = 32; // Height of a single tile
            int columns = 153;   // Number of columns in the combined tileset
            int rows = 92;      // Number of rows in the combined tileset (adjust as needed)

            BufferedImage tilesetImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(combinedTilesetPath)));

            // Populate the tileIndexMap with individual tiles
            int globalTileIndex = 0;
            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < columns; x++) {
                    if (y * tileWidth >= tilesetImage.getHeight()) {
                        break; // Prevent out-of-bounds access if rows*columns exceeds the tileset dimensions
                    }
                    Tile tile = new Tile();
                    tile.image = tilesetImage.getSubimage(
                            x * tileWidth,
                            y * tileHeight,
                            tileWidth,
                            tileHeight
                    );

                    tileIndexMap.put(globalTileIndex++, tile);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void updateLevel(){
        level.updateTriggers();
    }


    public void loadMapStructure() {
        try {
            for (int layer = 1; layer <= level.mapLayers.size(); layer++) { // Start from 1 to match file names
                InputStream is = getClass().getResourceAsStream("/assets/maps/map_layer" + layer + ".csv");
                if (is == null) {
                    throw new FileNotFoundException("File not found: /assets/maps/map_layer" + layer + ".csv");
                }

                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                int row = 0;

                while (row < level.height) {
                    String line = br.readLine();
                    if (line == null) break; // End of file
                    String[] numbers = line.split(",");
                    for (int col = 0; col < level.width; col++) {
                        level.mapLayers.get(layer - 1)[row][col] = Integer.parseInt(numbers[col]); // Adjust index for list
                    }
                    row++;
                }
                br.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void handleTileEffects() {
        int[][] collisionLayer = level.mapLayers.get(3); // Assuming layer 3 is for collision
        for (int row = 0; row < level.height; row++) {
            for (int col = 0; col < level.width; col++) {
                int tileNum = collisionLayer[row][col];
                Tile tile = tileIndexMap.get(tileNum);

                if (tile != null && tile.collision) {
                    Rectangle hitbox = new Rectangle(col * tileSize, row * tileSize, tileSize, tileSize);
                    BulletHellLogic.addCollidable(hitbox);
                }
            }
        }
    }



    public void draw(Graphics2D g2, SpriteManager playerSprite) {
        for (int layer = 0; layer < level.mapLayers.size(); layer++) {
            int[][] layerGrid = level.mapLayers.get(layer);
            for (int row = 0; row < level.height; row++) {
                for (int col = 0; col < level.width; col++) {
                    int tileNum = layerGrid[row][col];
                    Tile tile = tileIndexMap.get(tileNum);

                    if (tile != null) {
                        int worldX = col * tileSize;
                        int worldY = row * tileSize;

                        int screenX = worldX - BulletHellLogic.player.x + Player.screenX;
                        int screenY = worldY - BulletHellLogic.player.y + Player.screenY;

                        if (worldX + tileSize > BulletHellLogic.player.x - Player.screenX &&
                                worldX - tileSize < BulletHellLogic.player.x + Player.screenX &&
                                worldY + tileSize > BulletHellLogic.player.y - Player.screenY &&
                                worldY - tileSize < BulletHellLogic.player.y + Player.screenY) {
                            g2.drawImage(tile.image, screenX, screenY, tileSize, tileSize, null);
                        }
                    }
                }
            }

            // Draw the player after the second layer
            if (layer == 2) {
                int playerScreenX = Player.screenX;
                int playerScreenY = Player.screenY;
                double playerFacingAngle = BulletHellLogic.player.facingAngle;

                playerSprite.drawSprite(g2, playerFacingAngle, playerScreenX, playerScreenY);
            }
        }
    }



    // Inner class for Tileset metadata
    private static class Tileset {
        String path;
        int tileWidth, tileHeight, columns, rows;

        Tileset(String path, int tileWidth, int tileHeight, int columns, int rows) {
            this.path = path;
            this.tileWidth = tileWidth;
            this.tileHeight = tileHeight;
            this.columns = columns;
            this.rows = rows;
        }
    }
}