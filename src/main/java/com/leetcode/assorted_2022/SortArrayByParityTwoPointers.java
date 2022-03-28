package com.leetcode.assorted_2022;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SortArrayByParityTwoPointers {
    private final Logger logger = LoggerFactory.getLogger(SortArrayByParityTwoPointers.class);

    public int[] sortArrayByParity(int[] data) {
        int i = 0;
        int j = data.length - 1;

        //8, 7, 6, 5, 4, 3, 2, 1

        while(i < j) {
            if (data[i] % 2 == 0) {
                i++;
            }

            if (data[j] % 2 != 0) {
                j--;
            }

            if (data[i] % 2 != 0 && data[j] % 2 == 0) {
                var temp = data[i];
                data[i] = data[j];
                data[j] = temp;
                i++;
                j--;
            }
        }

        logger.info("[{}]", data);

        return data;
    }

    @Test
    public void test() {
        Assert.assertEquals(
            sortArrayByParity(new int[] {8, 7, 6, 5, 4, 3, 2, 1}),
            new int[] {8, 2, 6, 4, 5, 3, 7, 1}
        );

        Assert.assertEquals(
            sortArrayByParity(new int[] {1, 3}),
            new int[] {1, 3}
        );

        Assert.assertEquals(
            sortArrayByParity(new int[] {2, 4}),
            new int[] {2, 4}
        );

        Assert.assertEquals(
            sortArrayByParity(new int[] {1, 2, 4}),
            new int[] {4, 2, 1}
        );

        Assert.assertEquals(
            sortArrayByParity(new int[] {3, 2, 4, 1}),
            new int[] {4, 2, 3, 1}
        );
    }
}
