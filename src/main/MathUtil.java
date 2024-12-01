package main;

import main.entities.EntityBase;
import main.gameplay.Player;

import java.awt.*;
import java.util.ArrayList;

public class MathUtil {

	
	/**Calculates the distance between two points
	 * @return An array with the distance on the x **/
	public static int[] distFrom(int[] startPoint, int[] endPoint) {
		int xDist = startPoint[0] - endPoint[0];
		int yDist = startPoint[1] - endPoint[1];
		return new int[]{xDist, yDist};
	}
	
	public static double[] distFrom(double[] startPoint, double[] endPoint) {
		double xDist = startPoint[0] - endPoint[0];
		double yDist = startPoint[1] - endPoint[1];
		return new double[]{xDist, yDist};
	}

	public static Rectangle checkForCollision(EntityBase entity, ArrayList<? extends Rectangle> list){
		Rectangle futurePosition = new Rectangle((int) (entity.mX + entity.vX), (int) (entity.mY + entity.vY), entity.width, entity.height);

		for(Rectangle rect : list){
			if(futurePosition.intersects(rect)){
				return rect;
			}
		}
		return null;
	}

	//public static boolean checkMapBounds(Rectangle rectangle){

	//}
	/**Used for rendering, subtract this from your x coordinate in order to get the render coordinates
	 * accounting for player offset **/
	public static int getXRenderOffset(){
		return BulletHellLogic.player.x + Player.screenX;
	}
	/**Used for rendering, subtract this from your y coordinate in order to get the render coordinates
	 * accounting for player offset **/
	public static int getYRenderOffset(){
		return BulletHellLogic.player.y + Player.screenY;
	}
}
