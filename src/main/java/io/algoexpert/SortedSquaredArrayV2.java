package io.algoexpert;

import org.testng.annotations.Test;
import org.testng.internal.junit.ArrayAsserts;

public class SortedSquaredArrayV2 {
    public int[] sortedSquaredArray(int[] array) {
        var result = new int[array.length];
        var i = 0;
        var j = array.length - 1;
        var iterator = array.length - 1;

        while (i < j) {
            var left = array[i] * array[i];
            var right = array[j] * array[j];
            if (left > right) {
                result[iterator] = left;
                i++;
                iterator--;
            } else if (left < right) {
                result[iterator] = right;
                j--;
                iterator--;
            } else {
                result[iterator] = left;
                iterator--;
                result[iterator] = right;
                iterator--;
                i++;
                j--;
            }
        }

        if (j == i) {
            result[iterator] = array[i] * array[i];
        }

        // -5, -3, -2, 3, 4, 6
        // 25 9  4    9  16 36

        return result;
    }

    @Test
    public void test() {
        ArrayAsserts.assertArrayEquals(sortedSquaredArray(new int[]{-10, -5, 0, 5, 10}), new int[]{0, 25, 25, 100, 100});
        ArrayAsserts.assertArrayEquals(sortedSquaredArray(new int[]{-1}), new int[]{1});
        ArrayAsserts.assertArrayEquals(sortedSquaredArray(new int[]{2}), new int[]{4});
        ArrayAsserts.assertArrayEquals(sortedSquaredArray(new int[]{-5, -2, 3, 4}), new int[]{4, 9, 16, 25});
        ArrayAsserts.assertArrayEquals(sortedSquaredArray(new int[]{-6, -5, -2, 3, 4}), new int[]{4, 9, 16, 25, 36});
        ArrayAsserts.assertArrayEquals(sortedSquaredArray(new int[]{1, 2, 3, 4, 5}), new int[]{1, 4, 9, 16, 25});
        ArrayAsserts.assertArrayEquals(sortedSquaredArray(new int[]{-4, -3, -2}), new int[]{4, 9, 16});
    }
}
