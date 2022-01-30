package se.kth.dd2480.group25.assignment1.lic;

import com.google.common.math.DoubleMath;
import se.kth.dd2480.group25.assignment1.Coordinate;
import se.kth.dd2480.group25.assignment1.InputParameters;
import se.kth.dd2480.group25.assignment1.helpers.Constants;
import se.kth.dd2480.group25.assignment1.helpers.GeometryHelper;

import java.util.List;

/**
 * Launch Interceptor Condition #10
 * <p>
 * This condition is satisfied if there exist three points x, y, z on the plane, which together form a triangle with
 * area greater than the value of {@link InputParameters#getArea1()}.
 * <p>
 * Additionally, points x and y must be separated by exactly {@link InputParameters#getE_pts()} intermediary points
 * and points y and z must be separated by exactly {@link InputParameters#getF_pts()} intermediary points.
 */
public class LaunchInterceptorCondition10 implements LaunchInterceptorCondition {
    @Override
    public boolean evaluate(List<Coordinate> coordinates, InputParameters inputParameters) {
        int firstOffset = inputParameters.getE_pts();
        int secondOffset = inputParameters.getF_pts();
        for (int firstIndex = 0; firstIndex + firstOffset + secondOffset + 2 < coordinates.size(); ++firstIndex) {
            int middleIndex = firstIndex + firstOffset + 1;
            int lastIndex = middleIndex + secondOffset + 1;

            double triangleArea = GeometryHelper.getTriangleArea(coordinates.get(firstIndex),
                                                                 coordinates.get(middleIndex),
                                                                 coordinates.get(lastIndex));

            // If triangle area is greater than the minimum allowed area (watch out for floating point precision!)
            if (DoubleMath.fuzzyCompare(triangleArea, inputParameters.getArea1(), Constants.FLOAT_TOLERANCE) > 0) {
                return true;
            }
        }
        return false;
    }

}
