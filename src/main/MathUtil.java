package main;

public class MathUtil {

	
	/**Calculates the distance between two points
	 * @return An array with  the distance on the x **/
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
}
