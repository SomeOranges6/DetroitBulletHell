package main;

public class MathUtil {
	
	/**Calculates the distance between two points
	 * @return An array with the distance on the x **/
	public int distFrom(int[] startPoint, int[] endPoint) {
		int xDist = startPoint[0] - endPoint[0];
		int yDist = startPoint[1] - endPoint[1];
		
		double totalDist = Math.hypot(xDist, yDist); 
		return (int)totalDist;
	}
	
	
}