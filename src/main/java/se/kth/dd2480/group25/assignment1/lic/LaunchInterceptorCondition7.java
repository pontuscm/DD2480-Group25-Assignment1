package se.kth.dd2480.group25.assignment1.lic;

import se.kth.dd2480.group25.assignment1.Coordinate;
import se.kth.dd2480.group25.assignment1.InputParameters;
import se.kth.dd2480.group25.assignment1.helpers.GeometryHelper;

import java.util.List;

/**
 * Launch Interceptor Condition #3
 * <p>
 * This condition is satisfied if there exists at least one set of two data points separated by a certain number of
 * consecutive intervening points that are a distance greater than length1 apart. The condition
 * is not met when the number of points is less than 3.
 */
public class LaunchInterceptorCondition7 implements LaunchInterceptorCondition {

    /**
     * @param coordinates     List of x, y coordinates of the planar data points
     * @param inputParameters The input parameters to the decision system, containing the number of intervening data
     *                        points and the number of points
     * @return {@code true} at least one set of two data points separated by {@link InputParameters#getK_pts()}
     *  consecutive intervening points that are a distance greater than {@link InputParameters#getLength1()} apart,
     *  otherwise {@code false}
     */
    @Override
    public boolean evaluate(List<Coordinate> coordinates, InputParameters inputParameters) {
        if (inputParameters.getN_pts() < 3 || inputParameters.getK_pts() < 0 || inputParameters.getK_pts() > inputParameters.getN_pts() - 2) {
            return false;
        }

        int k_pts = inputParameters.getK_pts();

        // Iteration starts at index K_PTS and looks back to the data point that has k_pts intervening
        // data points to the one we are currently looking at.
        for (int i = k_pts+1; i < coordinates.size(); i++) {
            Coordinate a = coordinates.get(i-k_pts-1);
            Coordinate b = coordinates.get(i);
            double distance = GeometryHelper.distance(a, b);
            if (distance > inputParameters.getLength1()){
                return true;
            }
        }
        return false;
    }

}
