package se.kth.dd2480.group25.assignment1.lic;

import se.kth.dd2480.group25.assignment1.Coordinate;
import se.kth.dd2480.group25.assignment1.InputParameters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LaunchInterceptorCondition4 implements LaunchInterceptorCondition{

    @Override
    public boolean evaluate(List<Coordinate> coordinates, InputParameters inputParameters) {
        int q = inputParameters.getQ_pts();

        for (int i = 0; i <= coordinates.size()-q; i++) {
            List<Boolean> quads = new ArrayList(List.of(false, false, false, false));

            for (int j = 0; j < q; j++) {
                double x = coordinates.get(i+j).getX();
                double y = coordinates.get(i+j).getY();
                if (x >= 0) {
                    if (y >= 0) { quads.set(0, true); }
                    else { quads.set(1, true); }
                } else {
                    if (y >= 0) { quads.set(3, true); }
                    else { quads.set(2, true); }
                }
            }
            if (Collections.frequency(quads, true) >= inputParameters.getQuads()) {
                return true;
            }

        }

        return false;
    }
}
