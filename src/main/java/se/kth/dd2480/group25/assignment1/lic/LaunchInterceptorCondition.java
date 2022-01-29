package se.kth.dd2480.group25.assignment1.lic;

import se.kth.dd2480.group25.assignment1.Coordinate;
import se.kth.dd2480.group25.assignment1.InputParameters;

import java.util.List;

public interface LaunchInterceptorCondition {
    boolean evaluate(List<Coordinate> coordinates, InputParameters inputParameters);
}
