package com.leetcode.assorted_2025;

import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FindKthLargest {
    public int findKthLargest(int[] nums, int k) {
        var index = new HashMap<Integer, Integer>();
        var max = Integer.MIN_VALUE;

        for (var item : nums) {
            var value = index.getOrDefault(item, 0);
            index.put(item, value + 1);
            max = Math.max(max, item);
        }

        while (true) {
            var value = index.get(max);
            if (value != null) {
                while (k > 0 && value > 0) {
                    k--;
                    value--;
                }

                if (k == 0) {
                    return max;
                }
            }
            max = max - 1;
        }
    }

    @Test
    public void test() {
        Assert.assertEquals(findKthLargest(new int[] {3, 2, 1, 5, 6, 4}, 2), 5);
        Assert.assertEquals(findKthLargest(new int[] {3, 2, 3, 1, 2, 4, 5, 5, 6}, 4), 4);
    }
}
