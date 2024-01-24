package com.leetcode.assorted_2024;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

public class NumberOfProvinces {
    public int findCircleNum(int[][] grid) {
        // build graph
        var graph = new Graph();
        for (var i = 0; i < grid.length; i++) {
            for (var j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    graph.addConnection(i, j);
                }
            }
        }

        var visited = new HashSet<Integer>();
        var queue = new LinkedList<Integer>();
        var provinces = 0;
        for (var v : graph.getAll()) {
            if (!visited.contains(v)) {
                provinces++;
                // start new province
                queue.offer(v);
                visited.add(v);
                while (!queue.isEmpty()) {
                    var vv = queue.poll();
                    visited.add(vv);
                    for (var vvv : graph.getNeighbours(vv)) {
                        if (!visited.contains(vvv)) {
                            queue.offer(vvv);
                        }
                    }
                }
            }
        }
        return provinces;
    }

    private static class Graph {
        private final Map<Integer, Set<Integer>> adjList = new HashMap<>();

        void addConnection(int from, int to) {
            var fromConnections = adjList.computeIfAbsent(from, ignored -> new HashSet<>());
            fromConnections.add(to);
            var toConnections = adjList.computeIfAbsent(to, ignored -> new HashSet<>());
            toConnections.add(from);
        }

        public Set<Integer> getNeighbours(int v) {
            return adjList.get(v);
        }

        public Set<Integer> getAll() {
            return adjList.keySet();
        }
    }

    @Test
    public void cases() {
        Assert.assertEquals(findCircleNum(new int[][]{new int[]{1}}), 1);
        Assert.assertEquals(findCircleNum(new int[][]{new int[]{1, 1, 0}, new int[]{1, 1, 0}, new int[]{0, 0, 1}}), 2);
}
}
