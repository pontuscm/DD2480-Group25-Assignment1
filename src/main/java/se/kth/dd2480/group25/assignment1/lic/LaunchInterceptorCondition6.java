package se.kth.dd2480.group25.assignment1.lic;

import se.kth.dd2480.group25.assignment1.Coordinate;
import se.kth.dd2480.group25.assignment1.InputParameters;
import se.kth.dd2480.group25.assignment1.helpers.GeometryHelper;

import java.util.List;

/**
 * Launch Interceptor Condition #6
 * <p>
 * This condition is satisfied if there exists at least one set of N_PTS consecutive data 
 * points such that at least one of the points lies a distance greater than DIST from the 
 * line joining the first and last of these N_PTS points. If the first and last points of 
 * these N_PTS are identical, then the calculated distance to compare with DIST will be 
 * the distance from the coincident point to all other points of the N PTS consecutive 
 * points. The condition is not met when NUMPOINTS < 3.
 * (3 ≤ N_PTS ≤ NUMPOINTS), (0 ≤ DIST)
 * <p>
 */
public class LaunchInterceptorCondition6 implements LaunchInterceptorCondition {
    @Override
    public boolean evaluate(List<Coordinate> coordinates, InputParameters inputParameters) {
        int n_pts = inputParameters.getN_pts();
        double dist = inputParameters.getDist();
        // Checks NUMPOINTS < 3 and 3 ≤ N_PTS ≤ NUMPOINTS
        if (coordinates.size() < 3 || n_pts < 3 || n_pts > coordinates.size() || dist < 0) {
            return false;
        }

        // Coordinates a and b make up a line. Its distance to c is compared to DIST. 
        for (int aIndex = 0; aIndex < coordinates.size() - n_pts + 1; aIndex++) {
            int bIndex = aIndex + n_pts - 1;
            
            if (coordinates.get(aIndex).getX() == coordinates.get(bIndex).getX() && coordinates.get(aIndex).getY() == coordinates.get(bIndex).getY()) {
                for (int cIndex = aIndex + 1; cIndex < bIndex; cIndex++) {
                    if (GeometryHelper.distanceBetween(coordinates.get(aIndex), coordinates.get(cIndex)) > dist) {
                        return true;
                    }
                }
            } else {
                for (int cIndex = aIndex + 1; cIndex < bIndex; cIndex++) {
                    if (GeometryHelper.distanceBetween(coordinates.get(aIndex), coordinates.get(cIndex)) > dist && GeometryHelper.distanceBetween(coordinates.get(bIndex), coordinates.get(cIndex)) > dist) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
