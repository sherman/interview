package com.leetcode.tbank;

import java.util.TreeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Given a list of users with some sessions. Each session is represented by start time and end time.
 * We need to find a maximum user sessions at the same time.
 * The start time strictly less than the end time.
 */
public class MaxUsersWatchingStream {
    private static final Logger logger = LoggerFactory.getLogger(MaxUsersWatchingStream.class);

    public int getMaxUsers(int[][] sessions) {
        var tree = new TreeMap<Integer, Integer>();

        for (var session : sessions) {
            var start = session[0];
            var end = session[1];
            var currentValue = tree.getOrDefault(start, 0);
            tree.put(start, currentValue + 1);
            currentValue = tree.getOrDefault(end, 0);
            tree.put(end, currentValue - 1);
        }

        var current = 0;
        var result = Integer.MIN_VALUE;
        for (var element : tree.keySet()) {
            current = current + tree.get(element);
            if (current > result) {
                result = current;
            }
        }

        return result;
    }

    @Test
    public void test() {
        Assert.assertEquals(1, getMaxUsers(new int[][] {{1, 2}}));
        Assert.assertEquals(2, getMaxUsers(new int[][] {{1, 2}, {1, 2}}));
        Assert.assertEquals(2, getMaxUsers(new int[][] {{1, 2}, {1, 2}, {4, 10}}));
        Assert.assertEquals(3, getMaxUsers(new int[][] {{1, 2}, {1, 2}, {4, 10}, {4, 7}, {5, 6}}));
        Assert.assertEquals(4, getMaxUsers(new int[][] {{1, 2}, {1, 2}, {4, 10}, {4, 7}, {5, 6}, {3, 7}}));
        Assert.assertEquals(1, getMaxUsers(new int[][] {{1, 100}, {105, 106}}));
    }
}
