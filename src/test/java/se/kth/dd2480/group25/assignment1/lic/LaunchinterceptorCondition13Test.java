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

public class LaunchinterceptorCondition13Test {

    private InputParameters parameters = new InputParameters();
    private LaunchInterceptorCondition13 condition = new LaunchInterceptorCondition13();    

    @ParameterizedTest
    @MethodSource("validInputProvider")
    void shouldAcceptForValidInput(List<Coordinate> coordinates, double radius1, double radius2, int apts, int bpts) {
        parameters.setRadius1(radius1);
        parameters.setRadius2(radius2);
        parameters.setA_pts(apts);
        parameters.setB_pts(bpts);
        assertTrue(condition.evaluate(coordinates, parameters));
    }

    /**
     * @return Combinations of valid inputs
     */
    static Stream<Arguments> validInputProvider() {
        return Stream.of(
            arguments(List.of(Coordinate.of(0, 0),
                              Coordinate.of(6, 5),
                              Coordinate.of(0, 1),
                              Coordinate.of(6, 5),
                              Coordinate.of(1, 1)),
                              0.7,0.6,1,1)  // circumcircle radius is around 0.707 > given radius
        );
    }

    /**
     * Should return false since the number of points 
     * should be greater than 4
     */
    @Test
    void shouleRejectIfTooFewCoordinates() {
        List<Coordinate> coordinates = List.of(Coordinate.of(0, 0),
                                               Coordinate.of(0, 1),
                                               Coordinate.of(1, 1),
                                               Coordinate.of(3, 5)); 
        parameters.setRadius1(0.7);
        parameters.setRadius2(0.7);

        parameters.setA_pts(1);                                 
        parameters.setB_pts(1);
        assertFalse(condition.evaluate(coordinates, parameters));
    }

    /**
     * Should return false since the number of intervining points 
     * should be greater than 0
     */
    @Test
    void shouleRejectIfWrongInterveneNumber() {
        List<Coordinate> coordinates = List.of(Coordinate.of(0, 0),
                                               Coordinate.of(4, 7),
                                               Coordinate.of(0, 1),
                                               Coordinate.of(3, 5),
                                               Coordinate.of(1, 1),
                                               Coordinate.of(0, 0)); 
        parameters.setRadius1(1);
        parameters.setRadius2(0.7);

        parameters.setA_pts(1);
        parameters.setB_pts(-2);       
        assertFalse(condition.evaluate(coordinates, parameters));
    }


    /**
     * Should return false since the circumcircle radius is 
     * lower than the given raidus
     */
    @Test
    void shouldRejectIfTooSmallRadius() {
        List<Coordinate> coordinates = List.of(Coordinate.of(0, 0),
                                               Coordinate.of(1, 0),
                                               Coordinate.of(0, 3),
                                               Coordinate.of(1, 2),
                                               Coordinate.of(4, 0)); // circumcircle radius is 2.5 which is lower than the given radius
        parameters.setRadius1(3);
        parameters.setRadius2(3);

        parameters.setA_pts(1);
        parameters.setB_pts(1);
        assertFalse(condition.evaluate(coordinates, parameters));
    }


}
