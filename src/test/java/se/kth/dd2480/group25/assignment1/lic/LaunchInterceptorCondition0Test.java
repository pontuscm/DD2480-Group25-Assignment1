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

public class LaunchInterceptorCondition0Test {

    private InputParameters parameters = new InputParameters();
    private LaunchInterceptorCondition0 condition = new LaunchInterceptorCondition0();

    @ParameterizedTest
    @MethodSource("validInputProvider")
    void shouldAcceptForValidInput(List<Coordinate> coordinates, double length) {
        parameters.setLength1(length);
        assertTrue(condition.evaluate(coordinates, parameters));
    }

    /**
     * @return a combination of valid inputs
     */
    static Stream<Arguments> validInputProvider() {
        return Stream.of(
                arguments(List.of(point(0, 0), point(0, 15)), 10),
                arguments(List.of(point(30, 15), point(50, 100)), 50)
        );
    }

    /**
     * Should return false since there exists no set of two consecutive points
     * in the list that have a distance greater than length1.
     */
    @Test
    void shouldRejectIfSmaller() {
        List<Coordinate> coordinates = CoordinateTestUtils.ListBuilder.create()
                .add(point(1, 5))
                .add(point(0, 0))
                .get();
        parameters.setLength1(15);
        assertFalse(condition.evaluate(coordinates, parameters));
    }

    /**
     * Should return false since there exists no set of two consecutive points
     * in the list that have a distance greater than length1, only a set of
     * two consecutive points that are exactly length1 apart.
     */
    @Test
    void shouldRejectIfExactLength() {
        List<Coordinate> coordinates = CoordinateTestUtils.ListBuilder.create()
                .add(point(0, 5))
                .add(point(0, 0))
                .get();
        parameters.setLength1(5);
        assertFalse(condition.evaluate(coordinates, parameters));
    }
}
