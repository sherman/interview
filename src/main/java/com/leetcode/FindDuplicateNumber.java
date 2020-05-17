package com.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FindDuplicateNumber {
    private static final Logger logger = LoggerFactory.getLogger(FindDuplicateNumber.class);

    public static int findDuplicate(int[] nums) {
        int l = 0;
        int r = nums.length - 1;

        while (l < r) {
            int mid = l + (r - l) / 2;
            int count = 0;
            for (int v : nums) {
                if (v <= mid) {
                    count++;
                }
            }

            logger.info("{} > {} {} {}", count, mid, l, r);

            if (count > mid) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    @Test
    public void findDuplicate() {
        Assert.assertEquals(FindDuplicateNumber.findDuplicate(new int[]{1, 3, 4, 2, 2}), 2);
        Assert.assertEquals(FindDuplicateNumber.findDuplicate(new int[]{3, 1, 3, 4, 2}), 3);
    }
}
