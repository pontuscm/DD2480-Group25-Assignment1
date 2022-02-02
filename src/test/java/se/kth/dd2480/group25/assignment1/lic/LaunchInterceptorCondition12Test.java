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

public class LaunchInterceptorCondition12Test {

    private InputParameters parameters = new InputParameters();
    private LaunchInterceptorCondition12 condition = new LaunchInterceptorCondition12();

    @ParameterizedTest
    @MethodSource("validInputProvider")
    void shouldAcceptForValidInput(List<Coordinate> coordinates, int k_pts, double length1, double length2) {
        parameters.setN_pts(coordinates.size());
        parameters.setK_pts(k_pts);
        parameters.setLength1(length1);
        parameters.setLength2(length2);
        assertTrue(condition.evaluate(coordinates, parameters));
    }

    static Stream<Arguments> validInputProvider() {
        return Stream.of(
                arguments(List.of(point(0, 0), point(200, 200), point(200, 200), point(20, 30)), 2, 10, 40),
                arguments(List.of(point(1, 1), point(50, 100), point(40, 40), point(50, 101)), 1, 20, 5)
        );
    }

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

    @Test
    void shouldRejectIfDistanceSmallerThanLength1() {
        List<Coordinate> coordinates = CoordinateTestUtils.ListBuilder.create()
                .add(point(1, 5))
                .add(point(0, 0))
                .add(point(5, 2))
                .get();
        parameters.setN_pts(3);
        parameters.setK_pts(1);
        parameters.setLength1(20);
        parameters.setLength2(100);
        assertFalse(condition.evaluate(coordinates, parameters));
    }

    @Test
    void shouldRejectIfDistanceGreaterThanLength2() {
        List<Coordinate> coordinates = CoordinateTestUtils.ListBuilder.create()
                .add(point(1, 5))
                .add(point(0, 0))
                .add(point(5, 2))
                .get();
        parameters.setN_pts(3);
        parameters.setK_pts(1);
        parameters.setLength1(1);
        parameters.setLength2(1);
        assertFalse(condition.evaluate(coordinates, parameters));
    }

}