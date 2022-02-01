package se.kth.dd2480.group25.assignment1.lic;

import se.kth.dd2480.group25.assignment1.Coordinate;
import se.kth.dd2480.group25.assignment1.InputParameters;
import se.kth.dd2480.group25.assignment1.helpers.EvaluationHelper;
import se.kth.dd2480.group25.assignment1.helpers.TriPredicate;

import java.util.List;

/**
 * Launch Interceptor Condition #14
 * <p>
 * This condition is satisfied if there exist three points x, y, z on the plane, which together form a triangle with
 * area greater than the value of {@link InputParameters#getArea1()} and if there also exist three points on
 * the plane which together form a triangle with area lesser than the value of {@link InputParameters#getArea2()}.
 * The two triples can be the same points but do not have to be.
 * <p>
 * Additionally for both triples, the first point and the second point must be separated by exactly {@link InputParameters#getE_pts()} intermediary
 * points whereas the second point and the third point must be separated by exactly {@link InputParameters#getF_pts()} intermediary points.
 */
public class LaunchInterceptorCondition14 implements LaunchInterceptorCondition {

    /**
     * @param coordinates     List of x, y coordinates of the planar data points
     * @param inputParameters The input parameters to the decision system, containing the minimum and maximum required
     *                        triangle areas as well as the counts of intermediary points offsetting the triangle vertices
     * @return {@code true} if there exist points on the plane separated by the offsets {@link InputParameters#getE_pts()}
     * and {@link InputParameters#getF_pts()} which together form a triangle with area greater than the
     * value of {@link InputParameters#getArea1()} and if there exists three points similarly separated by the offsets
     * which together form a triangle with area lesser than the value of  {@link InputParameters#getArea2()}, otherwise {@code false}
     */
    @Override
    public boolean evaluate(List<Coordinate> coordinates, InputParameters inputParameters) {

        TriPredicate triangleGreaterThanTest = EvaluationHelper.triangleAreaGreaterThanTest(inputParameters.getArea1());
        TriPredicate triangleLesserThanTest = EvaluationHelper.triangleAreaLesserThanTest(inputParameters.getArea2());

        int firstOffset = inputParameters.getE_pts();
        int secondOffset = inputParameters.getF_pts();

        boolean hasTriangleGreaterThanArea1 =
            EvaluationHelper.triEvaluateWithOffsets(coordinates, firstOffset, secondOffset, triangleGreaterThanTest);

        boolean hasTriangleLesserThanArea2 =
            EvaluationHelper.triEvaluateWithOffsets(coordinates, firstOffset, secondOffset, triangleLesserThanTest);

        return hasTriangleGreaterThanArea1 && hasTriangleLesserThanArea2;
    }

}
