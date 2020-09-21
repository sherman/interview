package com.leetcode.amazon;

import java.util.List;

import com.google.common.collect.ImmutableList;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BinaryMatrix {
    final int[][] data;

    public BinaryMatrix(int[][] data) {
        this.data = data;
    }

    public int get(int row, int col) {
        return data[row][col];
    }

    public List<Integer> dimensions() {
        return ImmutableList.of(data.length, data.length > 0 ? data[0].length : 0);
    }

    public int leftMostColumnWithOne(BinaryMatrix m) {
        int finalResult = Integer.MAX_VALUE;
        List<Integer> dims = m.dimensions();
        for (int i = 0; i < dims.get(0); i++) {
            int result = Integer.MAX_VALUE;
            result = binarySearch(m, i, 0, dims.get(1) - 1, result);
            finalResult = Math.min(finalResult, result);
            if (finalResult == 0) {
                break;
            }
        }

        if (finalResult == Integer.MAX_VALUE) {
            return -1;
        }

        return finalResult;
    }

    private int binarySearch(BinaryMatrix m, int row, int l, int r, int result) {
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int midElt = m.get(row, mid);
            if (midElt == 1) {
                result = Math.min(mid, result);
                return binarySearch(m, row, l, mid - 1, result);
            } else if (midElt == 0) {
                return binarySearch(m, row, mid + 1, r, result);
            } else {
                return -1;
            }
        }

        return result;
    }

    public int findLeftmostOne(int[] data) {
        int result = Integer.MAX_VALUE;
        return binarySearch(data, 0, data.length - 1, result);
    }

    public int binarySearch(int[] data, int l, int r, int result) {
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (data[mid] == 1) {
                result = Math.min(mid, result);
                return binarySearch(data, l, mid - 1, result);
            } else if (data[mid] == 0) {
                return binarySearch(data, mid + 1, r, result);
            } else {
                return -1;
            }
        }

        return result;
    }

    @Test
    public void test() {
        Assert.assertEquals(findLeftmostOne(new int[]{0, 0, 0, 0}), Integer.MAX_VALUE);
        Assert.assertEquals(findLeftmostOne(new int[]{0, 1, 0, 0}), 1);
        Assert.assertEquals(findLeftmostOne(new int[]{0, 0, 1, 0}), 2);
        Assert.assertEquals(findLeftmostOne(new int[]{0, 0}), Integer.MAX_VALUE);
        Assert.assertEquals(findLeftmostOne(new int[]{0, 1}), 1);
    }

    @Test
    public static void test2() {
        BinaryMatrix m = new BinaryMatrix(
                new int[][]{
                        new int[]{0, 1},
                        new int[]{0, 0}
                }
        );
        Assert.assertEquals(m.leftMostColumnWithOne(m), 1);
    }
}
