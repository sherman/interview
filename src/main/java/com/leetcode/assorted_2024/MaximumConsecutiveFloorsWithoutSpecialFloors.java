package com.leetcode.assorted_2024;

import java.util.HashSet;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MaximumConsecutiveFloorsWithoutSpecialFloors {
    public int maxConsecutive(int bottom, int top, int[] special) {
        var specials = new HashSet<Integer>();
        for (var floor : special) {
            specials.add(floor);
        }

        var max = 0;
        var current = 0;

        for (var i = bottom; i <= top; i++) {
            if (specials.contains(i)) {
                max = Math.max(max, current);
                current = 0;
            } else {
                current++;
            }
        }

        if (current > 0) {
            max = Math.max(max, current);
        }

        return max;
    }

    @Test
    public void test() {
        Assert.assertEquals(maxConsecutive(2, 9, new int[] {4, 6}), 3);
        Assert.assertEquals(maxConsecutive(6, 8, new int[] {7, 6, 8}), 0);
    }
}
