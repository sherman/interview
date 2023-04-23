package com.leetcode.assorted_2023;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TwoSumIIInputArrayIsSorted {
    public int[] twoSum(int[] numbers, int target) {
        var l = 0;
        var r = numbers.length - 1;

        while (l < r) {
            if (numbers[l] + numbers[r] == target) {
                return new int[] {l + 1, r + 1};
            }

            if (numbers[l] < target - numbers[r]) {
                l++;

            }

            if (numbers[r] > target - numbers[l]) {
                r--;
            }
        }

        return new int[] {};
    }

    @Test
    public void test() {
        // 2, 7, 11, 15 = 9      # 9 - 2 = 7          9 - 7 = 2       9 - 15 = -6    9 - 11 = -2
        // <- <-

        // 2, 7, 11, 15 = 18     # 18 - 2 = 16        18 - 7 = 11     18 - 11 = 7    18 - 15 = 3
        // -> <-

        // if l > (target - r) l++
        // if r > (target - l) r--

        // 2, 7, 11, 15 = 22
        // ->

        Assert.assertEquals(twoSum(new int[] {2, 7, 11, 15}, 9), new int[] {1, 2});
        Assert.assertEquals(twoSum(new int[] {2, 7, 11, 15}, 18), new int[] {2, 3});
        Assert.assertEquals(twoSum(new int[] {-1, -1, 1, 1, 1}, -2), new int[] {1, 2});
    }
}
