package com.leetcode;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;
import org.testng.internal.junit.ArrayAsserts;

public class NextGreaterElementI {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> index = getIndex(nums2);

        int[] res = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {
            int r = getGreater(nums2, nums1[i], index.get(nums1[i]));
            res[i] = r;
        }

        return res;
    }

    private Map<Integer, Integer> getIndex(int[] data) {
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < data.length; i++) {
            index.put(data[i], i);
        }
        return index;
    }

    private int getGreater(int[] data, int element, int index) {
        for (int i = index; i < data.length; i++) {
            if (data[i] > element) {
                return data[i];
            }
        }
        return -1;
    }

    @Test
    public void test() {
        ArrayAsserts.assertArrayEquals(nextGreaterElement(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2}), new int[]{-1, 3, -1});
    }
}
