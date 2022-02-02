package se.kth.dd2480.group25.assignment1.lic;

import se.kth.dd2480.group25.assignment1.Coordinate;
import se.kth.dd2480.group25.assignment1.InputParameters;
import se.kth.dd2480.group25.assignment1.helpers.Constants;
import se.kth.dd2480.group25.assignment1.helpers.GeometryHelper;

import java.util.List;

public class LaunchInterceptorCondition2 implements LaunchInterceptorCondition {
    @Override
    public boolean evaluate(List<Coordinate> coordinates, InputParameters inputParameters) {
        if (coordinates.size() <=2) {
            return false;
        }

        for (int i = 1; i < coordinates.size()-1; i++) {
            Coordinate first = coordinates.get(i-1);
            Coordinate vertex = coordinates.get(i);
            Coordinate last = coordinates.get(i+1);

            if (!first.equals(vertex) && !last.equals(vertex)) {
                if (GeometryHelper.getAngle(first, vertex, last) < (Constants.PI - inputParameters.getEpsilon())) {
                    return true;
                }
                if (GeometryHelper.getAngle(first, vertex, last) > (Constants.PI + inputParameters.getEpsilon())) {
                    return true;
                }
            }
        }

        return false;
    }
}
