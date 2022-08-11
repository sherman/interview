package com.leetcode.assorted_2022;

import java.util.Arrays;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }

        var cache = new int[grid.length][grid[0].length];
        for (int i = 0; i < cache.length; i++) {
            Arrays.fill(cache[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i == 0 && j == 0) {
                    cache[0][0] = grid[0][0];
                    // first row
                }

                if (i == 0) {
                    if (j > 0) {
                        cache[i][j] = cache[i][j - 1] + grid[i][j];
                    }
                } else {
                    if (j == 0) {
                        cache[i][j] = cache[i - 1][j] + grid[i][j];
                    } else {
                        cache[i][j] = Math.min(cache[i - 1][j], cache[i][j - 1]) + grid[i][j];
                    }
                }
            }
        }

        return cache[cache.length - 1][cache[0].length - 1];
    }

    @Test
    public void test() {
        Assert.assertEquals(minPathSum(new int[][] {}), 0);
        Assert.assertEquals(minPathSum(new int[][] {new int[] {1, 3, 1}, new int[] {1, 5, 1}, new int[] {4, 2, 1}}), 7);
    }

}
