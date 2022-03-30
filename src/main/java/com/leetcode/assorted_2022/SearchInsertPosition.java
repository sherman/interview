package com.leetcode.assorted_2022;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchInsertPosition {
    private static final Logger logger = LoggerFactory.getLogger(SearchInsertPosition.class);

    public int searchInsert(int[] nums, int target) {
        var lo = 0;
        var hi = nums.length - 1;

        if (target < nums[lo]) {
            return 0;
        }

        if (target > nums[hi]) {
            return nums.length;
        }

        var mid = lo + (hi - lo) / 2;
        while (lo < hi) {
            // when the target is not in the list
            if (hi - lo == 1 && target > nums[lo]) {
                return lo + 1;
            }

            mid = lo + (hi - lo) / 2;

            logger.info("{} {} {}", lo, mid, hi);
            // when the target is in the list
            if (target == nums[mid]) {
                return mid;
            } else if (target > nums[mid]) {
                lo = mid;
            } else {
                hi = mid;
            }
        }

        return mid;
    }

    @Test
    public void test() {
        Assert.assertEquals(searchInsert(new int[] {1}, 1), 0);
        Assert.assertEquals(searchInsert(new int[] {1, 3}, 1), 0);
        Assert.assertEquals(searchInsert(new int[] {1, 3, 4, 5}, 2), 1);
        Assert.assertEquals(searchInsert(new int[] {1, 3, 5, 6}, 5), 2);
        Assert.assertEquals(searchInsert(new int[] {1, 2, 3, 4, 6}, 5), 4);
        Assert.assertEquals(searchInsert(new int[] {1, 2, 3, 4, 6}, 7), 5);
        Assert.assertEquals(searchInsert(new int[] {2, 2, 3, 4, 6}, 1), 0);
    }
}
