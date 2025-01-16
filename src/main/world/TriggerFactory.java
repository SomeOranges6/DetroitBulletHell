package main.world;

import main.BulletHellLogic;
import main.entities.EntityBase;
import main.entities.enemies.HeavyEnemy;
import main.entities.enemies.RailgunnerEnemy;
import main.entities.enemies.NormalEnemy;
import main.entities.enemies.BossEnemy;
import main.entities.enemies.ShotgunnerEnemy;

import java.awt.*;
import java.util.Random;
import java.util.function.Function;

import static main.BulletHellLogic.player;

public class TriggerFactory {

    static Random rand = new Random();
    
    public static Function<int[], EntityBase> radiusCheck = (coords) -> {
        Rectangle radius = new Rectangle(coords[0], coords[1], 400, 400);
        if (radius.intersects(player)){
            return player;
        }
        return null;
    };

    public static Trigger testSpawnTrigger(int x, int y) {
        Trigger spawn = new Trigger(radiusCheck, null);
        spawn.x = x;
        spawn.y = y;
        spawn.action = (player) -> {
            for (int i = 0; i < 3; i++) {
                BulletHellLogic.spawnEntity(new NormalEnemy(spawn.x + rand.nextInt(10) * 20, spawn.y + (rand.nextInt(10) + 5) * 50));
            }
            for (int i = 0; i < 2; i++) {
               BulletHellLogic.spawnEntity(new ShotgunnerEnemy(spawn.x + rand.nextInt(10) * 20, spawn.y + (rand.nextInt(10) + 5) * 50));
            }
        };
        return spawn;
    }
    public static Trigger heavyTrigger(int x, int y){
        Trigger spawn = new Trigger(radiusCheck, null);
        spawn.x = x;
        spawn.y = y;
        spawn.action = (player)-> {
            for (int i = 0; i < 5; i++) {
                BulletHellLogic.spawnEntity(new NormalEnemy(spawn.x + rand.nextInt(10) * 20, spawn.y + (rand.nextInt(10) + 5) * 50));
            }
            BulletHellLogic.spawnEntity(new HeavyEnemy(spawn.x + rand.nextInt(10) * 20, spawn.y + (rand.nextInt(10) + 5) * 50));
        };
        return spawn;
    }
    public static Trigger railgunnerTrigger(int x, int y){
        Trigger spawn = new Trigger(radiusCheck, null);
        spawn.x = x;
        spawn.y = y;
        spawn.action = (player)-> {
            for (int i = 0; i < 5; i++) {
                BulletHellLogic.spawnEntity(new NormalEnemy(spawn.x + rand.nextInt(10) * 20, spawn.y + (rand.nextInt(10) + 5) * 50));
            }
            BulletHellLogic.spawnEntity(new RailgunnerEnemy(spawn.x + rand.nextInt(10) * 20, spawn.y + (rand.nextInt(10) + 5) * 50));
        };
        return spawn;
    }
    public static Trigger shotgunnerTrigger(int x, int y){
        Trigger spawn = new Trigger(radiusCheck, null);
        spawn.x = x;
        spawn.y = y;
        spawn.action = (player)-> {
            for (int i = 0; i < 5; i++) {
                BulletHellLogic.spawnEntity(new NormalEnemy(spawn.x + rand.nextInt(10) * 20, spawn.y + (rand.nextInt(10) + 5) * 50));
            }
            BulletHellLogic.spawnEntity(new ShotgunnerEnemy(spawn.x + rand.nextInt(10) * 20, spawn.y + (rand.nextInt(10) + 5) * 50));
        };
        return spawn;
    }
    public static Trigger bossTrigger(int x, int y){
        Trigger spawn = new Trigger(radiusCheck, null);
        spawn.x = x;
        spawn.y = y;
        spawn.action = (player)-> {

            BulletHellLogic.spawnEntity(new BossEnemy(spawn.x + rand.nextInt(10) * 20, spawn.y + (rand.nextInt(10) + 5) * 50));
        };
        return spawn;
    
}
}
