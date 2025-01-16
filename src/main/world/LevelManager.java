package main.world;

import main.BulletHellLogic;
import main.gameplay.Player;
import main.gameplay.SpriteManager;
import main.gameplay.HealthBar;
import main.world.tiles.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class LevelManager {

    public int mapNum = 1;
    LevelBase level;

    public static final int originalTileSize = 32;
    public static final int scale = 1;
    public static final int tileSize = originalTileSize * scale;

    // List of tilesets and metadata
    private final List<Tileset> tilesets = new ArrayList<>();
    private final Map<Integer, Tile> tileIndexMap = new HashMap<>();

    // Add a HealthBar instance
    private final HealthBar healthBar;

    public LevelManager(LevelBase level) {
        this.level = level;
        this.healthBar = new HealthBar(200, 20, Color.GREEN, Color.GRAY); // Health bar dimensions and colors
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
            String combinedTilesetPath = "/assets/tilesets/tileset1.png";
            int tileWidth = 32;
            int tileHeight = 32;
            int columns = 153;
            int rows = 92;

            BufferedImage tilesetImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(combinedTilesetPath)));

            int globalTileIndex = 0;
            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < columns; x++) {
                    if (y * tileWidth >= tilesetImage.getHeight()) {
                        break;
                    }
                    Tile tile = new Tile();
                    tile.image = tilesetImage.getSubimage(x * tileWidth, y * tileHeight, tileWidth, tileHeight);

                    tileIndexMap.put(globalTileIndex++, tile);
                }
            }

            int[][] layer3 = level.mapLayers.get(2);
            for (int row = 0; row < level.height; row++) {
                for (int col = 0; col < level.width; col++) {
                    int tileNum = layer3[row][col];
                    Tile tile = tileIndexMap.get(tileNum);
                    if (tile != null) {
                        tile.collision = true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateLevel() {
        level.updateTriggers();
    }

    public void loadMapStructure() {
        try {
            for (int layer = 1; layer <= level.mapLayers.size(); layer++) {
                InputStream is = getClass().getResourceAsStream("/assets/maps/map_layer" + layer + ".csv");
                if (is == null) {
                    throw new FileNotFoundException("File not found: /assets/maps/map_layer" + layer + ".csv");
                }

                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                int row = 0;

                while (row < level.height) {
                    String line = br.readLine();
                    if (line == null) break;
                    String[] numbers = line.split(",");
                    for (int col = 0; col < level.width; col++) {
                        level.mapLayers.get(layer - 1)[row][col] = Integer.parseInt(numbers[col]);
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
        int[][] layer3 = level.mapLayers.get(2);

        for (int row = 0; row < layer3.length; row++) {
            for (int col = 0; col < layer3[row].length; col++) {
                int tileId = layer3[row][col];
                Tile tile = tileIndexMap.get(tileId);

                if (tile != null && !tile.collision) {
                    int worldX = col * LevelManager.tileSize;
                    int worldY = row * LevelManager.tileSize;

                    Rectangle collidable = new Rectangle(worldX, worldY, LevelManager.tileSize, LevelManager.tileSize);
                    BulletHellLogic.addCollidable(collidable);
                }
            }
        }
    }

    public void draw(Graphics2D g2, SpriteManager playerSprite) {
        int screenTileCols = (int) Math.ceil((double) BulletHellLogic.screenWidth / tileSize);
        int screenTileRows = (int) Math.ceil((double) BulletHellLogic.screenHeight / tileSize);

        int minOffsetX = Math.max((BulletHellLogic.player.x / tileSize) - (screenTileCols / 2) - 1, 0);
        int minOffsetY = Math.max((BulletHellLogic.player.y / tileSize) - (screenTileRows / 2) - 1, 0);

        int maxOffsetX = Math.min(minOffsetX + screenTileCols + 5, level.width);
        int maxOffsetY = Math.min(minOffsetY + screenTileRows + 5, level.height);

        for (int layer = 0; layer < level.mapLayers.size(); layer++) {
            int[][] layerGrid = level.mapLayers.get(layer);

            for (int row = minOffsetY; row < maxOffsetY; row++) {
                for (int col = minOffsetX; col < maxOffsetX; col++) {
                    int tileNum = layerGrid[row][col];
                    if (tileNum != -1) {
                        Tile tile = tileIndexMap.get(tileNum);

                        if (tile != null) {
                            int worldX = col * tileSize;
                            int worldY = row * tileSize;

                            int screenX = worldX - BulletHellLogic.player.x + (BulletHellLogic.screenWidth / 2);
                            int screenY = worldY - BulletHellLogic.player.y + (BulletHellLogic.screenHeight / 2);

                            g2.drawImage(tile.image, screenX, screenY, tileSize, tileSize, null);
                        }
                    }
                }
            }

            if (layer == 2) {
                int playerScreenX = BulletHellLogic.screenWidth / 2;
                int playerScreenY = BulletHellLogic.screenHeight / 2;
                double playerFacingAngle = BulletHellLogic.player.facingAngle;

                playerSprite.drawSprite(g2, playerFacingAngle, playerScreenX, playerScreenY);
            }
        }

        // Draw the health bar at the top layer
        healthBar.draw(g2);
    }

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
