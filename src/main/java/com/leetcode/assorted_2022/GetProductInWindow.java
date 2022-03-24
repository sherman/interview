package com.leetcode.assorted_2022;

import java.util.ArrayList;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetProductInWindow {
    public int[] getProduct(int[] data, int k) {
        if (k == 0 || k > data.length) {
            throw new IllegalArgumentException("Invalid args., expected 0 < k < data.length");
        }

        int currentSum = 1;
        int currentElements = 0;

        var result = new ArrayList<Integer>();
        int zeroPosition = -1;

        for (int i = 0; i < data.length; i++) {
            if (data[i] == 0) {
                zeroPosition = i;
            }

            if (currentElements < k) {
                currentSum = currentSum * data[i];
                currentElements++;
            }

            if (currentElements == k) {
                if (zeroPosition >= i - k + 1) {
                    result.add(0);
                    currentSum = data[i];
                } else {
                    result.add(currentSum);
                    currentSum = currentSum / data[i - k + 1];
                }
                currentElements--;
            }
        }

        return result.stream()
            .mapToInt(v -> v)
            .toArray();
    }

    @Test
    public void test() {
        Assert.assertEquals(getProduct(new int[] {1, 2, 3}, 2), new int[] {2, 6});
        Assert.assertEquals(getProduct(new int[] {0, 2, 3}, 2), new int[] {0, 6});
        Assert.assertEquals(getProduct(new int[] {0, 2, 0, 2, 3}, 2), new int[] {0, 0, 0, 6});
        Assert.assertEquals(getProduct(new int[] {0, 2, 0, 2, 3, 0}, 2), new int[] {0, 0, 0, 6, 0});
        Assert.assertEquals(getProduct(new int[] {0, 2, 1, 2, 3, 0}, 2), new int[] {0, 2, 2, 6, 0});
        Assert.assertEquals(getProduct(new int[] {0, 0, 1, 2, 3, 0}, 2), new int[] {0, 0, 2, 6, 0});
    }
}
