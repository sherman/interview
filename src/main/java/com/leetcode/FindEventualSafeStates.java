package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FindEventualSafeStates {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        //Map<V, Boolean> visited = new HashMap<>();
        Graph g = new Graph();
        for (int i = 0; i < graph.length; i++) {
            V v = new V(i);
            g.add(
                    v,
                    Arrays.stream(graph[i])
                            .mapToObj(V::new)
                            .collect(Collectors.toList())
            );
        }

        List<Integer> res = new ArrayList<>();

        Map<V, Color> visited = g.getAll().stream()
                .collect(Collectors.toMap(v -> v, ignored -> Color.WHITE));

        for (V vertex : g.getAll()) {
            if (!isCycle(vertex, g, visited)) {
                res.add(vertex.value);
            }
        }

        return res;
    }

    private boolean isCycle(V v, Graph graph, Map<V, Color> vv) {
        vv.put(v, Color.GREY);

        List<V> neighbours = graph.getNeighbours(v);
        for (V neighbour : neighbours) {
            if (vv.get(neighbour) != Color.BLACK) {
                if (vv.get(neighbour) == Color.GREY) {
                    return true;
                }

                if (isCycle(neighbour, graph, vv)) {
                    return true;
                }
            }
        }

        vv.put(v, Color.BLACK);

        return false;
    }

    private static class Graph {
        private Map<V, List<V>> edges = new HashMap<>();

        public void add(V v, List<V> nodes) {
            List<V> neighbours = edges.computeIfAbsent(v, ignored -> new ArrayList<>());
            nodes.forEach(neighbours::add);
        }

        public List<V> getNeighbours(V v) {
            return Optional.ofNullable(edges.get(v)).orElse(new ArrayList<>());
        }

        public List<V> getAll() {
            return new ArrayList<>(edges.keySet());
        }
    }

    private static class V {
        final int value;

        public V(int value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            V v = (V) o;
            return value == v.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    private enum Color {
        WHITE,
        GREY,
        BLACK
    }

    @Test
    public void test() {
        Assert.assertEquals(
                eventualSafeNodes(new int[][]{new int[]{1, 2}, new int[]{2, 3}, new int[]{5}, new int[]{0}, new int[]{5}, new int[]{}, new int[]{}}),
                ImmutableList.of(2, 4, 5, 6)
        );
    }
}
