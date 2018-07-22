package org.sherman.interview.graph;

import org.jetbrains.annotations.NotNull;

import java.util.*;

import static java.util.stream.Collectors.toSet;

/**
 * @author Denis Gabaydulin
 * @since 18/01/2016
 */
public class DirectedGraph {
    private static final int DEFAULT_WEIGHT = 42;

    private Set<Vertex> vertices = new HashSet<>();
    private Set<DirectedEdge> edges = new HashSet<>();
    private Map<Vertex, Integer> weights = new HashMap<>();
    private Map<Vertex, Integer> inDegrees = new HashMap<>();

    public void addEdge(@NotNull DirectedEdge edge) {
        if (edges.contains(edge)) {
            throw new IllegalArgumentException("Edge already in the graph!");
        }

        edges.add(edge);
        vertices.add(edge.getFrom());
        vertices.add(edge.getTo());
        weights.put(edge.getTo(), edge.getWeight());
        inDegrees.put(edge.getTo(), getInDegree(edge.getTo()) + 1);
    }

    @NotNull
    public Set<Vertex> getListOfNeighbours(@NotNull Vertex vertex) {
        return edges.stream()
            .filter(edge -> edge.getFrom().equals(vertex))
            .map(DirectedEdge::getTo)
            .collect(toSet());
    }

    @NotNull
    public Set<DirectedEdge> getListOfEdgeNeighbours(@NotNull Vertex vertex) {
        return edges.stream()
            .filter(edge -> edge.getFrom().equals(vertex))
            .collect(toSet());
    }

    public Set<Vertex> getVertices() {
        return vertices;
    }

    public void addEdge(@NotNull Vertex from, @NotNull Vertex to) {
        addEdge(new DirectedEdge(from, to, DEFAULT_WEIGHT));
    }

    public Integer getWeight(@NotNull Vertex vertex) {
        return Optional.ofNullable(weights.get(vertex)).orElse(0);
    }

    public Integer getInDegree(@NotNull Vertex vertex) {
        return Optional.ofNullable(inDegrees.get(vertex)).orElse(0);
    }

    public Map<Vertex, Integer> getInDegrees() {
        return inDegrees;
    }
}
