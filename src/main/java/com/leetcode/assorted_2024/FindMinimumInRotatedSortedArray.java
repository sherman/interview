package com.leetcode.assorted_2024;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FindMinimumInRotatedSortedArray {

    public int findMin(int[] nums) {
        // sorted
        if (nums[0] < nums[nums.length - 1]) {
            return nums[0];
        }

        if (nums.length == 1) {
            return nums[0];
        }

        if (nums.length == 2) {
            if (nums[0] > nums[1]) {
                return nums[1];
            }
        }

        var l = 0;
        var r = nums.length - 1;

        while (l <= r) {
            var mid = (l + r) / 2;
            var midElement = nums[mid];

            if (mid - 1 >= 0 && mid + 1 < nums.length) {
                var before = nums[mid - 1];
                var after = nums[mid + 1];

                if (before < midElement && midElement > after) {
                    return after;
                }

                if (before > midElement && midElement < after) {
                    return midElement;
                }
            }

            if (nums[l] > midElement) {
                r = mid - 1;
            } else if (nums[r] < midElement) {
                l = mid + 1;
            }
        }

        return 1;
    }


    @Test
    public void cases() {
        Assert.assertEquals(findMin(new int[]{1}), 1);
        Assert.assertEquals(findMin(new int[]{4, 5, 6, 3}), 3);
        Assert.assertEquals(findMin(new int[]{4, 5, 6, 7, 0, 1, 2}), 0);
        Assert.assertEquals(findMin(new int[]{2, 3, 4, 1}), 1);
        Assert.assertEquals(findMin(new int[]{8, 5, 6, 7}), 5);
        Assert.assertEquals(findMin(new int[]{3, 1, 2}), 1);
        Assert.assertEquals(findMin(new int[]{2, 1}), 1);
        Assert.assertEquals(findMin(new int[]{3, 4, 5, 1, 2}), 1);
        Assert.assertEquals(findMin(new int[]{11, 13, 15, 17}), 11);
    }
}
