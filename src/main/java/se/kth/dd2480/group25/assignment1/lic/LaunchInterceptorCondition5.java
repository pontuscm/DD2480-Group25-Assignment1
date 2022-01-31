package se.kth.dd2480.group25.assignment1.lic;

import se.kth.dd2480.group25.assignment1.Coordinate;
import se.kth.dd2480.group25.assignment1.InputParameters;

import java.util.List;

/**
 * Launch Interceptor Condition #5
 * <p>
 * This condition is satisfied if there exists at least one set of two consecutive data points, (X[i],Y[i]) and (X[j],Y[j]), such
 * that X[j] - X[i] < 0. (where i = j-1).
 * <p>
 */
public class LaunchInterceptorCondition5 implements LaunchInterceptorCondition {
    @Override
    public boolean evaluate(List<Coordinate> coordinates, InputParameters inputParameters) {
        for (int firstIndex = 0; firstIndex + 1 < coordinates.size(); ++firstIndex) {
            int secondIndex = firstIndex + 1;

            // Checks that X[j] - X[i] < 0 for consecutive indeces (where i = j-1)
            if (coordinates.get(secondIndex) - coordinates.get(firstIndex) < 0) {
                return true;
            }
        }
        return false;
    }
}
