package main;

import main.entities.EntityBase;

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

	public static boolean checkForCollision(EntityBase entity, ArrayList<? extends Rectangle> list){
		Rectangle futurePosition = new Rectangle((int) (entity.mX + entity.vX), (int) (entity.mY + entity.vY), entity.width, entity.height);

		for(Rectangle rect : list){
			if(futurePosition.intersects(rect)){
				return true;
			}
		}
		return false;
	}
}
