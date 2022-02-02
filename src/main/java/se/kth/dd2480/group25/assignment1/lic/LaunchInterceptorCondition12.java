package se.kth.dd2480.group25.assignment1.lic;

import se.kth.dd2480.group25.assignment1.Coordinate;
import se.kth.dd2480.group25.assignment1.InputParameters;
import se.kth.dd2480.group25.assignment1.helpers.GeometryHelper;

import java.util.List;

/**
 * Launch Interceptor Condition #3
 * <p>
 * This condition is satisfied if there exists at least one set of two data points separated by a certain number of
 * consecutive intervening points that are a distance greater than length1 apart. The condition also requires at least
 * two data points that a distance less than length2. The condition is not met when the number of points is less than 3.
 */
public class LaunchInterceptorCondition12 implements LaunchInterceptorCondition {

    /**
     * @param coordinates     List of x, y coordinates of the planar data points
     * @param inputParameters The input parameters to the decision system, containing the number of intervening data
     *                        points, the number of datapoints, as well as the length params to compare to
     * @return {@code true} if at least one set of two data points separated by {@link InputParameters#getK_pts()}
     *  consecutive intervening points that are a distance greater than {@link InputParameters#getLength1()} apart and
     *  at least one set of two data points separated by {@link InputParameters#getK_pts()} consecutive intervening
     *  points that are a distance less than {@link InputParameters#getLength2()} apart, otherwise {@code false}
     */
    @Override
    public boolean evaluate(List<Coordinate> coordinates, InputParameters inputParameters) {
        if (inputParameters.getN_pts() < 3) {
            return false;
        }

        int k_pts = inputParameters.getK_pts();

        boolean condition1 = false; // If there exists a pair of data points with a distance greater than length1
        boolean condition2 = false; // If there exists a pair of data points with a distance less than length2

        // Iteration starts at index K_PTS and looks back to the data point that has k_pts intervening
        // data points to the one we are currently looking at.
        for (int i = k_pts+1; i < coordinates.size(); i++) {
            Coordinate a = coordinates.get(i-k_pts-1);
            Coordinate b = coordinates.get(i);
            double distance = GeometryHelper.distance(a, b);
            if (distance > inputParameters.getLength1()){
                condition1 = true;
                if (condition2) break;
            }
            if (distance < inputParameters.getLength2()) {
                condition2 = true;
                if (condition1) break;
            }
        }
        return condition1 && condition2;
    }

}