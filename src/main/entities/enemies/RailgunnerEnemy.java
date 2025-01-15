package main.entities.enemies;

import main.BulletHellLogic;
import main.entities.EnemyBase;
import main.entities.projectiles.enemy.EvilProjectile;
import main.entities.projectiles.enemy.RailgunEvilProjectile;
import main.gameplay.Player;

import java.awt.*;

public class RailgunnerEnemy extends EnemyBase {

    public RailgunnerEnemy(int x, int y) {
        super(x, y, 30, 30);
        speed = 12;
        health = 20;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if(health <= 0) onDead();
        if(BulletHellLogic.tick % 120 == 0) {
            attack();
        }
    }

    /**
     * Spawns projectiles at a one second interval
     */
    @Override
    public void attack() {
        RailgunEvilProjectile testProjectile = new RailgunEvilProjectile(x, y);
        this.lookAtPlayer();
        testProjectile.setShooter(this);
        BulletHellLogic.spawnEntity(testProjectile);

    }

    @Override
    public void move() {
        super.move();
        x = (int) mX;
        y = (int) mY;
        //acording to all known laws of aviation, bees should not be able to fly. They of course, fly anyway. This is because bees dont give a shit about what scientists think. Yellow black yellow black yellow black, oooh black and yellow, lets shake it up a little. Barry breakfast is ready. Coming mom. Barry how many times have I told you not to fly down the stairs?! Im sorry, im just so excited. hi its me jerry seinfeld and ihave no respect for myself
    }


    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.ORANGE);
        g.fillRect(x - BulletHellLogic.player.x + Player.screenX, y - BulletHellLogic.player.y + Player.screenY, width, height);
    }
}
