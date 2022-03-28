package com.leetcode.assorted_2022;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NextPermutation {
    private static final Logger logger = LoggerFactory.getLogger(NextPermutation.class);

    public void nextPermutation(int[] nums) {
        int left = -1;
        for (var i = nums.length - 1; i >= 0; i--) {
            if (i - 1 >= 0 && nums[i] > nums[i - 1]) {
                left = i - 1;
                break;
            }
        }

        if (left == -1) {
            reverseArray(nums, 0);
            return;
        }

        logger.info("[{}]", nums[left]);

        var temp = nums[left + 1];
        nums[left + 1] = nums[left];
        nums[left] = temp;

        logger.info("[{}]", nums);
        reverseArray(nums, left + 1);
        logger.info("[{}]", nums);
    }

    private void reverseArray(int[] data, int from) {
        logger.info("{} {}", from, (data.length + from) / 2);
        for (var i = from; i < (data.length + from) / 2; i++) {
            var temp = data[i];
            data[i] = data[data.length - 1 - i + from];
            data[data.length - 1 - i +  + from] = temp;
        }
    }

    @Test
    public void test() {
        var nums = new int[] {10, 9, 4, 3, 1};
        nextPermutation(nums);
        Assert.assertEquals(nums, new int[] {1, 3, 4, 9, 10});

        nums = new int[] {5, 9, 4, 3, 1};
        nextPermutation(nums);
        Assert.assertEquals(nums, new int[] {9, 1, 3, 4, 5});
    }
}
