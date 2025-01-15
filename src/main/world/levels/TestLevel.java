package main.world.levels;

import java.util.Random;

import main.BulletHellLogic;
import main.entities.enemies.TestEnemy;
import main.world.LevelBase;
import main.world.LevelManager;
import main.world.Trigger;
import main.world.TriggerFactory;
import main.world.tiles.Tile;

public class TestLevel extends LevelBase {
	static Random rand = new Random();
	
    public TestLevel (){
    	// Call the LevelBase constructor with the number of layers, width, and height
        super(5, BulletHellLogic.maxWorldCol, BulletHellLogic.maxWorldRow);

        mapTileNum = new int[BulletHellLogic.maxWorldCol][BulletHellLogic.maxWorldRow];
        tile = new Tile[64];
        
        tileSheetPath = "/assets/tilesets/tileset1.png";
        mapPath = "/assets/maps/map_layer1.csv";
        width = BulletHellLogic.maxWorldRow;
        height = BulletHellLogic.maxWorldCol;
        setupTriggers();
       
    }
    


    public void setupTriggers(){
        addTriggerCache(TriggerFactory.testSpawnTrigger(10 * LevelManager.tileSize,10 * LevelManager.tileSize));
        addTriggerCache(TriggerFactory.testSpawnTrigger(20 * LevelManager.tileSize,20 * LevelManager.tileSize));
        addTriggerCache(TriggerFactory.heavyTrigger(20 * LevelManager.tileSize,60 * LevelManager.tileSize));
        addTriggerCache(TriggerFactory.railgunnerTrigger(20 * LevelManager.tileSize, 100 * LevelManager.tileSize));
        addTriggerCache(TriggerFactory.railgunnerTrigger(60 * LevelManager.tileSize,60 * LevelManager.tileSize));
    }
}
