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

	/**Checks an entity against a list of objects with hitboxes (rectangles), to see if any collide
	 * then returns the entity it collided with
	 * @param entity The entity to check against the list
	 * @param list A list of any class that extends Rectangle (i.e has a hitbox)
	 * @return whichever entity it collided with**/
	public static Rectangle checkForCollidedEntity(EntityBase entity, ArrayList<? extends Rectangle> list){
		Rectangle futurePosition = new Rectangle((int) (entity.mX + entity.vX), (int) (entity.mY + entity.vY), entity.width, entity.height);

		for(Rectangle rect : list){
			if(futurePosition.intersects(rect)){
				return rect;
			}
		}
		return null;
	}
	/** Same as checkForCollidedEntity, except does not return anything, if you do not need to know what it collided with **/
	public static boolean checkForCollision(EntityBase entity, ArrayList<? extends Rectangle> list){
		Rectangle futurePosition = new Rectangle((int) (entity.mX + entity.vX), (int) (entity.mY + entity.vY), entity.width, entity.height);

		for(Rectangle rect : list){
			if(futurePosition.intersects(rect)){
				return true;
			}
		}
		return false;
	}

	/**A variation of checkForCollision, except allows you to select which xAxis' motion gets utilized
	 * Used for cases like the player, which may still want to move up or down, even if left or right have collided
	 * @param xAxis true for x, false for y**/
	public static boolean checkForCollision(EntityBase entity, ArrayList<? extends Rectangle> list, boolean xAxis){
		int x = (int) (xAxis ? entity.mX + entity.vX : entity.mX);
		int y = (int) (!xAxis ? entity.mY + entity.vY : entity.mY);

		Rectangle futurePosition = new Rectangle(x, y, entity.width, entity.height);

		for(Rectangle rect : list){
			if(futurePosition.intersects(rect)){
				return true;
			}
		}
		return false;
	}

	//public static boolean checkMapBounds(Rectangle rectangle){

	//}
}
