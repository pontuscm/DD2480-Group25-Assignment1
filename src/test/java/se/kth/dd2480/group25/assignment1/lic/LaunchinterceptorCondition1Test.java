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

public class LaunchinterceptorCondition1Test {

    private InputParameters parameters = new InputParameters();
    private LaunchInterceptorCondition1 condition = new LaunchInterceptorCondition1();    

    @ParameterizedTest
    @MethodSource("validInputProvider")
    void shouldAcceptForValidInput(List<Coordinate> coordinates, double radius) {
        parameters.setRadius1(radius);
        assertTrue(condition.evaluate(coordinates, parameters));
    }

    static Stream<Arguments> validInputProvider() {
        return Stream.of(
            arguments(List.of(Coordinate.of(10, 10), Coordinate.of(15, 15), Coordinate.of(20, 20)), 7.0),
            arguments(List.of(Coordinate.of(0, 0),Coordinate.of(0, 1),Coordinate.of(1, 1)),0.7),
            arguments(List.of(Coordinate.of(0, 0),Coordinate.of(0, Math.sqrt(3.0)),Coordinate.of(1, 0),Coordinate.of(-1, 0)),1.1547) 
        );
    }

    @Test
    void collinearFalse() {
        List<Coordinate> coordinates = List.of(Coordinate.of(10, 10), Coordinate.of(15, 15), Coordinate.of(20, 20)); // half of the length of greatest line is 14.14/2 =7.07
        parameters.setRadius1(7.2);
        assertFalse(condition.evaluate(coordinates, parameters));
    }

    @Test
    void shouleRejectIfTooFewCoordinates() {
        List<Coordinate> coordinates = List.of(Coordinate.of(10, 10), Coordinate.of(15, 15)); 
        parameters.setRadius1(7.2);
        assertFalse(condition.evaluate(coordinates, parameters));
    }

    @Test
    void shouldRejectIfEqualRadius() {
        List<Coordinate> coordinates = List.of(Coordinate.of(0, 0),Coordinate.of(0, 1),Coordinate.of(1, 1)); 
        parameters.setRadius1(0.7071068);
        assertFalse(condition.evaluate(coordinates, parameters));
    }

    @Test
    void shouldRejectIfTooSmallRadius() {
        List<Coordinate> coordinates = List.of(Coordinate.of(0, 0),Coordinate.of(0, 1),Coordinate.of(1, 1)); 
        parameters.setRadius1(0.72);
        assertFalse(condition.evaluate(coordinates, parameters));
    }

    @Test
    void shouldRejectIfNotConsecutive() {
        List<Coordinate> coordinates = List.of(Coordinate.of(0, Math.sqrt(3.0)),Coordinate.of(0, 0),Coordinate.of(1, 0),Coordinate.of(-1, 0)); 
        parameters.setRadius1(1.1547);
        assertFalse(condition.evaluate(coordinates, parameters));
    }


}
