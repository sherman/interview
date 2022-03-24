package com.leetcode.assorted_2022;

import java.util.ArrayList;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetSumInWindow {
    public int[] getSum(int[] data, int k) {
        if (k == 0 || k > data.length) {
            throw new IllegalArgumentException("Invalid args., expected 0 < k < data.length");
        }

        int currentSum = 0;
        int currentElements = 0;

        var result = new ArrayList<Integer>();

        for (int i = 0; i < data.length; i++) {
            if (currentElements < k) {
                currentSum += data[i];
                currentElements++;
            }

            if (currentElements == k) {
                result.add(currentSum);
                currentSum -= data[i - k + 1];
                currentElements--;
            }
        }

        return result.stream()
            .mapToInt(v -> v)
            .toArray();
    }

    @Test
    public void test() {
        Assert.assertEquals(getSum(new int[] {1, 2, 3}, 2), new int[] {3, 5});
    }

}
