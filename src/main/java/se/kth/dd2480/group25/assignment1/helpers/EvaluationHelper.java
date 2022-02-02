package se.kth.dd2480.group25.assignment1.helpers;

import com.google.common.math.DoubleMath;
import se.kth.dd2480.group25.assignment1.Coordinate;

import java.util.List;

/**
 * A helper class for recurring evaluation checks used in multiple Launch Interceptor Conditions.
 */
public class EvaluationHelper {

    // Don't allow instantiation of fully static class
    private EvaluationHelper() {
    }

    /**
     * Performs checks on a series of coordinates which are separated by offsets.
     * Corresponds to LIC requirements of type:
     * <p>
     * <strong>"There exists at least one set of three data points separated by exactly x and y consecutive intervening points, respectively, that [...]"</strong>
     *
     * @param coordinates  List of x, y coordinates of the planar data points
     * @param firstOffset  The number of consecutive points between the first and middle coordinate points.
     * @param secondOffset The number of consecutive points between the middle and last coordinate points.
     * @param predicate    The evaluation check to perform on the three coordinate points, for example ({@link EvaluationHelper#triangleAreaGreaterThanTest(double)})
     * @return {@code true} if there exist three points in the coordinate array separated by the offsets that
     * satisfy the given predicate, otherwise {@code false}
     */
    public static boolean triEvaluateWithOffsets(List<Coordinate> coordinates, int firstOffset,
                                                 int secondOffset, TriPredicate predicate) {

        for (int firstIndex = 0; firstIndex + firstOffset + secondOffset + 2 < coordinates.size(); ++firstIndex) {
            int middleIndex = firstIndex + firstOffset + 1;
            int lastIndex = middleIndex + secondOffset + 1;

            if (predicate.test(coordinates.get(firstIndex), coordinates.get(middleIndex), coordinates.get(lastIndex))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Provides a {@link TriPredicate} that calculates the area of the triangle formed by the three given
     * coordinates and checks whether this area is greater than a provided minimum
     *
     * @param minArea The minimum area that the triangle must be greater than
     * @return {@link TriPredicate}
     */
    public static TriPredicate triangleAreaGreaterThanTest(double minArea) {
        return (first, middle, last) -> {
            double triangleArea = GeometryHelper.getTriangleArea(first, middle, last);

            // Check if triangle area is greater than the minimum allowed area (watch out for floating point precision!)
            return DoubleMath.fuzzyCompare(triangleArea, minArea, Constants.FLOAT_TOLERANCE) > 0;
        };
    }

    /**
     * Provides a {@link TriPredicate} that calculates the area of the triangle formed by the three given
     * coordinates and checks whether this area is lesser than a provided maximum
     *
     * @param maxArea The maximum area that the triangle must be lesser than
     * @return {@link TriPredicate}
     */
    public static TriPredicate triangleAreaLesserThanTest(double maxArea) {
        return (first, middle, last) -> {
            double triangleArea = GeometryHelper.getTriangleArea(first, middle, last);

            // Check if triangle area is lesser than the maximum allowed area (watch out for floating point precision!)
            return triangleArea > 0 && DoubleMath.fuzzyCompare(triangleArea, maxArea, Constants.FLOAT_TOLERANCE) < 0;
        };
    }

}
