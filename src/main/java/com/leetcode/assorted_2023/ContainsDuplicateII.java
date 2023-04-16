package com.leetcode.assorted_2023;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContainsDuplicateII {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        var index = new HashMap<Integer, List<Integer>>();

        for (var i = 0; i < nums.length; i++) {
            var element = nums[i];
            var indexes = index.computeIfAbsent(element, ignored -> new ArrayList<>());
            indexes.add(i);

            if (indexes.size() > 1) {
                var prev = indexes.get(indexes.size() - 2);
                var last = indexes.get(indexes.size() - 1);
                if (Math.abs(last - prev) <= k) {
                    return true;
                }
            }
        }

        return false;
    }

    @Test
    public void test() {
        // 1, 2, 3, 1, 2, 3   2

        // 1 -> 0, 3
        // 2 -> 1, 4
        // 3 -> 2, 5

        //  1 (3) 1



        Assert.assertTrue(containsNearbyDuplicate(new int[] {1, 2, 3, 1}, 3));
    }
}
