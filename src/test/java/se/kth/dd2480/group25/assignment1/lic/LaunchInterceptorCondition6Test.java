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

/*
 * Unit tests for the launch interceptor condition 5 class
 */
public class LaunchInterceptorCondition6Test {

    private InputParameters parameters = new InputParameters();
    private LaunchInterceptorCondition6 condition = new LaunchInterceptorCondition6();

    /* 
     * Test that checks three sets of valid input parameters and is expected evaluate 
     * to true. 
     */
    @ParameterizedTest
    @MethodSource("validInputProvider")
    void shouldAcceptForValidInput(List<Coordinate> coordinates, double dist, int n_pts) {
        parameters.setDist(dist);
        parameters.setN_pts(n_pts);
        assertTrue(condition.evaluate(coordinates, parameters));
    }

    static Stream<Arguments> validInputProvider() {
        var list1 = List.of(point(14, 23), point(0, 0), point(7, 12), point(0, 0), point(0, 0), point(30, 6));
        var list2 = List.of(point(0, 0), point(1, 0), point(5, 5), point(2, 0),
                            point(3, 0), point(10, 5), point(4, 0), point(20, 15));
        var list3 = ListBuilder.create()
                               .add(point(1, 1))
                               .add(repeating(0, 0, 1, 10))
                               .add(point(10, 10))
                               .add(repeating(10, 0, 1, 13))
                               .add(point(5, 5)).get();

        return Stream.of(
            arguments(list1, 0, 5),
            arguments(list2, 1, 3),
            arguments(list3, 2, 12)
        );
    }

    /* 
     * Test that checks with fewer coordinates than allowed. Input is two 
     * coordinates so it is expected to evaluate to false. 
     */
    @Test
    void shouldRejectIfTooFewCoordinates() {
        List<Coordinate> coordinates = List.of(Coordinate.of(10, 10), 
                                               Coordinate.of(8, 8));
        parameters.setDist(0);
        parameters.setN_pts(3);
        assertFalse(condition.evaluate(coordinates, parameters));
    }

    /* 
     * Test that checks with fewer N_PTS than allowed. Input is two 
     * N_PTS so it is expected to evaluate to false. 
     */
    @Test
    void shouldRejectIfFewerThanThreeNpts() {
        List<Coordinate> coordinates = List.of(Coordinate.of(8, 10),
                                               Coordinate.of(10, 11),
                                               Coordinate.of(7, 9));
        parameters.setDist(0);
        parameters.setN_pts(2);
        assertFalse(condition.evaluate(coordinates, parameters));
    }

    /* 
     * Test that checks with more N_PTS than coordinates which is not allowed. 
     * Input is five N_PTS and four coordinates so it is expected to evaluate 
     * to false. 
     */
    @Test
    void shouldRejectIfMoreNptsThanCoordinates() {
        List<Coordinate> coordinates = List.of(Coordinate.of(10, 10),
                                               Coordinate.of(11, 11),
                                               Coordinate.of(12, 9),
                                               Coordinate.of(13, 1));
        parameters.setDist(0);
        parameters.setN_pts(5);
        assertFalse(condition.evaluate(coordinates, parameters));
    }

    /* 
     * Test that checks with DIST set lower than zero which is not allowed. 
     * Input is DIST set at minus one so it is expected to evaluate to false. 
     */
    @Test
    void shouldRejectIfDistLowerThanZero() {
        List<Coordinate> coordinates = List.of(Coordinate.of(10, 10),
                                               Coordinate.of(11, 11),
                                               Coordinate.of(12, 9),
                                               Coordinate.of(13, 1));
        parameters.setDist(-1);
        parameters.setN_pts(3);
        assertFalse(condition.evaluate(coordinates, parameters));
    }

    /* 
     * Test that checks if there is no coordinate further away than DIST which
     * should not meet the condition. Input is four coordinates with x/y distances
     * of maximum four from each other and DIST set at ten so it is expected to 
     * evaluate to false. 
     */
    @Test
    void shouldRejectIfNoPointLongerAwayThanDist() {
        List<Coordinate> coordinates = List.of(Coordinate.of(0, 0),
                                               Coordinate.of(2, 4),
                                               Coordinate.of(3, 1),
                                               Coordinate.of(1, 2));
        parameters.setDist(10);
        parameters.setN_pts(3);
        assertFalse(condition.evaluate(coordinates, parameters));
    }
}
