package com.leetcode;

public class IsMonotonic {
    public boolean isMonotonic(int[] A) {
        if (A.length == 1) {
            return true;
        }

        boolean increase = A[0] < A[A.length - 1];
        for (int i = 1; i < A.length; i++) {
            if (increase) {
                if (A[i - 1] > A[i]) {
                    return false;
                }
            } else {
                if (A[i - 1] < A[i]) {
                    return false;
                }
            }
        }

        return true;
    }
}
