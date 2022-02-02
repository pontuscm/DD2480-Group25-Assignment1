package se.kth.dd2480.group25.assignment1.lic;

import se.kth.dd2480.group25.assignment1.Coordinate;
import se.kth.dd2480.group25.assignment1.InputParameters;
import se.kth.dd2480.group25.assignment1.helpers.GeometryHelper;

import java.util.List;

/**
 * Launch Interceptor Condition #3
 * <p>
 * This condition is satisfied if there exist three consecutive points on the plane which together form a triangle
 * with area greater than the value of {@link InputParameters#getArea1()}
 */
public class LaunchInterceptorCondition0 implements LaunchInterceptorCondition {

    /**
     * @param coordinates     List of x, y coordinates of the planar data points
     * @param inputParameters The input parameters to the decision system, containing the minimum required triangle area
     * @return {@code true} if there exists at least one set of two consecutive data points that are
     * a distance greater than {@link InputParameters#getLength1()} apart, otherwise {@code false}
     */
    @Override
    public boolean evaluate(List<Coordinate> coordinates, InputParameters inputParameters) {
        // Using previous coordinate variable to avoid duplicate list lookups
        Coordinate prevCoordinate = coordinates.get(0);

        // Iterating through all coordinates to check all coordinate pairs
        for (int i = 1; i < coordinates.size(); i++) {
            Coordinate coordinate = coordinates.get(i);
            double distance = GeometryHelper.distance(coordinate, prevCoordinate);
            if (distance > inputParameters.getLength1()){
                return true;
            }
            prevCoordinate = coordinate;
        }

        return false;
    }

}
