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

public class LaunchInterceptorCondition3Test {

	private InputParameters parameters = new InputParameters();
	private LaunchInterceptorCondition3 condition = new LaunchInterceptorCondition3();

	static Stream<Arguments> validInputProvider() {
		return Stream.of(
			arguments(List.of(Coordinate.of(14, 23), Coordinate.of(7, 12), Coordinate.of(30, 6)), 140.0),
			arguments(List.of(Coordinate.of(5, 5), Coordinate.of(10, 5), Coordinate.of(15, 6), Coordinate.of(20, 15)),
			          15.0),
			arguments(List.of(Coordinate.of(20, 15), Coordinate.of(15, 6), Coordinate.of(10, 5), Coordinate.of(5, 5)),
			          15.0)
		);
	}

	@ParameterizedTest
	@MethodSource("validInputProvider")
	void shouldAcceptForValidInput(List<Coordinate> coordinates, double minArea) {
		parameters.setArea1(minArea);
		assertTrue(condition.evaluate(coordinates, parameters));
	}

	@Test
	void shouldRejectIfSmaller() {
		List<Coordinate> coordinates = List.of(Coordinate.of(10, 15), Coordinate.of(25, 20), Coordinate.of(24, 9));
		parameters.setArea1(100.0);
		assertFalse(condition.evaluate(coordinates, parameters));
	}

	@Test
	void shouldRejectIfTooFewCoordinates() {
		List<Coordinate> coordinates = List.of(Coordinate.of(10, 10), Coordinate.of(20, 20));
		parameters.setArea1(5.0);
		assertFalse(condition.evaluate(coordinates, parameters));
	}

	@Test
	void shouldRejectIfNotConsecutive() {
		List<Coordinate> coordinates = List.of(Coordinate.of(10, 10),
		                                       Coordinate.of(23, 11),
		                                       Coordinate.of(34, 9),
		                                       Coordinate.of(53, 1));
		parameters.setArea1(50.0);
		assertFalse(condition.evaluate(coordinates, parameters));
	}

	@Test
	void shouldRejectIfExactlyMinSize() {
		List<Coordinate> coordinates = List.of(Coordinate.of(13, 17), Coordinate.of(26, 9), Coordinate.of(50, 19));
		parameters.setArea1(161.0);
		assertFalse(condition.evaluate(coordinates, parameters));
	}
}
