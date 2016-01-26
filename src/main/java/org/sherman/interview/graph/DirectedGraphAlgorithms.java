package org.sherman.interview.graph;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Denis Gabaydulin
 * @since 18/01/2016
 */
public class DirectedGraphAlgorithms {
    private static final Logger log = LoggerFactory.getLogger(DirectedGraphAlgorithms.class);

    private DirectedGraphAlgorithms() {
    }

    public static List<Vertex> naiveDijkstra(@NotNull DirectedGraph graph, @NotNull Vertex from, @NotNull Vertex to) {
        Set<Vertex> vertices = graph.getVertices();

        Map<Vertex, Integer> vertexDistances = vertices.stream()
                .collect(Collectors.toMap(Function.identity(), vertex -> 0));

        Set<Vertex> visited = new HashSet<>();
        Queue<Vertex> unvisited = new LinkedList<>();

        Stack<DirectedEdge> shortPaths = new Stack<>();

        unvisited.add(from);

        while (!unvisited.isEmpty()) {
            Optional.of(unvisited.poll()).ifPresent(
                    v -> {
                        visited.add(v);

                        graph.getListOfEdgeNeighbours(v).stream()
                                .filter(edge -> !visited.contains(edge.getTo()))
                                .forEach(
                                        edge -> {
                                            int fromWeight = Math.max(0, vertexDistances.get(edge.getFrom()));
                                            int currentWeight = Math.max(0, vertexDistances.get(edge.getTo()));
                                            int toWeight = edge.getWeight();

                                            if (getDistance(fromWeight + toWeight, currentWeight) < currentWeight || currentWeight == 0) {
                                                vertexDistances.put(edge.getTo(), fromWeight + toWeight);
                                                shortPaths.add(edge);
                                            }

                                            unvisited.add(edge.getTo());

                                            if (visited.contains(from) && visited.contains(to)) {
                                                unvisited.clear();
                                            }
                                        }
                                );
                    }
            );
        }

        shortPaths.forEach(e -> log.info("{}", e.toString()));

        List<Vertex> shortPath = new ArrayList<>();

        Vertex current = to;
        while (!shortPaths.isEmpty()) {
            DirectedEdge edge = shortPaths.pop();
            if (edge.getTo().equals(current)) {
                shortPath.add(current);
                current = edge.getFrom();
            }
        }
        shortPath.add(current);

        if (shortPath.size() >= 2 && current.equals(from) && shortPath.get(0).equals(to)) {
            Collections.reverse(shortPath);
            return shortPath;
        } else {
            return null;
        }
    }

    private static int getDistance(int newWeight, int currentWeight) {
        if (currentWeight == 0) {
            return newWeight;
        }

        return Math.min(newWeight, currentWeight);
    }
}
