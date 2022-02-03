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
import static se.kth.dd2480.group25.assignment1.test_utils.CoordinateTestUtils.repeating;

class LaunchInterceptorCondition4Test {

    private InputParameters parameters = new InputParameters();
    private LaunchInterceptorCondition4 condition = new LaunchInterceptorCondition4();

    @ParameterizedTest
    @MethodSource("validInputProvider")
    void shouldAcceptForValidInput(List<Coordinate> coordinates, int quads, int q) {
        parameters.setQuads(quads);
        parameters.setQ_pts(q);

        assertTrue(condition.evaluate(coordinates, parameters));
    }

    /**
     * @return a combination of valid inputs
     */
    static Stream<Arguments> validInputProvider() {
        var list1 = List.of(point(14, 23), point(-5, -56), point(-7, 12), point(0, 0), point(0, 0), point(30, 6));
        var list2 = List.of(point(0, 0), point(1, 0), point(-5, 5), point(2, 0),
                point(3, 0), point(10, 5), point(4, 0), point(20, 15));
        var list3 = CoordinateTestUtils.ListBuilder.create()
                .add(point(-20, 15))
                .add(repeating(0, 0, 1, 10))
                .add(point(10, -5))
                .add(repeating(10, 0, 1, 13))
                .add(point(-6, -7))
                .add(point(5, -5)).get();

        return Stream.of(
                arguments(list1, 1, 2),
                arguments(list2, 2, 5),
                arguments(list3, 3, 3)
        );
    }

    /**
     * Should return false since two points cannot exist in three different quadrants
     */
    @Test
    void shouldRejectIfTooFewQuads(){
        List<Coordinate> coordinates = List.of(Coordinate.of(-10, 15), Coordinate.of(25, -20), Coordinate.of(24, 9));
        parameters.setQuads(3);
        parameters.setQ_pts(2);
        assertFalse(condition.evaluate(coordinates, parameters));
    }

    /**
     * should return false since there are no three consecutive points that exist in three different quadrants
     */
    @Test
    void shouldRejectIfNotConsequtive(){
        List<Coordinate> coordinates = List.of(Coordinate.of(-10, 15), Coordinate.of(25, -20), Coordinate.of(25, -20), Coordinate.of(24, 9));
        parameters.setQuads(3);
        parameters.setQ_pts(3);
        assertFalse(condition.evaluate(coordinates, parameters));
    }

}