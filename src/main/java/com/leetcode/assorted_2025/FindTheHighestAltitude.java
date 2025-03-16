package com.leetcode.assorted_2025;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FindTheHighestAltitude {
    public int largestAltitude(int[] gain) {
        var prev = 0;
        var max = 0;
        for (var i = 0; i < gain.length; i++) {
            var candidate = prev + gain[i];
            max = Math.max(max, candidate);
            prev = candidate;
        }

        return max;
    }

    @Test
    public void test() {
        Assert.assertEquals(largestAltitude(new int[] {-5, 1, 5, 0, -7}), 1);
        Assert.assertEquals(largestAltitude(new int[] {-4, -3, -2, -1, 4, 3, 2}), 0);
        Assert.assertEquals(largestAltitude(new int[] {0, 0, 0, 0}), 0);  // no altitude change
        Assert.assertEquals(largestAltitude(new int[] {10, -10, 10, -10}), 10);  // alternating gains and losses
        Assert.assertEquals(largestAltitude(new int[] {-1, -2, -3, -4}), 0);  // always decreasing
        Assert.assertEquals(largestAltitude(new int[] {1, 2, 3, 4}), 10);  // always increasing
    }
}
