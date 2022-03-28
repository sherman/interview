package com.leetcode.assorted_2022;

import org.testng.Assert;
import org.testng.annotations.Test;

public class NumberOfConnectedCities {
    private static void dfs(int[][] roads, int i, int j) {
        if (i < 0 || j < 0 || i >= roads.length || j >= roads[i].length || roads[i][j] == 0) {
            return;
        }

        roads[i][j] = 0;

        dfs(roads, i - 1, j);
        dfs(roads, i + 1, j);
        dfs(roads, i, j - 1);
        dfs(roads, i, j + 1);
    }

    public int getNumberOfConnectedCities(int[][] roads) {
        int connectedCities = 0;
        for (var i = 0; i < roads.length; i++) {
            for (var j = 0; j < roads[i].length; j++) {
                if (roads[i][j] == 1) {
                    connectedCities++;
                    dfs(roads, i, j);
                }
            }
        }
        return connectedCities;
    }

    @Test
    public void test() {
        Assert.assertEquals(
            getNumberOfConnectedCities(new int[][] {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}),
            2
        );
    }
}
