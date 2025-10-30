package com.leetcode.assorted_2025;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MaximizeDistanceToClosestPerson {
    public int maxDistToClosest(int[] seats) {
        var current = 0;
        var bestDist = 0;
        var startedHandled = false;

        for (var i = 0; i < seats.length; i++) {
            var state = seats[i];
            if (state == 1) {
                if (current > 0) {
                    var candidate = 0;
                    if (!startedHandled && seats[0] == 0) {
                        candidate = current;
                        startedHandled = true;
                    } else {
                        candidate = current % 2 == 0 ? current / 2 : (current / 2) + 1;
                    }
                    if (candidate > bestDist) {
                        bestDist = candidate;
                    }
                    current = 0;
                }
            } else {
                current++;
            }
        }

        if (current > 0) {
            var candidate = 0;
            if (seats[seats.length - 1] == 0) {
                candidate = current;
            } else {
                candidate = current % 2 == 0 ? current / 2 : (current / 2) + 1;
            }
            if (candidate > bestDist) {
                bestDist = candidate;
            }
        }
        return bestDist;
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
