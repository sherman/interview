package io.algoexpert;

import java.util.HashSet;
import org.testng.annotations.Test;
import org.testng.internal.junit.ArrayAsserts;

public class TwoNumberSum {

    public static int[] twoNumberSum(int[] array, int targetSum) {
        var cache = new HashSet<Integer>();
        for (var element : array) {
            var diff = targetSum - element;
            if (cache.contains(diff)) {
                return new int[] {diff, element};
            } else {
                cache.add(element);
            }
        }
        return new int[] {};
    }

    @Test
    public void test() {
        ArrayAsserts.assertArrayEquals(twoNumberSum(new int[] {3, 5, -4, 8, 11, 1, -1, 6}, 10), new int[] {11, -1});
    }
}
