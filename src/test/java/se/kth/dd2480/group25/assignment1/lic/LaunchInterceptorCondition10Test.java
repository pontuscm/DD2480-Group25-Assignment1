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

public class LaunchInterceptorCondition10Test {

    private InputParameters parameters = new InputParameters();
    private LaunchInterceptorCondition10 condition = new LaunchInterceptorCondition10();

    @ParameterizedTest
    @MethodSource("validInputProvider")
    void shouldAcceptForValidInput(List<Coordinate> coordinates, double minArea, int firstOffset, int secondOffset) {
        parameters.setArea1(minArea);
        parameters.setE_pts(firstOffset);
        parameters.setF_pts(secondOffset);
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
            arguments(list1, 140.0, 1, 2),
            arguments(list2, 15.0, 2, 1),
            arguments(list3, 15.0, 10, 13)
        );
    }

    @Test
    void shouldRejectIfSmaller() {
        List<Coordinate> coordinates = ListBuilder.create()
                                                  .add(point(10, 15))
                                                  .add(point(0, 0))
                                                  .add(point(25, 20))
                                                  .add(point(100, 100))
                                                  .add(point(200, 200))
                                                  .add(point(24, 9))
                                                  .get();
        parameters.setArea1(100.0);
        parameters.setE_pts(1);
        parameters.setF_pts(2);
        assertFalse(condition.evaluate(coordinates, parameters));
    }

    @Test
    void shouldRejectIfTooFewCoordinates() {
        List<Coordinate> coordinates = ListBuilder.create()
                                                  .add(point(10, 15))
                                                  .add(point(0, 0))
                                                  .add(point(25, 20))
                                                  .add(point(100, 100))
                                                  .get();
        parameters.setArea1(2);
        parameters.setE_pts(1);
        parameters.setF_pts(1);
        assertFalse(condition.evaluate(coordinates, parameters));
    }

    @Test
    void shouldRejectIfExactlyMinSize() {
        List<Coordinate> coordinates = ListBuilder.create()
                                                  .add(point(13, 17))
                                                  .add(point(0, 0))
                                                  .add(point(26, 9))
                                                  .add(point(100, 100))
                                                  .add(point(200, 200))
                                                  .add(point(50, 19))
                                                  .get();
        parameters.setArea1(161.0);
        parameters.setE_pts(1);
        parameters.setF_pts(2);
        assertFalse(condition.evaluate(coordinates, parameters));
    }

}
