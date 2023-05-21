package com.leetcode.assorted_2023;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FindFirstAndLastPositionOfElementInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        var result = new int[] {-1, -1};

        if (nums.length == 1) {
            if (nums[0] == target) {
                return new int[] {0, 0};
            } else {
                return result;
            }
        }

        var l = 0;
        var r = nums.length - 1;

        while (l <= r) {
            var midIndex = (l + r) / 2;
            var mid = nums[midIndex];

            if (mid == target) {
                var temp = midIndex;
                while (midIndex > 0 && nums[midIndex] == target) {
                    midIndex--;
                }

                result[0] = midIndex >= 0 && nums[midIndex] == target ? midIndex : midIndex + 1;
                midIndex = temp;
                while (midIndex < nums.length && nums[midIndex] == target) {
                    midIndex++;
                }
                result[1] = midIndex < nums.length && nums[midIndex] == target ? midIndex : midIndex - 1;
                return result;
            } else if (mid < target) {
                l = midIndex + 1;
            } else {
                r = midIndex - 1;
            }
        }

        return result;
    }

    @Test
    public void test() {
        Assert.assertEquals(searchRange(new int[] {5, 7, 7, 8, 8, 10}, 8), new int[] {3, 4});
        Assert.assertEquals(searchRange(new int[] {2, 2}, 2), new int[] {0, 1});
        Assert.assertEquals(searchRange(new int[] {0}, 0), new int[] {0, 0});
        Assert.assertEquals(searchRange(new int[] {1}, 0), new int[] {-1, -1});
        Assert.assertEquals(searchRange(new int[] {1, 1, 1, 1}, 1), new int[] {0, 3});
        Assert.assertEquals(searchRange(new int[] {1, 1, 2, 2, 3, 4, 9, 9}, 3), new int[] {4, 4});
    }
}
