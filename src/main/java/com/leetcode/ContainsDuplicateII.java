package com.leetcode;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.assertEquals;

public class ContainsDuplicateII {
    public boolean contains(int[] nums, int k) {
        Map<Integer, SortedSet<Integer>> index = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            index.putIfAbsent(nums[i], new TreeSet<>());
            SortedSet<Integer> indexes = index.get(nums[i]);
            indexes.add(i);
        }

        return index.values().stream()
            .filter(v -> v.size() > 1)
            .anyMatch(
                v -> {
                    Integer prev = null;
                    for (Integer current : v) {
                        if (prev != null) {
                            if (Math.abs(prev - current) <= k) {
                                return true;
                            }
                        }
                        prev = current;
                    }
                    return false;
                }
            );
    }

    @Test(dataProvider = "cases")
    public void contains(int[] nums, int k, boolean expected) {
        assertEquals(contains(nums, k), expected);
    }

    @DataProvider
    private static Object[][] cases() {
        return new Object[][]{
            {new int[]{1, 2, 3, 1}, 3, true},

            {new int[]{1,0,1,1}, 1, true},

            {new int[]{1,2,3,1,2,3}, 2, false},

            {new int[]{}, 1, false}
        };
    }
}
