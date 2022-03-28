package com.leetcode.assorted_2022;

import java.util.ArrayList;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SortArrayByParity {
    public int[] sortArrayByParity(int[] data) {
        var result = new ArrayList<Integer>(data.length);

        for (int i = 0; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                result.add(data[i]);
            }
        }

        for (int i = 0; i < data.length; i++) {
            if (data[i] % 2 != 0) {
                result.add(data[i]);
            }
        }

        return result.stream()
            .mapToInt(v -> v)
            .toArray();
    }

    @Test
    public void test() {
        Assert.assertEquals(
            sortArrayByParity(new int[] {8, 7, 6, 5, 4, 3, 2, 1}),
            new int[] {8, 6, 4, 2, 7, 5, 3, 1}
        );
    }
}
