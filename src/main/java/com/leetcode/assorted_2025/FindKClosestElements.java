package com.leetcode.assorted_2025;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FindKClosestElements {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        var result = new ArrayList<Integer>();
        var heap = new PriorityQueue<Integer>();

        for (int value : arr) {
            if (k > 0) {
                heap.offer(value);
                k--;
            } else if (Math.abs(heap.peek() - x) > Math.abs(value - x)) {
                heap.poll();
                heap.offer(value);
            }
        }


        while (!heap.isEmpty()) {
            result.add(heap.poll());
        }
        //Collections.sort(result);
        return result;
    }

    @Test
    public void test() {
        Assert.assertEquals(
            findClosestElements(new int[] {0, 0, 1, 2, 3, 3, 4, 7, 7, 8}, 3, 5),
            List.of(3, 3, 4)
        );

        Assert.assertEquals(
            findClosestElements(new int[] {1, 1, 1, 10, 10, 10}, 1, 9),
            List.of(10)
        );

        Assert.assertEquals(
            findClosestElements(new int[] {1, 1, 2, 3, 4, 5}, 4, -1),
            List.of(1, 1, 2, 3)
        );

        Assert.assertEquals(
            findClosestElements(new int[] {1, 2, 3, 4, 5}, 4, 3),
            List.of(1, 2, 3, 4)
        );
    }
}
