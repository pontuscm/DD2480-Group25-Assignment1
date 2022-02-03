package se.kth.dd2480.group25.assignment1;

import se.kth.dd2480.group25.assignment1.lic.*;

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
        return List.of(
            new LaunchInterceptorCondition0(),
            new LaunchInterceptorCondition1(),
            new LaunchInterceptorCondition2(),
            new LaunchInterceptorCondition3(),
            new LaunchInterceptorCondition4(),
            new LaunchInterceptorCondition5(),
            new LaunchInterceptorCondition6(),
            new LaunchInterceptorCondition7(),
            new LaunchInterceptorCondition8(),
            new LaunchInterceptorCondition9(),
            new LaunchInterceptorCondition10(),
            new LaunchInterceptorCondition11(),
            new LaunchInterceptorCondition12(),
            new LaunchInterceptorCondition13(),
            new LaunchInterceptorCondition14()
        );
    }

}
