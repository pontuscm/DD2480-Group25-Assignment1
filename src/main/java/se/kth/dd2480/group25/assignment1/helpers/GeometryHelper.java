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

    public static double distanceBetween(Coordinate a, Coordinate b){
        double xDis = a.getX() - b.getX();
        double yDis = a.getY() - b.getY();
        return Math.sqrt((xDis * xDis) + (yDis * yDis));
    }

    public static double getAngle(Coordinate first, Coordinate vertex, Coordinate last) {
        double sideA = distanceBetween(first, vertex);
        double sideB = distanceBetween(vertex, last);
        double sideC = distanceBetween(first, last);
        return Math.acos((Math.pow(sideA,2) + Math.pow(sideB,2) - Math.pow(sideC,2))/(2*sideA*sideB));
    }
}
