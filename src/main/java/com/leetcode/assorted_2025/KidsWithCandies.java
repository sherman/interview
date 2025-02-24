package com.leetcode.assorted_2025;

import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

public class KidsWithCandies {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        var max = Integer.MIN_VALUE;
        for (var i = 0; i < candies.length; i++) {
            max = Math.max(max, candies[i]);
        }
        var result = new ArrayList<Boolean>();
        for (var i = 0; i < candies.length; i++) {
            if (candies[i] + extraCandies >= max) {
                result.add(true);
            } else {
                result.add(false);
            }
        }
        return result;
    }

    @Test
    public void test() {
        Assert.assertEquals(kidsWithCandies(new int[] {2, 3, 5, 1, 3}, 3), List.of(true, true, true, false, true));
    }
}
