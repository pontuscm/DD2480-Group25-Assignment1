package se.kth.dd2480.group25.assignment1.lic;

import se.kth.dd2480.group25.assignment1.Coordinate;
import se.kth.dd2480.group25.assignment1.InputParameters;

import java.util.List;

/**
 * Launch Interceptor Condition #11
 * <p>
 * This condition is satisfied if there exists at least one set of two data points, (X[i],Y[i]) 
 * and (X[j],Y[j]), separated by exactly G_PTS consecutive intervening points, such that 
 * X[j] - X[i] < 0. (where i < j ) The condition is not met when NUMPOINTS < 3.
 * 1 ≤ G_PTS ≤ NUMPOINTS−2
 * <p>
 */
public class LaunchInterceptorCondition11 implements LaunchInterceptorCondition {
    @Override
    public boolean evaluate(List<Coordinate> coordinates, InputParameters inputParameters) {
        int g_pts = inputParameters.getG_pts();
        // Checks NUMPOINTS < 3 and 1 ≤ G_PTS ≤ NUMPOINTS−2
        if (coordinates.size() < 3 || g_pts < 1 || g_pts > coordinates.size() - 2) {
            return false;
        }

        for (int firstIndex = 0; firstIndex < coordinates.size() - g_pts - 1; ++firstIndex) {
            for (int secondIndex = firstIndex + 1 + g_pts; secondIndex < coordinates.size(); ++secondIndex) {
                // Checks that X[j] - X[i] < 0 for two indeces separated by g_pts (where i < j)
                if (coordinates.get(secondIndex).getX() - coordinates.get(firstIndex).getX() < 0) {
                    return true;
                }
            }
        }

        return false;
    }
}
