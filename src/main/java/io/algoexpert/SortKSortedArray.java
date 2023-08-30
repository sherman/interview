package io.algoexpert;

import org.testng.annotations.Test;
import org.testng.internal.junit.ArrayAsserts;

import java.util.PriorityQueue;

public class SortKSortedArray {
    public int[] sortKSortedArray(int[] array, int k) {
        var heap = new PriorityQueue<Integer>();

        var result = new int[array.length];
        var j = 0;
        for (var i = 0; i < array.length; i++) {
            if (heap.size() < k + 1) {
                heap.offer(array[i]);
            } else {
                var element = heap.poll();
                result[j] = element;
                j++;
                heap.offer(array[i]);
            }
        }

        while (!heap.isEmpty()) {
            var element = heap.poll();
            result[j] = element;
            j++;
        }

        return result;
    }

    @Test
    public void test() {
        ArrayAsserts.assertArrayEquals(
            sortKSortedArray(new int[]{3, 2, 1, 5, 4, 7, 6, 5}, 3),
            new int[]{1, 2, 3, 4, 5, 5, 6, 7}
        );

        ArrayAsserts.assertArrayEquals(
            sortKSortedArray(new int[]{-1, -3, -4, 2, 1, 3}, 2),
            new int[]{-4, -3, -1, 1, 2, 3}
        );


    }
}
