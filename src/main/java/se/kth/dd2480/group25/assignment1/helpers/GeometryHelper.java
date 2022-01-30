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

     /**
     * Check if three points are collinear
     *
     * @return ture or false
     */

     public static boolean isCollinear(Coordinate a, Coordinate b, Coordinate c) {
        double slopeAB = (b.getY() - a.getY()) / (b.getX() - a.getX());
        double slopeBC = (c.getY() - b.getY()) / (c.getX() - b.getX());
        double slopeAC = (c.getY() - a.getY()) / (c.getX() - a.getX());

        if( Double.compare(slopeAB, slopeAC) == 0 ||
            Double.compare(slopeBC, slopeAC) == 0 ||
            Double.compare(slopeAB, slopeBC) == 0){
                return true;
            }
        return false;
     }

    /**
     * Calculate the distance between point a and b
     * 
     * @return the distance between point a and b
     */

    public static double distanceBetween(Coordinate a, Coordinate b){
        double xDis = a.getX() - b.getX();
        double yDis = a.getY() - b.getY();
        return Math.sqrt((xDis * xDis) + (yDis * yDis));
    }
    
}
