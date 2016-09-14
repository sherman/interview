package org.sherman.interview.graph;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.Optional.ofNullable;

/**
 * @author Denis Gabaydulin
 * @since 18/01/2016
 */
public class DirectedGraphAlgorithms {
    private static final Logger log = LoggerFactory.getLogger(DirectedGraphAlgorithms.class);

    private DirectedGraphAlgorithms() {
    }

    public static List<Vertex> naiveDijkstra(@NotNull DirectedGraph graph, @NotNull Vertex from, @NotNull Vertex to) {
        Map<Vertex, Integer> vertexDistances = new HashMap<>();

        Set<Vertex> visited = new HashSet<>();
        Set<Vertex> unvisited = new HashSet<>();

        Stack<DirectedEdge> shortPaths = new Stack<>();

        unvisited.add(from);

        while (!unvisited.isEmpty()) {
            Vertex min = getMinimum(vertexDistances, unvisited);

            visited.add(min);
            unvisited.remove(min);

            graph.getListOfEdgeNeighbours(min).stream()
                    .filter(edge -> !visited.contains(edge.getTo()))
                    .forEach(
                            edge -> {
                                if (
                                        getShortestDistance(vertexDistances, edge.getTo()) >
                                                getShortestDistance(vertexDistances, edge.getFrom()) + edge.getWeight()
                                ) {
                                    vertexDistances.put(edge.getTo(), getShortestDistance(vertexDistances, edge.getFrom()) + edge.getWeight());
                                    unvisited.add(edge.getTo());
                                    shortPaths.add(edge);
                                }
                            }
                    );

            if (visited.contains(to)) {
                unvisited.clear();
            }
        }

        shortPaths.forEach(e -> log.info("e {}", e.toString()));

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

        log.info("================================");

        if (shortPath.size() >= 2 && current.equals(from) && shortPath.get(0).equals(to)) {
            Collections.reverse(shortPath);
            return shortPath;
        } else {
            return null;
        }
    }

    private static Vertex getMinimum(Map<Vertex, Integer> vertexDistances, Set<Vertex> unvisited) {
        return unvisited.stream()
                .map(v -> new AbstractMap.SimpleEntry<>(v, getShortestDistance(vertexDistances, v)))
                .min(comparingInt(AbstractMap.SimpleEntry::getValue))
                .map(AbstractMap.SimpleEntry::getKey)
                .get();
    }

    private static int getShortestDistance(Map<Vertex, Integer> vertexDistances, Vertex vertex) {
        return ofNullable(vertexDistances.get(vertex)).orElse(Integer.MAX_VALUE);
    }
}