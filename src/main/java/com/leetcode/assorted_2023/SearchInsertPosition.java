package com.leetcode.assorted_2023;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        var l = 0;
        var r = nums.length - 1;

        if (target < nums[l]) {
            return 0;
        }

        if (target > nums[r]) {
            return nums.length;
        }

        var midIndex = (l + r) / 2;
        while (l < r) {
            // when the target is not in the list
            if (r - l == 1 && target > nums[l]) {
                return l + 1;
            }

            midIndex = (l + r) / 2;
            var mid = nums[midIndex];
            if (mid == target) {
                return midIndex;
            }
            if (target < mid) {
                r = midIndex;
            } else {
                l = midIndex;
            }
        }

        return midIndex;
    }

    @Test
    public void test() {
        Assert.assertEquals(searchInsert(new int[] {1, 3, 5, 6}, 7), 5);
        Assert.assertEquals(searchInsert(new int[] {1}, 2), 1);
        Assert.assertEquals(searchInsert(new int[] {1}, 0), 0);
        Assert.assertEquals(searchInsert(new int[] {1, 3, 5, 6}, 5), 2);
        Assert.assertEquals(searchInsert(new int[] {1, 3, 5, 6}, 2), 1);
        Assert.assertEquals(searchInsert(new int[] {1, 3, 5, 6}, 0), 0);
    }
}
