package se.kth.dd2480.group25.assignment1.helpers;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import se.kth.dd2480.group25.assignment1.Coordinate;
import se.kth.dd2480.group25.assignment1.test_utils.CoordinateTestUtils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EvaluationHelperTest {

    @Nested
    class TriEvaluateWithOffsetsTests {
        @Test
        void mustReturnTrueIfPredicateIsTrue() {
            TriPredicate predicate = (x, y, z) -> true;
            boolean result = EvaluationHelper.triEvaluateWithOffsets(CoordinateTestUtils.repeating(0, 0, 0, 10),
                                                                     2, 3, predicate);
            assertTrue(result);
        }

        @Test
        void mustReturnFalseIfPredicateFalse() {
            TriPredicate predicate = (x, y, z) -> false;
            boolean result = EvaluationHelper.triEvaluateWithOffsets(CoordinateTestUtils.repeating(0, 0, 0, 10),
                                                                     2, 3, predicate);
            assertFalse(result);
        }

    }

    @Nested
    class TriangleAreaGreaterThanTests {

        @Test
        void mustReturnTrue() {
            TriPredicate predicate = EvaluationHelper.triangleAreaGreaterThanTest(140.0);
            assertTrue(predicate.test(Coordinate.of(14, 23), Coordinate.of(7, 12), Coordinate.of(30, 6)));
        }

        @Test
        void mustReturnFalse() {
            TriPredicate predicate = EvaluationHelper.triangleAreaGreaterThanTest(100.0);
            assertFalse(predicate.test(Coordinate.of(10, 15), Coordinate.of(25, 20), Coordinate.of(24, 9)));
        }

        @Test
        void shouldReturnFalseIfExactlyMinSize() {
            TriPredicate predicate = EvaluationHelper.triangleAreaGreaterThanTest(161.0);
            assertFalse(predicate.test(Coordinate.of(13, 17), Coordinate.of(26, 9), Coordinate.of(50, 19)));
        }

    }

    @Nested
    class TriangleAreaLesserThanTests {
        @Test
        void mustReturnTrue() {
            TriPredicate predicate = EvaluationHelper.triangleAreaLesserThanTest(100.0);
            assertTrue(predicate.test(Coordinate.of(30, 19), Coordinate.of(37, 27), Coordinate.of(54, 18)));
        }

        @Test
        void mustReturnFalse() {
            TriPredicate predicate = EvaluationHelper.triangleAreaLesserThanTest(90.0);
            assertFalse(predicate.test(Coordinate.of(30, 19), Coordinate.of(37, 27), Coordinate.of(54, 18)));
        }

        @Test
        void shouldReturnFalseIfExactlyMaxSize() {
            TriPredicate predicate = EvaluationHelper.triangleAreaLesserThanTest(99.5);
            assertFalse(predicate.test(Coordinate.of(30, 19), Coordinate.of(37, 27), Coordinate.of(54, 18)));
        }
    }

}
