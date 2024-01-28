package com.leetcode.assorted_2024;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;
import java.util.function.Function;

public class FindCenterOfStarGraph {
    public int findCenter(int[][] edges) {
        var adjList = new HashMap<Integer, Set<Integer>>();

        for (var i = 0; i < edges.length; i++) {
            var from = edges[i][0];
            var to = edges[i][1];
            var fromConnections = adjList.computeIfAbsent(from, ignored -> new HashSet<>());
            fromConnections.add(to);
            var toConnections = adjList.computeIfAbsent(to, ignored -> new HashSet<>());
            toConnections.add(from);
        }

        return adjList.entrySet().stream()
            .map(entry -> Map.entry(entry.getKey(), entry.getValue().stream().mapToInt(v -> v).count()))
            .sorted(Comparator.comparing((Function<Map.Entry<Integer, Long>, Long>) Map.Entry::getValue).reversed())
            .findFirst()
            .map(Map.Entry::getKey)
            .get();
    }

    @Test
    public void test() {
        Assert.assertEquals(findCenter(new int[][]{{1, 2}, {2, 3}, {4, 2}}), 2);
    }
}
