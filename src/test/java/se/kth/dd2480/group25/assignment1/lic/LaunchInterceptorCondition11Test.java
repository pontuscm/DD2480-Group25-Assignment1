package se.kth.dd2480.group25.assignment1.lic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import se.kth.dd2480.group25.assignment1.Coordinate;
import se.kth.dd2480.group25.assignment1.InputParameters;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static se.kth.dd2480.group25.assignment1.test_utils.CoordinateTestUtils.*;

public class LaunchInterceptorCondition11Test {

    private InputParameters parameters = new InputParameters();
    private LaunchInterceptorCondition11 condition = new LaunchInterceptorCondition11();

    @ParameterizedTest
    @MethodSource("validInputProvider")
    void shouldAcceptForValidInput(List<Coordinate> coordinates, int g_pts) {
        parameters.setG_pts(g_pts);
        assertTrue(condition.evaluate(coordinates, parameters));
    }

    static Stream<Arguments> validInputProvider() {
        var list1 = List.of(point(14, 23), point(0, 0), point(17, 12), point(0, 0), point(0, 0), point(10, 6));
        var list2 = List.of(point(0, 0), point(1, 0), point(5, 5), point(2, 0),
                            point(3, 0), point(10, 5), point(4, 0), point(20, 15));
        var list3 = ListBuilder.create()
                               .add(point(20, 15))
                               .add(repeating(0, 0, 1, 10))
                               .add(point(10, 5))
                               .add(repeating(10, 0, 1, 13))
                               .add(point(5, 5)).get();

        return Stream.of(
            arguments(list1, 2),
            arguments(list2, 3),
            arguments(list3, 5)
        );
    }

    @Test
    void shouldRejectIfTooFewCoordinates() {
        List<Coordinate> coordinates = List.of(Coordinate.of(10, 10), 
                                               Coordinate.of(18, 11));
        assertFalse(condition.evaluate(coordinates, parameters));
    }

    @Test
    void shouldRejectIfLessThanOneGpts() {
        List<Coordinate> coordinates = List.of(Coordinate.of(10, 10),
                                               Coordinate.of(10, 11),
                                               Coordinate.of(10, 9));
        parameters.setG_pts(0);
        assertFalse(condition.evaluate(coordinates, parameters));
    }

    @Test
    void shouldRejectIfLessThanGptsPlusTwoCoordinates() {
        List<Coordinate> coordinates = List.of(Coordinate.of(10, 10),
                                               Coordinate.of(18, 11),
                                               Coordinate.of(12, 9),
                                               Coordinate.of(19, 1));
        parameters.setG_pts(3);
        assertFalse(condition.evaluate(coordinates, parameters));
    }

    @Test
    void shouldPassIfConditionsMetOnLastCoordinates() {
        List<Coordinate> coordinates = List.of(Coordinate.of(2, 10),
                                               Coordinate.of(9, 11),
                                               Coordinate.of(11, 9),
                                               Coordinate.of(13, 1),
                                               Coordinate.of(23, 13),
                                               Coordinate.of(26, 19),
                                               Coordinate.of(13, 1));
        parameters.setG_pts(1);
        assertTrue(condition.evaluate(coordinates, parameters));
    }
}
