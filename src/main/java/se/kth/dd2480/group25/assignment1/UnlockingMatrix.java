package se.kth.dd2480.group25.assignment1;

import java.util.*;

public class UnlockingMatrix {
    private Boolean[][] matrix = new Boolean[15][15];

    public List<Boolean> applyPreliminaryUnlockingVector(List<Boolean> preliminaryUnlockingVector) {
        List<Boolean> unlockingVector = new ArrayList<>();

        for (int i = 0; i < preliminaryUnlockingVector.size(); i++) {
            if (preliminaryUnlockingVector.get(i)) {
                unlockingVector.add(Collections.frequency(Arrays.asList(this.matrix[i]), false) == 0);
            }
            else { unlockingVector.add(true); } //if false, the condition isn't relevant and the corresponding value is unconditionally true
        }
        return unlockingVector;
    }

    public void setIndex(int i, int j, Boolean value) {
        this.matrix[i][j] = value;
    }

}
