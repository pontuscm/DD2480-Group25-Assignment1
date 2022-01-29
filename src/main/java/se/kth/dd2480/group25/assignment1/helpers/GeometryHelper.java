package se.kth.dd2480.group25.assignment1.helpers;

import se.kth.dd2480.group25.assignment1.Coordinate;

/**
 * A helper class containing mathematical common methods useful in evaluating various Launch Interceptor Conditions
 */
public final class GeometryHelper {

	// Don't allow instantiation of fully static class
	private GeometryHelper() {
	}

	/**
	 * Calculates the area of a triangle based formed by 3 points on a two-dimensional plane
	 *
	 * @return The total area of the triangle
	 */
	public static double getTriangleArea(Coordinate first, Coordinate second, Coordinate third) {
		return Math.abs(first.getX() * (second.getY() - third.getY()) +
			                second.getX() * (third.getY() - first.getY()) +
			                third.getX() * (first.getY() - second.getY())) / 2;
	}
}
