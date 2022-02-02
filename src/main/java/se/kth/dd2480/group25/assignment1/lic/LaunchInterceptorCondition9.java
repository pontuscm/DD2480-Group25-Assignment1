package se.kth.dd2480.group25.assignment1.lic;

import se.kth.dd2480.group25.assignment1.Coordinate;
import se.kth.dd2480.group25.assignment1.InputParameters;
import se.kth.dd2480.group25.assignment1.helpers.Constants;
import se.kth.dd2480.group25.assignment1.helpers.GeometryHelper;

import java.util.List;

/**
 * Launch Interceptor Condition #9
 *
 * This condition is satisfied if there exist three points on the plane, separated by exactly
 * {@link InputParameters#getC_pts()} and {@link InputParameters#getD_pts()} consecutive intervening points,
 * which together form an angle either greater than the value of pi + {@link InputParameters#getEpsilon()},
 * or lesser than pi - {@link InputParameters#getEpsilon()}
 *
 * The second of the three points is always the vertex of the angle. If either the first
 * point or the last point (or both) coincides with the vertex, the angle is undefined and the LIC
 * is not satisfied by those three points.
 */
public class LaunchInterceptorCondition9 implements LaunchInterceptorCondition {

    /**
     * @param coordinates     List of x, y coordinates of the planar data points
     * @param inputParameters The input parameters to the decision system, containing the deviation from pi, and the
     *                        number ov intervening points
     * @return {@code true} if there exist three points on the plane, separated by exactly {@link InputParameters#getC_pts()}
     * and {@link InputParameters#getD_pts()} consecutive intervening points, which together form an angle
     * either greater than the value of pi + {@link InputParameters#getEpsilon()},
     * or lesser than pi - {@link InputParameters#getEpsilon()}, otherwise {@code false}
     */
    @Override
    public boolean evaluate(List<Coordinate> coordinates, InputParameters inputParameters) {
        int c = inputParameters.getC_pts();
        int d = inputParameters.getD_pts();
        //Stop if too few numpoints
        if (coordinates.size() < c + d + 3) {
            return false;
        }

        for (int i = c+1; i < coordinates.size()-(d+1); i++) {
            Coordinate first = coordinates.get(i-c-1);
            Coordinate vertex = coordinates.get(i);
            Coordinate last = coordinates.get(i+d+1);

            if (!first.equals(vertex) && !last.equals(vertex)) {
                if (GeometryHelper.getAngle(first, vertex, last) < (Constants.PI - inputParameters.getEpsilon())) {
                    return true;
                }
                if (GeometryHelper.getAngle(first, vertex, last) > (Constants.PI + inputParameters.getEpsilon())) {
                    return true;
                }
            }
        }

        return false;
    }
}