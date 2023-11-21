package com.leetcode.assorted_2023;

import org.testng.annotations.Test;

public class FloodFill {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        var visited = new int[image.length][image[0].length];
        var startPointColor = image[sr][sc];
        floodFill(image, visited, sr, sc, startPointColor, color);
        return image;
    }

    public void floodFill(int[][] image, int[][] visited, int i, int j, int expectedColor, int color) {
        if (i < 0 || j < 0 || i >= image.length || j >= image[i].length) {
            return;
        }

        if (visited[i][j] == 1) {
            return;
        }

        visited[i][j] = 1;

        if (image[i][j] == expectedColor) {
            image[i][j] = color;
            floodFill(image, visited, i - 1, j, expectedColor, color);
            floodFill(image, visited, i + 1, j, expectedColor, color);
            floodFill(image, visited, i, j - 1, expectedColor, color);
            floodFill(image, visited, i, j + 1, expectedColor, color);
        }
    }

    @Test
    public void test() {
        floodFill(new int[][]{new int[]{1, 1, 1}, new int[]{1, 1, 0}, new int[]{1, 0, 1}}, 1, 1, 2);
    }
}
