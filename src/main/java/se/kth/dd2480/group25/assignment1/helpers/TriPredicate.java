package se.kth.dd2480.group25.assignment1.helpers;

import se.kth.dd2480.group25.assignment1.Coordinate;

import java.util.function.Predicate;

/**
 * A functional interface analogous to {@link java.util.function.Predicate} but for three input arguments and only of type {@link Coordinate}.
 *
 * @see Predicate
 */
@FunctionalInterface
public interface TriPredicate {
    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param first  the first input argument
     * @param middle the second input argument
     * @param last   the second input argument
     * @return {@code true} if the input arguments match the predicate,
     * otherwise {@code false}
     */
    boolean test(Coordinate first, Coordinate middle, Coordinate last);
}
