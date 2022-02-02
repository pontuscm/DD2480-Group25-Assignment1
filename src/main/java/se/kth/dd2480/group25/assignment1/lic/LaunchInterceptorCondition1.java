package se.kth.dd2480.group25.assignment1.lic;

import se.kth.dd2480.group25.assignment1.Coordinate;
import se.kth.dd2480.group25.assignment1.InputParameters;
import se.kth.dd2480.group25.assignment1.helpers.GeometryHelper;

import java.util.List;

/**
 * Launch Interceptor Condition #3
 * <p>
 * This condition is satisfied if there exist three consecutive points on the plane that cannot all be contained
 * Within or on a circle of {@link InputParameters#getRadius1()}
 */
public class LaunchInterceptorCondition1 implements LaunchInterceptorCondition{

    /**
     * @param coordinates     List of x, y coordinates of the planar data points 
     * @param inputParameters The input parameters to the decision system, containing the given radius1
     * @return {@code true} if there exist three consecutive points on the plane that cannot all be contained
     * Within or on a circle of {@link InputParameters#getRadius1()} otherwise {@code false}
     */

    @Override

    public boolean evaluate(List<Coordinate> coordinates, InputParameters inputParameters) {

        if (coordinates.size() < 3 ){
            return false;
        }

        for (int i = 0; i + 2 < coordinates.size(); i++){
            Coordinate a = coordinates.get(i);
            Coordinate b = coordinates.get(i+1);
            Coordinate c = coordinates.get(i+2);

            if (GeometryHelper.isCollinear(a,b,c)){
                double diameter = GeometryHelper.lineLength(a,b,c);
                if (diameter / 2 > inputParameters.getRadius1()){
                    return true;
                }
                continue;
            }

            double r = GeometryHelper.circumcircleRadius(a, b, c);
            if(r > inputParameters.getRadius1()){
                return true;
            }
        }
        return false;

    }

}
