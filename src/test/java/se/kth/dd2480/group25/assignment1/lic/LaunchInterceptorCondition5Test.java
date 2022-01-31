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

public class LaunchInterceptorCondition5Test {

    private InputParameters parameters = new InputParameters();
    private LaunchInterceptorCondition5 condition = new LaunchInterceptorCondition5();

    @ParameterizedTest
    @MethodSource("validInputProvider")
    void shouldAcceptForValidInput(List<Coordinate> coordinates) {
        assertTrue(condition.evaluate(coordinates, parameters));
    }

    static Stream<Arguments> validInputProvider() {
        var list1 = List.of(point(14, 23), point(0, 0), point(7, 12), point(0, 0), point(0, 0), point(30, 6));
        var list2 = List.of(point(0, 0), point(1, 0), point(5, 5), point(2, 0),
                            point(3, 0), point(10, 5), point(4, 0), point(20, 15));
        var list3 = ListBuilder.create()
                               .add(point(20, 15))
                               .add(repeating(0, 0, 1, 10))
                               .add(point(10, 5))
                               .add(repeating(10, 0, 1, 13))
                               .add(point(5, 5)).get();

        return Stream.of(
            arguments(list1),
            arguments(list2),
            arguments(list3)
        );
    }

    @Test
    void shouldRejectIfTooFewCoordinates() {
        List<Coordinate> coordinates = List.of(Coordinate.of(10, 10));
        assertFalse(condition.evaluate(coordinates, parameters));
    }

    @Test
    void shouldRejectIfAllXAreEqual() {
        List<Coordinate> coordinates = List.of(Coordinate.of(10, 10),
                                               Coordinate.of(10, 11),
                                               Coordinate.of(10, 9));
        assertFalse(condition.evaluate(coordinates, parameters));
    }

    @Test
    void shouldRejectIfAllXAreIncreasing() {
        List<Coordinate> coordinates = List.of(Coordinate.of(10, 10),
                                               Coordinate.of(11, 11),
                                               Coordinate.of(12, 9),
                                               Coordinate.of(13, 1));
        assertFalse(condition.evaluate(coordinates, parameters));
    }

    @Test
    void shouldPassIfFirstXHasLargerValueThanSecond() {
        List<Coordinate> coordinates = List.of(Coordinate.of(12, 10),
                                               Coordinate.of(9, 11),
                                               Coordinate.of(11, 9),
                                               Coordinate.of(13, 1));
        assertTrue(condition.evaluate(coordinates, parameters));
    }

    @Test
    void shouldPassIfLastXHasSmallerValueThanSecondLast() {
        List<Coordinate> coordinates = List.of(Coordinate.of(8, 10),
                                               Coordinate.of(9, 11),
                                               Coordinate.of(13, 9),
                                               Coordinate.of(11, 1));
        assertTrue(condition.evaluate(coordinates, parameters));
    }
}
