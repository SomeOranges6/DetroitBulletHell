package main.world;

import main.BulletHellLogic;
import main.swing.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;

import javax.imageio.ImageIO;

/**Handles the building and rendering of maps, TBD whether it will be replaced by room system **/
public class TileManager {

    GamePanel gp = BulletHellLogic.gPanel;
    public Tile[] tile;
    public int[][] mapTileNum;
    public int mapNum = 1;
    BufferedImage tileSheet;

    public TileManager() {
        mapTileNum = new int[BulletHellLogic.maxWorldCol][BulletHellLogic.maxWorldRow];
        tile = new Tile[64]; // 8x8 grid, 64 tiles
        getTileImage();
        loadMap();
    }

    public void getTileImage() {
        try {
            tileSheet = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/tileset1.png")));
            int tileIndex = 0;
            for (int y = 0; y < 8; y++) {
                for (int x = 0; x < 8; x++) {
                    tile[tileIndex] = new Tile();
                    tile[tileIndex].image = tileSheet.getSubimage(x * 32, y * 32, 32, 32);

                    // Set collision properties
                    //TODO: replace with more extendable check later
                    if (tileIndex == 56 || tileIndex == 54) {
                        tile[tileIndex].collision = true;
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

    public void changeMap(int newMapNum) {
        if (mapNum != newMapNum) {
            mapNum = newMapNum;
            loadMap(); // Reload the map
        }
    }

    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/maps/map" + mapNum + ".txt");
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int row = 0;

            while (row < BulletHellLogic.maxWorldRow) { // Iterate over rows
                String line = br.readLine();
                String[] numbers = line.split(" ");
                for (int col = 0; col < BulletHellLogic.maxWorldCol; col++) { // Iterate over columns
                    mapTileNum[col][row] = Integer.parseInt(numbers[col]);
                }
                row++; // Move to the next row
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void addCollisionBounds(){
        for (int worldRow = 0; worldRow < BulletHellLogic.maxWorldRow; worldRow++) {
            for (int worldCol = 0; worldCol < BulletHellLogic.maxWorldCol; worldCol++) {
                int tileNum = mapTileNum[worldCol][worldRow];
                /* TODO: finish off tile collision logic by making rectangles with the dimensions of a tile
                 *  i.e 32 by 32, and then add that to the BulletHellLogic collision list
                 */
            }
        }
    }

    public void draw(Graphics2D g2) {
        for (int worldRow = 0; worldRow < BulletHellLogic.maxWorldRow; worldRow++) {
            for (int worldCol = 0; worldCol < BulletHellLogic.maxWorldCol; worldCol++) {
                int tileNum = mapTileNum[worldCol][worldRow];

                int worldX = worldCol * BulletHellLogic.tileSize;
                int worldY = worldRow * BulletHellLogic.tileSize;

                int screenX = worldX - BulletHellLogic.player.x + BulletHellLogic.player.screenX;
                int screenY = worldY - BulletHellLogic.player.x + BulletHellLogic.player.screenY;

                if (worldX + BulletHellLogic.tileSize > BulletHellLogic.player.x - BulletHellLogic.player.screenX &&
                    worldX - BulletHellLogic.tileSize < BulletHellLogic.player.x + BulletHellLogic.player.screenX &&
                    worldY + BulletHellLogic.tileSize > BulletHellLogic.player.x - BulletHellLogic.player.screenY &&
                    worldY - BulletHellLogic.tileSize < BulletHellLogic.player.x + BulletHellLogic.player.screenY) {
                    g2.drawImage(tile[tileNum].image, screenX, screenY, BulletHellLogic.tileSize, BulletHellLogic.tileSize, null);
                }
            }
        }
    }
}
