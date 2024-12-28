package main.world.levels;

import main.BulletHellLogic;
import main.world.LevelBase;
import main.world.LevelManager;
import main.world.TriggerFactory;
import main.world.tiles.Tile;

public class TestLevel extends LevelBase {

    public TestLevel (){
        mapTileNum = new int[BulletHellLogic.maxWorldCol][BulletHellLogic.maxWorldRow];
        tile = new Tile[64];
        tileSheetPath = "/assets/tileset1.png";
        mapPath = "/assets/maps/map1.txt";
        width = BulletHellLogic.maxWorldRow;
        height = BulletHellLogic.maxWorldCol;
        setupTriggers();
    }


    public void setupTriggers(){
        addTriggerCache(TriggerFactory.testSpawnTrigger(10 * LevelManager.tileSize,10 * LevelManager.tileSize));
        addTriggerCache(TriggerFactory.testSpawnTrigger(20 * LevelManager.tileSize,20 * LevelManager.tileSize));
    }
}
