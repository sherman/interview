package org.sherman.interview.misc;

public class MaximumDistanceInArrays {
    public static int getMaxDistance(int[][] arrays) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0 && arrays[i][0] < min) {
                min = arrays[i][0];
            } else if (arrays[i].length > 0 && arrays[i][arrays[i].length - 1] > max) {
                max = arrays[i][arrays[i].length - 1];
            }
        }

        return max - min;
    }
}
