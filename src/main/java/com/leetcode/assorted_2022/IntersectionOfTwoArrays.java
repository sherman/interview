package com.leetcode.assorted_2022;

import java.util.ArrayList;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IntersectionOfTwoArrays {
    public static int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) {
            return new int[] {};
        }

        var numbers = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums1.length; i++) {
            if (null != numbers.putIfAbsent(nums1[i], 1)) {
                numbers.put(nums1[i], numbers.get(nums1[i]) + 1);
            }
        }

        var result = new ArrayList<Integer>();
        for (int i = 0; i < nums2.length; i++) {
            Integer counter = numbers.get(nums2[i]);
            if (counter != null && counter > 0) {
                result.add(nums2[i]);
                numbers.put(nums2[i], numbers.get(nums2[i]) - 1);
            }
        }

        return result.stream()
            .mapToInt(v -> v)
            .toArray();
    }

    @Test
    public void test() {
        Assert.assertEquals(
            intersect(new int[] {4, 9, 5}, new int[] {9, 4, 9, 8, 4}),
            new int[] {9, 4}
        );

        Assert.assertEquals(
            intersect(new int[] {1, 2, 2, 1}, new int[] {2, 2}),
            new int[] {2, 2}
        );
    }
}
