package com.leetcode.uber;

import java.util.HashSet;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NumberOfUniqueIslands {
    public int numIslands(char[][] grid) {
        var islandPaths = new HashSet<String>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    var builder = new StringBuilder();
                    traverseBfs(grid, i, j, builder, 'I');
                    islandPaths.add(builder.toString());
                }
            }
        }

        return islandPaths.size();
    }

    private void traverseBfs(char[][] grid, int i, int j, StringBuilder pathBuilder, Character direction) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] == '0') {
            return;
        }

        pathBuilder.append(direction);

        grid[i][j] = '0';
        traverseBfs(grid, i - 1, j, pathBuilder, 'L');
        traverseBfs(grid, i, j - 1, pathBuilder, 'U');
        traverseBfs(grid, i, j + 1, pathBuilder, 'D');
        traverseBfs(grid, i + 1, j, pathBuilder, 'R');
    }

    @Test
    public void test() {
        Assert.assertEquals(
            numIslands(
                new char[][] {
                    new char[] {'1', '1', '0', '1', '0'},
                    new char[] {'1', '1', '0', '1', '0'},
                    new char[] {'1', '1', '0', '0', '1'},
                    new char[] {'0', '0', '1', '0', '1'},
                    new char[] {'0', '1', '0', '0', '0'}
                }
            ),
            3
        );
    }
}
