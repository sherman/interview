package com.leetcode.assorted_2025;

import java.util.HashSet;
import java.util.List;

public class FindTheDifferenceOfTwoArrays {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        var index1 = new HashSet<Integer>();
        for (var item : nums1) {
            index1.add(item);
        }
        var index2 = new HashSet<Integer>();
        for (var item : nums2) {
            index2.add(item);
        }

        var result1 = index1.stream().filter(elt -> !index2.contains(elt)).toList();
        var result2 = index2.stream().filter(elt -> !index1.contains(elt)).toList();
        return List.of(result1, result2);
    }
}
