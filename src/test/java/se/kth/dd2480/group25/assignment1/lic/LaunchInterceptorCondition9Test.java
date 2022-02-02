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

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static se.kth.dd2480.group25.assignment1.test_utils.CoordinateTestUtils.point;
import static se.kth.dd2480.group25.assignment1.test_utils.CoordinateTestUtils.repeating;

class LaunchInterceptorCondition9Test {

    private InputParameters parameters = new InputParameters();
    private LaunchInterceptorCondition9 condition = new LaunchInterceptorCondition9();


    @ParameterizedTest
    @MethodSource("validInputProvider")
    void shouldAcceptForValidInput(List<Coordinate> coordinates, double epsilon, int firstOffset, int secondOffset) {
        parameters.setEpsilon(epsilon);
        parameters.setC_pts(firstOffset);
        parameters.setD_pts(secondOffset);
        assertTrue(condition.evaluate(coordinates, parameters));
    }

    static Stream<Arguments> validInputProvider() {
        var list1 = List.of(point(55, 2), point(0, 0), point(3, 1), point(0, 0), point(55, 3));
        var list2 = List.of(point(0, 0), point(55, 2), point(5, 5), point(40, 20),
                point(3, 0), point(10, 5), point(4, 0), point(10, 10));
        var list3 = CoordinateTestUtils.ListBuilder.create()
                .add(point(55, 2))
                .add(repeating(0, 0, 1, 10))
                .add(point(3, 1))
                .add(repeating(10, 0, 1, 15))
                .add(point(32, 25))
                .add(repeating(8, 0, 1, 15)).get();

        return Stream.of(
                arguments(list1, 1.0, 1, 1),
                arguments(list2, 0.58, 2, 1),
                arguments(list3, 0.87, 10, 15)
        );
    }

    @Test
    void shouldRejectIfCloseToPi() {
        List<Coordinate> coordinates = CoordinateTestUtils.ListBuilder.create()
                .add(point(55, 2))
                .add(point(0, 0))
                .add(point(40, 10))
                .add(point(0, 100))
                .add(point(200, 200))
                .add(point(10, 10))
                .get();
        parameters.setEpsilon(2.0);
        parameters.setC_pts(1);
        parameters.setD_pts(2);
        assertFalse(condition.evaluate(coordinates, parameters));
    }

    @Test
    void shouldRejectIfCoincideWithVertex() {
        List<Coordinate> coordinates = CoordinateTestUtils.ListBuilder.create()
                .add(point(55, 2))
                .add(point(0, 0))
                .add(point(40, 10))
                .add(point(0, 100))
                .add(point(10, 200))
                .add(point(40, 10))
                .get();
        parameters.setEpsilon(2.0);
        parameters.setC_pts(1);
        parameters.setD_pts(2);
        assertFalse(condition.evaluate(coordinates, parameters));
    }

}