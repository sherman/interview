package com.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchInsertPosition {
    private static final Logger logger = LoggerFactory.getLogger(SearchInsertPosition.class);

    public static int searchInsert(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;

        while (l < r) {
            int midIndex = (r + l) / 2;
            int mid = nums[midIndex];

            logger.info("[{}] [{}]", mid, midIndex);

            if (mid < target) {
                l = midIndex + 1;
            } else if (mid > target) {
                r = midIndex - 1;
            } else if (mid == target) {
                return midIndex;
            }
        }

        return (nums[l] < target) ? l + 1 : l;
    }

    @Test
    public void searchInsert() {
        Assert.assertEquals(SearchInsertPosition.searchInsert(new int[]{1, 3, 5, 6}, 5), 2);
        Assert.assertEquals(SearchInsertPosition.searchInsert(new int[]{1, 3, 5, 6}, 2), 1);
        Assert.assertEquals(SearchInsertPosition.searchInsert(new int[]{1, 3, 5, 6}, 7), 4);
        Assert.assertEquals(SearchInsertPosition.searchInsert(new int[]{1, 3, 5, 6}, 0), 0);
    }
}
