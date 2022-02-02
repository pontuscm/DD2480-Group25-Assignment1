package se.kth.dd2480.group25.assignment1;

import se.kth.dd2480.group25.assignment1.lic.LaunchInterceptorCondition;

import java.util.List;

public class LaunchDecider {

    public static void main(String[] args) {
        System.out.println("This application will decide whether to launch hypothetical ballistic countermeasures");
    }

    public boolean decide(int numPoints, List<Coordinate> coordinates, InputParameters parameters,
                          LogicalConnectorMatrix logicalConnectorMatrix, List<Boolean> preliminaryUnlockingVector) {

        List<LaunchInterceptorCondition> launchInterceptorConditions = createLaunchInterceptorConditions();

        List<Boolean> conditionsMetVector =
            launchInterceptorConditions.stream().map(condition -> condition.evaluate(coordinates, parameters)).toList();

        UnlockingMatrix preliminaryUnlockingMatrix = logicalConnectorMatrix.applyConditionsMetVector(
            conditionsMetVector);

        List<Boolean> finalUnlockingVector =
            preliminaryUnlockingMatrix.applyPreliminaryUnlockingVector(preliminaryUnlockingVector);

        boolean shouldLaunch = finalUnlockingVector.stream().allMatch(Boolean::booleanValue);

        return shouldLaunch;
    }

    private List<LaunchInterceptorCondition> createLaunchInterceptorConditions() {
        // TODO: Instantiate all LICs once ready
        return List.of();
    }

}
