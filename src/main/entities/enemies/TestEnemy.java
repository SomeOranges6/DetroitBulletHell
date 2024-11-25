package main.entities.enemies;

import main.BulletHellLogic;
import main.entities.EnemyBase;

public class TestEnemy extends EnemyBase {

	public TestEnemy(int x, int y) {
		super(x, y);
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		if(BulletHellLogic.tick % 20 == 0) {
			attack();
		}
	}
	
	@Override
	public void move() {
		// TODO Auto-generated method stub
		super.move();
		
		
	}

}
