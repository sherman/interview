package io.algoexpert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SweetAndSavory {
    private static final Logger logger = LoggerFactory.getLogger(SweetAndSavory.class);

    public int[] sweetAndSavory(int[] dishes, int target) {
        var sweet = new ArrayList<Integer>();
        var savory = new ArrayList<Integer>();

        for (var i = 0; i < dishes.length; i++) {
            if (dishes[i] < 0) {
                sweet.add(dishes[i]);
            } else {
                savory.add(dishes[i]);
            }
        }

        // must have at least one sweet and one savory dish
        if (sweet.isEmpty() || savory.isEmpty()) {
            return new int[]{0, 0};
        }

        Collections.sort(savory);
        Collections.sort(sweet, Comparator.comparingInt(Math::abs));

        var i = 0;
        var j = 0;
        var pair = new int[2];
        var bestDiff = Integer.MAX_VALUE;
        while (i < sweet.size() && j < savory.size()) {
            var candidate = sweet.get(i) + savory.get(j);
            if (candidate <= target) {
                var diff = target - candidate;
                if (diff < bestDiff) {
                    bestDiff = diff;
                    pair[0] = sweet.get(i);
                    pair[1] = savory.get(j);
                }
                j++;
            } else {
                i++;
            }
        }

        return pair;

        //
        // -5 -3
        //  1  7
        //-20
        // 0
        //  -45 -27 -24 -18  -15  8   80
        //
        //  -25 -7   -4   2    5  12  100
        //
        //  -20
        // -3, -5, 1, 7
        //
        //
    }

    @Test
    public void test() {
        Assert.assertEquals(sweetAndSavory(new int[]{-3, -5, 1, 7}, 8), new int[]{-3, 7});
        Assert.assertEquals(sweetAndSavory(new int[]{2, 5, -4, -7, 12, 100, -25}, -20), new int[]{-25, 5});
        Assert.assertEquals(sweetAndSavory(new int[]{3, 5, 7, 2, 6, 8, 1}, 10), new int[]{0, 0});
    }
}
