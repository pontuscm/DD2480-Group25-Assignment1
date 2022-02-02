package se.kth.dd2480.group25.assignment1.lic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import se.kth.dd2480.group25.assignment1.Coordinate;
import se.kth.dd2480.group25.assignment1.InputParameters;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class LaunchInterceptorCondition2Test {

    private InputParameters parameters = new InputParameters();
    private LaunchInterceptorCondition2 condition = new LaunchInterceptorCondition2();


    @ParameterizedTest
    @MethodSource("validInputProvider")
    void shouldAcceptForValidInput(List<Coordinate> coordinates, double epsilon) {
        parameters.setEpsilon(epsilon);
        assertTrue(condition.evaluate(coordinates, parameters));
    }

    static Stream<Arguments> validInputProvider() {
        return Stream.of(
                arguments(List.of(Coordinate.of(55, 2), Coordinate.of(3, 1), Coordinate.of(55, 3)), 1.0),
                arguments(List.of(Coordinate.of(55, 2), Coordinate.of(40, 20), Coordinate.of(10, 10)), 0.1),
                arguments(List.of(Coordinate.of(55, 2), Coordinate.of(3, 1), Coordinate.of(32, 25)), 0.87)
        );
    }

    @Test
    void shouldRejectIfCloseToPi() {
        List<Coordinate> coordinates = List.of(Coordinate.of(55, 2), Coordinate.of(40, 10), Coordinate.of(10, 10));
        parameters.setEpsilon(2.0);
        assertFalse(condition.evaluate(coordinates, parameters));
    }

    @Test
    void shouldRejectIfCoincideWithVertex() {
        List<Coordinate> coordinates = List.of(Coordinate.of(55, 2), Coordinate.of(40, 10), Coordinate.of(40, 10));
        parameters.setEpsilon(2.0);
        assertFalse(condition.evaluate(coordinates, parameters));
    }

}