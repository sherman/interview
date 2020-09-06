package org.sherman.interview.amazon;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static java.util.stream.Collectors.toSet;

public class LargestItemAssociation {
    private static final Logger logger = LoggerFactory.getLogger(LargestItemAssociation.class);

    public Set<String> largestItemAssociation(List<PairString> pairs) {
        UndirectedGraph graph = new UndirectedGraph();
        for (PairString edge : pairs) {
            graph.addEdge(edge.first, edge.second);
        }
        return findLargestConnectedComponent(graph);
    }

    @Test
    public void largestItemAssociation() {
        Assert.assertEquals(
                ImmutableSet.of("item3", "item4", "item5"),
                largestItemAssociation(
                        ImmutableList.of(
                                new PairString("item1", "item2"),
                                new PairString("item3", "item4"),
                                new PairString("item4", "item5")
                        )
                )
        );

        Assert.assertEquals(
                ImmutableSet.of("item2", "item1", "item4", "item3", "item5"),
                largestItemAssociation(
                        ImmutableList.of(
                                new PairString("item1", "item2"),
                                new PairString("item3", "item4"),
                                new PairString("item4", "item5"),
                                new PairString("item5", "item2")
                        )
                )
        );
    }

    public Set<String> findLargestConnectedComponent(UndirectedGraph graph) {
        Set<String> largest = new HashSet<>();
        Set<String> components = new HashSet<>();
        Map<String, Boolean> visited = new HashMap<>();
        for (String v : graph.getAllVertices()) {
            if (!visited.containsKey(v)) {
                components.clear();
                dfs(graph, v, visited, components);
                if (components.size() > largest.size()) {
                    largest = components;
                }
                //logger.info("==========");
            }
        }

        return largest;
    }

    private void dfs(UndirectedGraph graph, String v, Map<String, Boolean> visited, Set<String> components) {
        //logger.info("[{}]", v);
        visited.put(v, true);
        components.add(v);
        for (String neighbour : graph.getNeighbours(v)) {
            if (!visited.containsKey(neighbour)) {
                dfs(graph, neighbour, visited, components);
            }
        }
    }

    private static class PairString {
        String first;
        String second;

        public PairString(String first, String second) {
            this.first = first;
            this.second = second;
        }
    }

    private static class UndirectedGraph {
        private Map<String, Set<String>> edges = new HashMap<>();

        public void addEdge(String v1, String v2) {
            Set<String> vertices = edges.computeIfAbsent(v1, ignored -> new HashSet<>());
            vertices.add(v2);
            vertices = edges.computeIfAbsent(v2, ignored -> new HashSet<>());
            vertices.add(v1);
        }

        public Set<String> getNeighbours(String v) {
            return Optional.ofNullable(edges.get(v)).orElse(ImmutableSet.of());
        }

        @NotNull
        public Set<String> getAllVertices() {
            return new ImmutableSet.Builder<String>()
                    .addAll(edges.keySet())
                    .addAll(edges.values().stream().flatMap(Set::stream).collect(toSet()))
                    .build();
        }
    }
}
