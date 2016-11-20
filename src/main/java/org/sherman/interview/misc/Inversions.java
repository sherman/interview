package org.sherman.interview.misc;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * @author sherman
 * @since 20/11/2016
 */
public class Inversions {
    private Inversions() {
    }

    public static int getCountOfInversions(@NotNull int[] data) {
        if (data.length == 1) {
            return 0;
        }

        int mid = data.length / 2;

        int[] left = Arrays.copyOfRange(data, 0, mid);
        int[] right = Arrays.copyOfRange(data, mid, data.length);

        int leftCount = getCountOfInversions(left);
        int rightCount = getCountOfInversions(right);

        return leftCount + rightCount + mergeAndCountInversion(left, right);
    }

    private static int mergeAndCountInversion(int left[], int right[]) {
        int i = 0, j = 0;

        int inversions = 0;
        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                i++;
            } else if (left[i] > right[j]) {
                j++;
                inversions++;
            } else {
                i++;
                j++;
            }
        }

        return inversions;
    }
}
