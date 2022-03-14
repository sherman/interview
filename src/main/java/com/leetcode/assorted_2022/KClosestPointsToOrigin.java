package com.leetcode.assorted_2022;

import java.util.ArrayList;
import java.util.PriorityQueue;
import org.testng.Assert;
import org.testng.annotations.Test;

public class KClosestPointsToOrigin {
    public int[][] kClosest(int[][] points, int k) {
        var heap = new PriorityQueue<Point>((o1, o2) -> {
            var v1 = Math.sqrt(Math.pow(o1.x, 2) + Math.pow(o1.y, 2));
            var v2 = Math.sqrt(Math.pow(o2.x, 2) + Math.pow(o2.y, 2));
            return Double.compare(v1, v2);
        });

        for (var point : points) {
            heap.offer(new Point(point[0], point[1]));
        }

        var result = new ArrayList<int[]>();
        while (k > 0) {
            var element = heap.poll();
            result.add(new int[] {element.x, element.y});
            k--;
        }

        return result.toArray(int[][]::new);
    }

    @Test
    public void test() {
        Assert.assertEquals(kClosest(new int[][] {new int[] {1, 3}, new int[] {-2, 2}}, 1), new int[][] {new int[] {-2, 2}});
        Assert.assertEquals(
            kClosest(new int[][] {new int[] {3, 3}, new int[] {5, -1}, new int[] {-2, 4}}, 2),
            new int[][] {new int[] {3, 3}, new int[] {-2, 4}}
        );
    }

    private record Point(int x, int y) {
    }
}
