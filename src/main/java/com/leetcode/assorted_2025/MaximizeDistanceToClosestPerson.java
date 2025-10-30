package com.leetcode.assorted_2025;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MaximizeDistanceToClosestPerson {
    public int maxDistToClosest(int[] seats) {
        var maxDist = 0;
        var lastOccupied = -1;

        for (var i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                if (lastOccupied == -1) {
                    // leading empty seats
                    maxDist = i;
                } else {
                    maxDist = Math.max(maxDist, (i - lastOccupied)  /2);
                }
                lastOccupied = i;
            }
        }

        // trailing empty seats
        if (seats[seats.length - 1] == 0) {
            maxDist = Math.max(maxDist, seats.length - 1 - lastOccupied);
        }
        return maxDist;
    }

    @Test
    public void test() {
        Assert.assertEquals(maxDistToClosest(new int[] {0, 1, 0, 0, 0, 1, 1, 0, 1, 1}), 2);
        Assert.assertEquals(maxDistToClosest(new int[] {0, 1}), 1);
        Assert.assertEquals(maxDistToClosest(new int[] {0, 0, 1}), 2);
        Assert.assertEquals(maxDistToClosest(new int[] {1, 0, 0, 0}), 3);
        Assert.assertEquals(maxDistToClosest(new int[] {1, 0, 0}), 2);
        Assert.assertEquals(maxDistToClosest(new int[] {1, 0, 0, 0, 1, 0, 1}), 2);
    }
}
