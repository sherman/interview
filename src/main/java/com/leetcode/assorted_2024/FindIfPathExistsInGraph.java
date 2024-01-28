package com.leetcode.assorted_2024;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class FindIfPathExistsInGraph {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        if (n == 1 && source == destination) {
            return true;
        }

        var adjList = new HashMap<Integer, Set<Integer>>();
        var visited = new HashMap<Integer, Boolean>();

        for (var i = 0; i < edges.length; i++) {
            var from = edges[i][0];
            var to = edges[i][1];
            var fromConnections = adjList.computeIfAbsent(from, ignored -> new HashSet<>());
            fromConnections.add(to);
            var toConnections = adjList.computeIfAbsent(to, ignored -> new HashSet<>());
            toConnections.add(from);
            visited.put(to, false);
            visited.put(from, false);
        }

        if (!adjList.containsKey(source)) {
            return false;
        }

        var queue = new LinkedList<Integer>();
        queue.offer(source);
        visited.put(source, true);

        while (!queue.isEmpty()) {
            var v = queue.poll();
            var connections = adjList.get(v);
            for (var connection : connections) {
                if (connection == destination) {
                    return true;
                }

                if (!visited.get(connection)) {
                    queue.offer(connection);
                    visited.put(connection, true);
                }
            }
        }

        return false;
    }

    @Test
    public void test() {
        Assert.assertEquals(validPath(1, new int[][]{}, 0, 0), true);
        Assert.assertEquals(validPath(3, new int[][]{{0, 1}, {1, 2}, {2, 0}}, 0, 2), true);
    }
}
