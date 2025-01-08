package com.leetcode.assorted_2025;

import java.util.Arrays;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckIfNAndItsDoubleExist {
    public boolean checkIfExist(int[] arr) {
        // 0 * 2 == 0
        if (Arrays.stream(arr).filter(e -> e == 0).count() > 1) {
            return true;
        }

        var values = new HashMap<Integer, Integer>();
        for (var i = 0; i < arr.length; i++) {
            values.put(arr[i], i);
        }

        for (var element : values.keySet()) {
            var index = values.get(element * 2);
            if (index != null && !index.equals(values.get(element))) {
                return true;
            }
        }

        return false;
    }

    @Test
    public void test() {
        Assert.assertTrue(checkIfExist(new int[] {0, 0}));
        Assert.assertFalse(checkIfExist(new int[] {3, 1, 7, 11}));
        Assert.assertTrue(checkIfExist(new int[] {10, 2, 5, 3}));
    }

}
