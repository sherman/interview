package com.leetcode.assorted_2022;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RemoveDuplicatesFromSortedArray {
    private static final Logger logger = LoggerFactory.getLogger(RemoveDuplicatesFromSortedArray.class);

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return 1;
        }

        int uniqueSize = 1;
        int current = nums[0];
        int index = 0;
        logger.info("[{}]", current);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != current) {
                logger.info("[{}] [{}]", current, i);
                nums[++index] = nums[i];
                uniqueSize++;
                current = nums[i];
            }
        }

        return uniqueSize;
    }

    @Test
    public void test() {
        var data1 = new int[] {1, 1, 2};
        Assert.assertEquals(removeDuplicates(data1), 2);
        Assert.assertEquals(data1[0], 1);
        Assert.assertEquals(data1[1], 2);


        var data2 = new int[] {1, 2, 3};
        Assert.assertEquals(removeDuplicates(data2), 3);
        Assert.assertEquals(data2[0], 1);
        Assert.assertEquals(data2[1], 2);
        Assert.assertEquals(data2[2], 3);

        var data3 = new int[] {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        Assert.assertEquals(removeDuplicates(data3), 5);
        Assert.assertEquals(data3[0], 0);
        Assert.assertEquals(data3[1], 1);
        Assert.assertEquals(data3[2], 2);
        Assert.assertEquals(data3[3], 3);
        Assert.assertEquals(data3[4], 4);
    }
}
