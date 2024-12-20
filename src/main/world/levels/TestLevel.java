package main.world.levels;

import main.BulletHellLogic;
import main.world.LevelBase;
import main.world.tiles.Tile;

public class TestLevel extends LevelBase {

    public TestLevel (){
        mapTileNum = new int[BulletHellLogic.maxWorldCol][BulletHellLogic.maxWorldRow];
        tile = new Tile[64];
        tileSheetPath = "/assets/tileset1.png";
        mapPath = "/assets/maps/map1.txt";
        width = BulletHellLogic.maxWorldRow;
        height = BulletHellLogic.maxWorldCol;
    }
}
