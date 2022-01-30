package se.kth.dd2480.group25.assignment1.helpers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import se.kth.dd2480.group25.assignment1.Coordinate;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class GeometryHelperTest {

    static Stream<Arguments> triangleAreaTestProvider() {
        return Stream.of(
            arguments(Coordinate.of(15, 15), Coordinate.of(23, 30), Coordinate.of(50, 25), 222.5d),
            arguments(Coordinate.of(41, 40), Coordinate.of(37, 35), Coordinate.of(70, 10), 132.5d),
            arguments(Coordinate.of(0.75, 3.1), Coordinate.of(5.98, 9.21), Coordinate.of(10.11, 9.99), 10.57745d),
            arguments(Coordinate.of(46, 25), Coordinate.of(20, 25), Coordinate.of(75, 25), 0.0d)
        );
    }

    @ParameterizedTest
    @MethodSource("triangleAreaTestProvider")
    void testGetTriangleArea(Coordinate first, Coordinate second, Coordinate third, double expectedArea) {
        double calculatedArea = GeometryHelper.getTriangleArea(first, second, third);
        double epsilon = 0.000001d; // needed for comparing floating point numbers
        assertEquals(expectedArea, calculatedArea, epsilon);
    }
}
