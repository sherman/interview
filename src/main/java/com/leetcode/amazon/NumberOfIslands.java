package com.leetcode.amazon;

import org.testng.Assert;
import org.testng.annotations.Test;

public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        int islands = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    islands++;
                    traverseBfs(grid, i, j);
                }
            }
        }

        return islands;
    }

    private void traverseBfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0';
        traverseBfs(grid, i - 1, j);
        traverseBfs(grid, i, j - 1);
        traverseBfs(grid, i, j + 1);
        traverseBfs(grid, i + 1, j);
    }

    @Test
    public void test() {
        Assert.assertEquals(
                numIslands(
                        new char[][]{
                                new char[]{'1', '1', '1', '1', '0'},
                                new char[]{'1', '1', '0', '1', '0'},
                                new char[]{'1', '1', '0', '0', '0'},
                                new char[]{'0', '0', '0', '0', '0'}
                        }
                ),
                1
        );
    }
}
