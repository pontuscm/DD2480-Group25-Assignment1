package se.kth.dd2480.group25.assignment1.lic;

import org.junit.jupiter.api.Test;
import se.kth.dd2480.group25.assignment1.InputParameters;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static se.kth.dd2480.group25.assignment1.test_utils.CoordinateTestUtils.*;

public class LaunchInterceptorCondition14Test {

    private final InputParameters parameters = new InputParameters();
    private final LaunchInterceptorCondition14 condition = new LaunchInterceptorCondition14();

    /**
     * Should return true since it has both a triangle greater than AREA1 and one that is lesser than AREA2.
     */
    @Test
    void shouldAcceptValidInput() {
        parameters.setE_pts(3);
        parameters.setF_pts(2);
        parameters.setArea1(250.0);
        parameters.setArea2(75.0);

        var points = ListBuilder.create()
                                .add(repeating(10, 20, 5, 4))
                                .add(point(30, 30))
                                .add(repeating(35, 20, 5, 2))
                                .add(point(50, 10))
                                .add(point(55, 25))
                                .get();

        assertTrue(condition.evaluate(points, parameters));
    }

    /**
     * Should return false since the area of the largest triangle is lesser than AREA2.
     */
    @Test
    void shouldRejectIfNoLargerTriangle() {
        parameters.setE_pts(3);
        parameters.setF_pts(2);
        parameters.setArea1(350.0);
        parameters.setArea2(75.0);

        var points = ListBuilder.create()
                                .add(repeating(10, 20, 5, 4))
                                .add(point(30, 30))
                                .add(repeating(35, 20, 5, 2))
                                .add(point(50, 10))
                                .add(point(55, 25))
                                .get();

        assertFalse(condition.evaluate(points, parameters));
    }

    /**
     * Should return false since the area of the smallest triangle is greater than AREA2.
     */
    @Test
    void shouldRejectIfNoSmallerTriangle() {
        parameters.setE_pts(3);
        parameters.setF_pts(2);
        parameters.setArea1(250.0);
        parameters.setArea2(30.0);

        var points = ListBuilder.create()
                                .add(repeating(10, 20, 5, 4))
                                .add(point(30, 30))
                                .add(repeating(35, 20, 5, 2))
                                .add(point(50, 10))
                                .add(point(55, 25))
                                .get();

        assertFalse(condition.evaluate(points, parameters));
    }

    /**
     * Should return false since the triangle area is exactly the minimum size (we require that triangle must be greater than AREA1).
     */
    @Test
    void shouldRejectIfExactlyMinimumSize() {
        parameters.setE_pts(3);
        parameters.setF_pts(2);
        parameters.setArea1(300.0);
        parameters.setArea2(75.0);

        var points = ListBuilder.create()
                                .add(repeating(10, 20, 5, 4))
                                .add(point(30, 30))
                                .add(repeating(35, 20, 5, 2))
                                .add(point(50, 10))
                                .add(point(55, 25))
                                .get();

        assertFalse(condition.evaluate(points, parameters));
    }

    /**
     * Should return false since the triangle area is exactly the maximum size (we require that triangle must be lesser than AREA2).
     */
    @Test
    void shouldRejectIfExactlyMaximumSize() {
        parameters.setE_pts(3);
        parameters.setF_pts(2);
        parameters.setArea1(250.0);
        parameters.setArea2(50.0);

        var points = ListBuilder.create()
                                .add(repeating(10, 20, 5, 4))
                                .add(point(30, 30))
                                .add(repeating(35, 20, 5, 2))
                                .add(point(50, 10))
                                .add(point(55, 25))
                                .get();

        assertFalse(condition.evaluate(points, parameters));
    }
}
