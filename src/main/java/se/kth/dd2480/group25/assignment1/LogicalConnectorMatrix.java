package se.kth.dd2480.group25.assignment1;

import java.util.List;
import se.kth.dd2480.group25.assignment1.UnlockingMatrix;

public class LogicalConnectorMatrix {
    private OPERATION[][] matrix = new OPERATION[15][15];

    public enum OPERATION {
        ANDD, ORR, NOTUSED
    }

    public UnlockingMatrix applyConditionsMetVector(List<Boolean> conditionsMetVector) {
        UnlockingMatrix um = new UnlockingMatrix();

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (this.matrix[i][j] == OPERATION.ANDD) {
                    um.setIndex(i, j, (conditionsMetVector.get(i) && conditionsMetVector.get(j)));
                }
                else if (this.matrix[i][j] == OPERATION.ORR) {
                    um.setIndex(i, j, (conditionsMetVector.get(i) || conditionsMetVector.get(j)));
                }
                else { um.setIndex(i, j, true); }
            }
        }

        return um;
    }

    public void setIndex(int i, int j, OPERATION value) {
        this.matrix[i][j] = value;
    }
}
