package com.leetcode.fb;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.Test;
import org.testng.internal.junit.ArrayAsserts;

public class IntersectionOfTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int l = 0;
        int r = 0;

        Set<Integer> intersect = new HashSet<>();

        while (l < nums1.length && r < nums2.length) {
            if (nums1[l] == nums2[r]) {
                intersect.add(nums1[l]);
                l++;
                r++;
            } else if (nums1[l] < nums2[r]) {
                l++;
            } else {
                r++;
            }
        }

        return intersect.stream()
                .mapToInt(v -> v)
                .toArray();
    }

    @Test
    public void test() {
        ArrayAsserts.assertArrayEquals(
                intersection(new int[]{1, 2, 2, 1}, new int[]{2, 2}),
                new int[]{2}
        );
    }
}
