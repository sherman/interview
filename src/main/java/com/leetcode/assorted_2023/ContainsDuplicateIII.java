package com.leetcode.assorted_2023;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContainsDuplicateIII {
    public boolean containsNearbyDuplicate(int[] nums, int k, int m) {
        for (var i = 0; i < nums.length; i++) {
            var data = new ArrayList<Integer>();
            for (var j = i; j <= Math.min(i + k, nums.length - 1); j++) {
                data.add(nums[j]);
            }
            Collections.sort(data);

            for (var j = 1; j < data.size(); j++) {
                if (Math.abs(data.get(j) - data.get(j - 1)) <= m) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean containsNearbyDuplicate2(int[] nums, int k, int m) {
        var set = new TreeSet<Long>();

        for (int i = 0; i < nums.length; i++) {
            Long num = (long) nums[i];
            var floor = set.floor(num);
            var ceil = set.ceiling(num);

            if (floor != null && Math.abs(floor - num) <= m) {
                return true;
            }

            if (ceil != null && Math.abs(ceil - num) <= m) {
                return true;
            }

            set.add(num);

            if (set.size() > k) {
                set.remove((long) nums[i - k]);
            }
        }

        return false;
    }

    @Test
    public void test1() {
        Assert.assertTrue(containsNearbyDuplicate(new int[] {1, 2, 3, 1}, 3, 0));
        Assert.assertFalse(containsNearbyDuplicate(new int[] {1, 5, 9, 1, 5, 9}, 2, 3));
    }

    @Test
    public void test2() {
        Assert.assertTrue(containsNearbyDuplicate2(new int[] {1, 2, 3, 1}, 3, 0));
        Assert.assertFalse(containsNearbyDuplicate2(new int[] {1, 5, 9, 1, 5, 9}, 2, 3));
    }
}
