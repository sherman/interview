package org.sherman.interview.graph;

import org.jetbrains.annotations.NotNull;

import java.util.*;

import static java.util.stream.Collectors.toSet;

/**
 * @author Denis Gabaydulin
 * @since 18/01/2016
 */
public class DirectedGraph {
    private Set<Vertex> vertices = new HashSet<>();
    private Set<DirectedEdge> edges = new HashSet<>();

    public void addEdge(@NotNull DirectedEdge edge) {
        if (edges.contains(edge)) {
            throw new IllegalArgumentException("Edge already in the graph!");
        }

        edges.add(edge);
        vertices.add(edge.getFrom());
        vertices.add(edge.getTo());
    }

    @NotNull
    public Set<Vertex> getListOfNeighbours(@NotNull Vertex vertex) {
        return edges.stream()
                .filter(edge -> edge.getFrom().equals(vertex))
                .map(DirectedEdge::getTo)
                .collect(toSet());
    }
}
