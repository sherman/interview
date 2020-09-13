package org.sherman.interview.misc;

import org.testng.annotations.Test;

import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

public class SquaresOfSortedArray {
    public int[] sortSquares(int[] elements) {
        int negativeIndex = 0;
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] < 0) {
                negativeIndex++;
            } else {
                break;
            }
        }

        int l = negativeIndex - 1;
        int r = negativeIndex;
        int index = 0;
        int[] newElements = new int[elements.length];

        while (l >= 0 && r < elements.length) {
            int left = elements[l] * elements[l];
            int right = elements[r] * elements[r];
            if (left < right) {
                newElements[index] = left;
                l--;
            } else {
                newElements[index] = right;
                r++;
            }
            index++;
        }

        while (l >= 0) {
            newElements[index++] = elements[l] * elements[l];
            l--;
        }

        while (r < elements.length) {
            newElements[index++] = elements[r] * elements[r];
            r++;
        }

        return newElements;
    }

    @Test
    public void check() {
        assertArrayEquals(sortSquares(new int[]{-3, -1, 2, 3, 4}), new int[]{1, 4, 9, 9, 16});
        assertArrayEquals(sortSquares(new int[]{-6, -3, -1, 2, 3, 4}), new int[]{1, 4, 9, 9, 16, 36});
    }
}
