package com.leetcode;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class MergeSortedArray {
    @Test(dataProvider = "cases")
    public void merge(int[] nums1, int m, int[] nums2, int n, int[] expected) {
        merge(nums1, m, nums2, n);
        assertEquals(nums1, expected);
    }

    private void merge(int[] nums1, int m, int[] nums2, int n) {
        while (n > 0 && m > 0) {
            int a1 = nums1[m - 1];
            int a2 = nums2[n - 1];
            if (a1 > a2) {
                nums1[n + m - 1] = nums1[m - 1];
                m--;
            } else {
                nums1[n + m - 1] = nums2[n - 1];
                n--;
            }
        }

        while (n > 0) {
            nums1[n + m - 1] = nums2[n - 1];
            n--;
        }
    }

    @DataProvider
    private Object[][] cases() {
        return new Object[][]{
            {
                new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3, new int[]{1, 2, 2, 3, 5, 6}
            },

            {
                new int[]{0}, 0, new int[]{1}, 1, new int[]{1}
            },

            {
                new int[]{2, 0}, 1, new int[]{1}, 1, new int[]{1, 2}
            }
        };
    }
}
