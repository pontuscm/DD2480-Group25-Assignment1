package se.kth.dd2480.group25.assignment1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static se.kth.dd2480.group25.assignment1.test_utils.CoordinateTestUtils.repeating;

@ExtendWith(MockitoExtension.class)
public class LaunchDeciderTest {

    @Mock
    private LogicalConnectorMatrix lcm;

    @Mock
    private UnlockingMatrix pum;

    private LaunchDecider launchDecider = new LaunchDecider();

    @Test
    void mustAllowLaunch() {
        when(lcm.applyConditionsMetVector(anyList())).thenReturn(pum);
        when(pum.applyPreliminaryUnlockingVector(anyList())).thenReturn(List.of(true, true, true, true));

        boolean result = launchDecider.decide(4, repeating(5, 5, 5, 4), new InputParameters(), lcm, List.of());
        assertTrue(result);
    }

    @Test
    void mustRejectLaunch() {
        when(lcm.applyConditionsMetVector(anyList())).thenReturn(pum);
        when(pum.applyPreliminaryUnlockingVector(anyList())).thenReturn(List.of(true, true, true, false));

        boolean result = launchDecider.decide(4, repeating(5, 5, 5, 4), new InputParameters(), lcm, List.of());
        assertFalse(result);
    }
}
