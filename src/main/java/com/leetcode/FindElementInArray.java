package com.leetcode;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class FindElementInArray {

    @Test(dataProvider = "cases")
    public void findElement(int[] data, int target, int expected) {
        assertEquals(findElement(data, target), expected);
    }

    private int findElement(int[] data, int target) {
        return findElement(data, 0, data.length - 1, target);
    }

    private int findElement(int[] data, int left, int right, int target) {
        if (left <= right) {
          int mid = (left + right) / 2;

          int midElt = data[mid];

          if (midElt == target) {
              return mid;
          } else if (midElt > target) {
              return findElement(data, left, mid - 1, target);
          } else {
              return findElement(data, mid + 1, right, target);
          }
        } else {
            return left;
        }
    }

    @DataProvider
    private Object[][] cases() {
        return new Object[][]{
            {
                new int[]{1,3,5,6}, 5, 2
            },

            {
                new int[]{1,3,5,6}, 2, 1
            },

            {
                new int[]{1,3,5,6}, 7, 4
            },

            {
                new int[]{1,3,5,6}, 0, 0
            }
        };
    }
}
