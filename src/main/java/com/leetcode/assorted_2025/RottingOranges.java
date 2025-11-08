package com.leetcode.assorted_2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RottingOranges {

    public static int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int max = -1;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    var level = recursiveRotting(grid, i, j, 1);
                    return Math.max(max, level) - 1;
                }
            }
        }

        return max;
    }

    private static int recursiveRotting(int[][] grid, int row, int col, int level) {
        if (grid[row][col] == 0 || (grid[row][col] == 2 && level != 1)) {
            return level - 1;
        }

        grid[row][col] = 2;

        var max = level;
        if (exists(grid, row - 1, col)) {
            var l1 = recursiveRotting(grid, row - 1, col, level + 1);
            max = Math.max(max, l1);
        }
        if (exists(grid, row, col - 1)) {
            var l2 = recursiveRotting(grid, row, col - 1, level + 1);
            max = Math.max(max, l2);
        }
        if (exists(grid, row, col + 1)) {
            var l3 = recursiveRotting(grid, row, col + 1, level + 1);
            max = Math.max(max, l3);
        }
        if (exists(grid, row + 1, col)) {
            var l4 = recursiveRotting(grid, row + 1, col, level + 1);
            max = Math.max(max, l4);
        }

        return max;
    }

    private static boolean exists(int[][] grid, int row, int col) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) {
            return false;
        } else {
            return true;
        }
    }

    @Test
    public void test() {
        //Assertions.assertEquals(4, RottingOranges.orangesRotting(new int[][] {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}}));
        Assertions.assertEquals(-1, RottingOranges.orangesRotting(new int[][] {{2, 1, 1}, {0, 1, 1}, {1, 0, 1}}));
    }
}
