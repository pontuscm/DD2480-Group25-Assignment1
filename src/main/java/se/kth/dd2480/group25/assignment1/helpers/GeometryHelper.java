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

    /**
     * Calculates the angle formed by three points on a two-dimensional plane, where the second point is the
     * vertex of the angle.
     *
     * @return The angle in radians
     */
    public static double getAngle(Coordinate first, Coordinate vertex, Coordinate last) {
        double sideA = distanceBetween(first, vertex);
        double sideB = distanceBetween(vertex, last);
        double sideC = distanceBetween(first, last);
        return Math.acos((Math.pow(sideA,2) + Math.pow(sideB,2) - Math.pow(sideC,2))/(2*sideA*sideB));
    }

    /**
     * Calculate the length of a line formed by 3 collinear points
     * 
     * @return the distance of the line formed by 3 collinear points
     */

     public static double lineLength(Coordinate a, Coordinate b, Coordinate c){
         double distanceAB = distanceBetween(a, b);
         double distanceAC = distanceBetween(a, c);
         double distanceCB = distanceBetween(c, b);

         double linelength = Math.max(Math.max(distanceAB, distanceAC),distanceCB);
         return linelength;
     }

    /**
     * Calculate the radius of the circumcircle of three points
     * 
     * @return the radius of the circumcircle of three points
     */

    public static double circumcircleRadius(Coordinate x, Coordinate y, Coordinate z){
        double a = distanceBetween(x, y);
        double b = distanceBetween(x, z);
        double c = distanceBetween(y, z);

        double radius = ( a * b * c ) / Math.sqrt(( a + b + c ) * ( a + b - c ) * ( a - b + c ) * ( -a + b + c ));
        return radius;
    }
    
    /**
     * Calculates the distance between two coordinates on a two-dimensional plane
     *
     * @return The total area of the triangle
     */
    public static double distance(Coordinate a, Coordinate b) {
        double xDiffSquared = Math.pow(a.getX() - b.getX(), 2);
        double yDiffSquared = Math.pow(a.getY() - b.getY(), 2);
        return Math.sqrt(xDiffSquared + yDiffSquared);
    }
}
