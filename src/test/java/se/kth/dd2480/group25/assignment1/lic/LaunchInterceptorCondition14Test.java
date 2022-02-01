package se.kth.dd2480.group25.assignment1.lic;

import org.junit.jupiter.api.Test;
import se.kth.dd2480.group25.assignment1.InputParameters;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static se.kth.dd2480.group25.assignment1.test_utils.CoordinateTestUtils.*;

public class LaunchInterceptorCondition14Test {

    private final InputParameters parameters = new InputParameters();
    private final LaunchInterceptorCondition14 condition = new LaunchInterceptorCondition14();

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
