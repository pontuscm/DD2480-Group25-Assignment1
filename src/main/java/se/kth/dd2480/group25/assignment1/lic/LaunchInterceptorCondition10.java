package se.kth.dd2480.group25.assignment1.lic;

import se.kth.dd2480.group25.assignment1.Coordinate;
import se.kth.dd2480.group25.assignment1.InputParameters;
import se.kth.dd2480.group25.assignment1.helpers.EvaluationHelper;
import se.kth.dd2480.group25.assignment1.helpers.TriPredicate;

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

    /**
     * @param coordinates     List of x, y coordinates of the planar data points
     * @param inputParameters The input parameters to the decision system, containing the minimum required triangle
     *                        area and the counts of intermediary points
     * @return {@code true} if there exist points on the plane separated by the offsets {@link InputParameters#getE_pts()}
     * and {@link InputParameters#getF_pts()} which together form a triangle with area greater than the
     * value of {@link InputParameters#getArea1()}, otherwise {@code false}
     */
    @Override
    public boolean evaluate(List<Coordinate> coordinates, InputParameters inputParameters) {

        TriPredicate triangleGreaterThanArea1Test = EvaluationHelper.triangleAreaGreaterThanTest(
            inputParameters.getArea1());

        return EvaluationHelper.triEvaluateWithOffsets(coordinates,
                                                       inputParameters.getE_pts(),
                                                       inputParameters.getF_pts(),
                                                       triangleGreaterThanArea1Test);
    }

}
