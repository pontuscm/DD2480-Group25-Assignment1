package se.kth.dd2480.group25.assignment1.lic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import se.kth.dd2480.group25.assignment1.Coordinate;
import se.kth.dd2480.group25.assignment1.InputParameters;
import se.kth.dd2480.group25.assignment1.test_utils.CoordinateTestUtils;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static se.kth.dd2480.group25.assignment1.test_utils.CoordinateTestUtils.point;

public class LaunchInterceptorCondition7Test {

    private InputParameters parameters = new InputParameters();
    private LaunchInterceptorCondition7 condition = new LaunchInterceptorCondition7();

    @ParameterizedTest
    @MethodSource("validInputProvider")
    void shouldAcceptForValidInput(List<Coordinate> coordinates, int k_pts, double length) {
        parameters.setN_pts(coordinates.size());
        parameters.setK_pts(k_pts);
        parameters.setLength1(length);
        assertTrue(condition.evaluate(coordinates, parameters));
    }

    /**
     * @return a combination of valid inputs
     */
    static Stream<Arguments> validInputProvider() {
        return Stream.of(
                arguments(List.of(point(0, 0), point(200, 200), point(200, 200), point(20, 30)), 2, 10),
                arguments(List.of(point(1, 1), point(50, 100), point(40, 40)), 1, 20)
        );
    }

    /**
     * Should return false since NUMPOINTS is less than 3.
     */
    @Test
    void shouldRejectIfFewerThan3Points() {
        List<Coordinate> coordinates = CoordinateTestUtils.ListBuilder.create()
                .add(point(1, 1))
                .add(point(0, 0))
                .get();
        parameters.setN_pts(2);
        parameters.setK_pts(1);
        parameters.setLength1(20);
        assertFalse(condition.evaluate(coordinates, parameters));
    }

    /**
     * Should return false since there exists no set of two points
     * with K_pts intervening points that are more than length1
     * apart.
     */
    @Test
    void shouldRejectIfDistanceSmaller() {
        List<Coordinate> coordinates = CoordinateTestUtils.ListBuilder.create()
                .add(point(1, 5))
                .add(point(0, 0))
                .add(point(5, 2))
                .get();
        parameters.setN_pts(3);
        parameters.setK_pts(1);
        parameters.setLength1(20);
        assertFalse(condition.evaluate(coordinates, parameters));
    }

}
