package com.leetcode.assorted_2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OnesAndZeroes {

    public int findMaxForm(String[] strs, int m, int n) {
        var table = new int[m + 1][n + 1];

        for (var k = 0; k < strs.length; k++) {
            int zeroes = (int) strs[k].chars().filter(c -> ((char) c) == '0').count();
            int ones = (int) strs[k].chars().filter(c -> ((char) c) == '1').count();

            var x = 1;

            for (int i = m; i >= zeroes; i--) {
                for (int j = n; j >= ones; j--) {
                    // reduce a number of available "resources":
                    // (i - zeroes)
                    // (j - ones)
                    table[i][j] = Math.max(table[i][j], table[i - zeroes][j - ones] + 1);
                }
            }
        }

        var result = 0;
        for (int i = m; i >= 0; i--) {
            for (int j = n; j >= 0; j--) {
                result = Math.max(table[i][j], result);
            }
        }

        return result;
    }

    @Test
    public void test() {
        Assertions.assertEquals(4, findMaxForm(new String[] {"10","0001","111001","1","0"}, 5, 3));
    }
}
