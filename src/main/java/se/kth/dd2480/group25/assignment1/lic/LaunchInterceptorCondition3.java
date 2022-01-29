package se.kth.dd2480.group25.assignment1.lic;

import com.google.common.math.DoubleMath;
import se.kth.dd2480.group25.assignment1.Coordinate;
import se.kth.dd2480.group25.assignment1.InputParameters;
import se.kth.dd2480.group25.assignment1.helpers.Constants;
import se.kth.dd2480.group25.assignment1.helpers.GeometryHelper;

import java.util.List;

/**
 * Launch Interceptor Condition #3
 * <p>
 * This condition is satisfied if there exist three consecutive points on the plane which together form a triangle
 * with area greater than the value of {@link InputParameters#getArea1()}
 */
public class LaunchInterceptorCondition3 implements LaunchInterceptorCondition {

	/**
	 * @param coordinates     List of x, y coordinates of the planar data points
	 * @param inputParameters The input parameters to the decision system, containing the minimum required triangle area
	 * @return {@code true} if there exist three consecutive points on the plane which together form a triangle with area
	 * greater than the value of {@link InputParameters#getArea1()}, otherwise {@code false}
	 */
	@Override
	public boolean evaluate(List<Coordinate> coordinates, InputParameters inputParameters) {
		// Reject any input that can't form a triangle
		if (coordinates.size() < 3) {
			return false;
		}

		for (int middleIndex = 1; middleIndex < coordinates.size() - 1; ++middleIndex) {
			double triangleArea = GeometryHelper.getTriangleArea(coordinates.get(middleIndex - 1),
			                                                     coordinates.get(middleIndex),
			                                                     coordinates.get(middleIndex + 1));

			// If triangle area is greater than the minimum allowed area (watch out for floating point precision!)
			if (DoubleMath.fuzzyCompare(triangleArea, inputParameters.getArea1(), Constants.FLOAT_TOLERANCE) > 0) {
				return true;
			}
		}
		return false;
	}

}
