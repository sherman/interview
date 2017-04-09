package org.sherman.interview.graph;

import com.google.common.collect.ImmutableSet;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

/**
 * @author Denis Gabaydulin
 * @since 09.04.17
 */
public class UnDirectedGraph {
    private final Map<Vertex, Set<Vertex>> graph = new HashMap<>();

    public void addEdge(@NotNull Vertex from, @NotNull Vertex to) {
        Set<Vertex> vertices = Optional.ofNullable(graph.get(from))
                .orElse(new HashSet<>());

        vertices.add(to);

        graph.put(from, vertices);
    }

    @NotNull
    public Set<Vertex> getListOfNeighbours(@NotNull Vertex vertex) {
        return Optional.ofNullable(graph.get(vertex))
                .orElse(ImmutableSet.of());
    }

    @NotNull
    public Set<Vertex> getAllVertices() {
        return new ImmutableSet.Builder<Vertex>()
                .addAll(graph.keySet())
                .addAll(graph.values().stream().flatMap(Set::stream).collect(toSet()))
                .build();
    }
}
