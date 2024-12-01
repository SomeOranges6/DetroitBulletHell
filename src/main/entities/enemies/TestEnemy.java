package main.entities.enemies;

import java.awt.Color;
import java.awt.Graphics2D;

import main.BulletHellLogic;
import main.MathUtil;
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

	/**Finds the direction the player is at and moves toward it **/
	@Override
	public void move() {
		double[] direction = MathUtil.distFrom(new double[]{mX, mY}, new double[]{BulletHellLogic.player.mX, BulletHellLogic.player.mY});
		vX = direction[0];
		vY = direction[1];

		mX += vX;
		mY += vY;

		x = (int) mX;
		y = (int) mY;
	}
	
	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.RED);
		g.drawRect(x, y, width, height);
	}

}
