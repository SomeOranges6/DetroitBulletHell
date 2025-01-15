package main.world;

import main.BulletHellLogic;
import main.entities.EntityBase;
import main.entities.enemies.HeavyEnemy;
import main.entities.enemies.TestEnemy;
import main.entities.enemies.EnemyA;
import main.entities.enemies.EnemyB;

import java.awt.*;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;

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

    public static Trigger testSpawnTrigger(int x, int y){
        Trigger spawn = new Trigger(radiusCheck, null);
        spawn.x = x;
        spawn.y = y;
        spawn.action = (player)-> BulletHellLogic.spawnEntity(new TestEnemy(spawn.x + rand.nextInt(10) * 20, spawn.y + rand.nextInt(10) * 20));
        return spawn;
    }
    public static Trigger heavyTrigger(int x, int y){
        Trigger spawn = new Trigger(radiusCheck, null);
        spawn.x = x;
        spawn.y = y;
        spawn.action = (player)-> BulletHellLogic.spawnEntity(new HeavyEnemy(spawn.x + rand.nextInt(10) * 20, spawn.y + rand.nextInt(10) * 20));
        return spawn;
    }
}
