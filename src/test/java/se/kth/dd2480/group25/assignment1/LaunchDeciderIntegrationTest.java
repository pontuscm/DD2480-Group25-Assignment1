package se.kth.dd2480.group25.assignment1;

import org.junit.jupiter.api.Test;
import se.kth.dd2480.group25.assignment1.test_utils.CoordinateTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static se.kth.dd2480.group25.assignment1.test_utils.CoordinateTestUtils.point;
import static se.kth.dd2480.group25.assignment1.test_utils.CoordinateTestUtils.repeating;

public class LaunchDeciderIntegrationTest {

    private LaunchDecider launchDecider = new LaunchDecider();

    @Test
    void mustLaunchIfMetConditions() {

        List<Coordinate> coordinates =
            CoordinateTestUtils.ListBuilder.create()
                                           .add(repeating(10, 20, 5, 4))
                                           .add(point(40, 40))
                                           .add(repeating(55, 20, 5, 2))
                                           .add(point(70, 10))
                                           .add(point(75, 23))
                                           .get();

        InputParameters parameters =
            new InputParameters(20.0, 30.0, 15.0, 140.0, 3, 2, 15.0,
                                3, 2, 3, 2, 4, 2, 3, 2, 1,
                                15.0, 25.0, 75.0);

        LogicalConnectorMatrix lcm = initLcm();
        setRelation(lcm, 3, 10, LogicalConnectorMatrix.OPERATION.ANDD);
        setRelation(lcm, 3, 14, LogicalConnectorMatrix.OPERATION.ANDD);
        setRelation(lcm, 2, 14, LogicalConnectorMatrix.OPERATION.ORR);

        List<Boolean> puv = List.of(false, false, true, false, false,
                                    false, false, true, false, false,
                                    true, false, false, false, true);

        boolean result = launchDecider.decide(coordinates.size(), coordinates, parameters, lcm, puv);
        assertTrue(result);
    }

    @Test
    void mustNotLaunchIfUnmetConditions() {
        List<Coordinate> coordinates =
            CoordinateTestUtils.ListBuilder.create()
                                           .add(repeating(10, 20, 5, 4))
                                           .add(point(40, 40))
                                           .add(repeating(55, 20, 5, 2))
                                           .add(point(70, 10))
                                           .add(point(75, 23))
                                           .get();

        InputParameters parameters =
            new InputParameters(20.0, 30.0, 15.0, 140.0, 3, 2, 15.0,
                                3, 2, 3, 2, 4, 2, 3, 2, 1,
                                15.0, 25.0, 45.0);

        LogicalConnectorMatrix lcm = initLcm();
        setRelation(lcm, 3, 10, LogicalConnectorMatrix.OPERATION.ANDD);
        setRelation(lcm, 3, 14, LogicalConnectorMatrix.OPERATION.ANDD);
        setRelation(lcm, 2, 14, LogicalConnectorMatrix.OPERATION.ORR);

        List<Boolean> puv = List.of(false, false, true, false, false,
                                    false, false, true, false, false,
                                    true, false, false, false, true);

        boolean result = launchDecider.decide(coordinates.size(), coordinates, parameters, lcm, puv);
        assertFalse(result);
    }

    private LogicalConnectorMatrix initLcm() {
        var lcm = new LogicalConnectorMatrix();
        for (int i = 0; i < 15; ++i) {
            for (int j = 0; j < 15; ++j) {
                lcm.setIndex(i, j, LogicalConnectorMatrix.OPERATION.NOTUSED);
            }
        }
        return lcm;
    }

    private void setRelation(LogicalConnectorMatrix lcm, int firstLic, int secondLic,
                             LogicalConnectorMatrix.OPERATION operation) {
        lcm.setIndex(firstLic, secondLic, operation);
        lcm.setIndex(secondLic, firstLic, operation);
    }

}
