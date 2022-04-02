package org.sherman.interview.graph;

import static java.util.Comparator.comparingInt;
import static java.util.Optional.ofNullable;

import com.google.common.primitives.Ints;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.function.Supplier;
import joptsimple.internal.Strings;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Denis Gabaydulin
 * @since 18/01/2016
 */
public class DirectedGraphAlgorithms {
    private static final Logger log = LoggerFactory.getLogger(DirectedGraphAlgorithms.class);

    private DirectedGraphAlgorithms() {
    }

    public static List<Vertex> dijkstra(@NotNull DirectedGraph graph, @NotNull Vertex from, @NotNull Vertex to) {
        // fill all vertexes with infinite distances;
        var vertexDistances = new HashMap<Vertex, Integer>();
        for (var vertex : graph.getVertices()) {
            vertexDistances.put(vertex, Integer.MAX_VALUE);
        }
        vertexDistances.put(from, 0);

        var visited = new HashSet<Vertex>();
        var unvisited = new HashSet<Vertex>();
        // add all vertexes to unvisited
        unvisited.addAll(vertexDistances.keySet());

        Stack<DirectedEdge> shortPaths = new Stack<>();

        var priorityQueue = new PriorityQueue<WeightedVertex>();
        priorityQueue.add(new WeightedVertex(from, 0));

        while (!unvisited.isEmpty() && !priorityQueue.isEmpty()) {
            // get a vertex with a minimum weight
            var min = priorityQueue.poll();

            var fromVertex = vertexDistances.get(min.v());
            for (var edge : graph.getListOfEdgeNeighbours(min.v())) {
                if (!visited.contains(edge.getTo())) {
                    var newWeight = fromVertex + edge.getWeight();
                    var currentWeight = vertexDistances.get(edge.getTo());
                    // replace weight if a new path is smaller
                    if (newWeight < currentWeight) {
                        vertexDistances.put(edge.getTo(), newWeight);
                        priorityQueue.add(new WeightedVertex(edge.getTo(), newWeight));
                        shortPaths.add(edge);
                        //log.info("{} -> {}", min, edge.getTo());
                    }
                }
            }

            visited.add(min.v());
            unvisited.remove(min.v());
        }

        List<Vertex> shortPath = new ArrayList<>();

        var current = to;
        while (!shortPaths.isEmpty()) {
            var next = shortPaths.pop();
            if (next.getTo().equals(current)) {
                //log.info("[{}]", current);
                shortPath.add(current);
                current = next.getFrom();
            }
        }
        shortPath.add(current);

        Collections.reverse(shortPath);
        log.info("[{}]", shortPath);

        return shortPath;
    }

    public static List<Vertex> naiveDijkstra(@NotNull DirectedGraph graph, @NotNull Vertex from, @NotNull Vertex to) {
        Map<Vertex, Integer> vertexDistances = new HashMap<>();

        Set<Vertex> visited = new HashSet<>();
        Set<Vertex> unvisited = new HashSet<>();

        Stack<DirectedEdge> shortPaths = new Stack<>();

        unvisited.add(from);

        while (!unvisited.isEmpty()) {
            Vertex min = getMinimum(vertexDistances, unvisited);

            log.info("Min [{}] is [{}]", min, vertexDistances.get(min));

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

    public static int[] bfs(@NotNull DirectedGraph graph, @NotNull Vertex start) {
        Queue<Vertex> vertices = new LinkedList<>();
        return graphTraverse(graph, start, vertices::poll, vertices::offer, vertices);
    }

    public static int[] dfs(@NotNull DirectedGraph graph, @NotNull Vertex start) {
        Stack<Vertex> vertices = new Stack<>();
        return graphTraverse(graph, start, vertices::pop, vertices::push, vertices);
    }

    public static int getNumberOfRoutes(@NotNull DirectedGraph graph, @NotNull Vertex from, @NotNull Vertex to) {
        return getNumberOfRoutesInternal(graph, to, from);
    }

    private static int getNumberOfRoutesInternal(
        DirectedGraph graph,
        Vertex target,
        Vertex current
    ) {
        log.info("{}", current);

        if (current.equals(target)) {
            return 1;
        }

        int sum = 0;

        for (Vertex v : graph.getListOfNeighbours(current)) {
            sum += getNumberOfRoutesInternal(graph, target, v);
        }

        return sum;
    }

    public static int getNumberOfRoutesDp(DirectedGraph g, Vertex from, Vertex to) {
        Map<Vertex, Integer> cache = new HashMap<>();
        return getNumberOfRoutesInternalDp(g, from, to, cache);
    }

    private static int getNumberOfRoutesInternalDp(
        DirectedGraph graph,
        Vertex target,
        Vertex current,
        Map<Vertex, Integer> cache
    ) {
        log.info("{}", current);

        if (cache.containsKey(current)) {
            return cache.get(current);
        }

        if (current.equals(target)) {
            return 1;
        }

        int sum = 0;

        for (Vertex v : graph.getListOfNeighbours(current)) {
            sum += getNumberOfRoutesInternalDp(graph, target, v, cache);
        }

        cache.put(current, sum);

        return sum;
    }

    public static boolean hasCycle(@NotNull DirectedGraph graph) {
        Map<Vertex, Enum> states = new HashMap<>();

        // at the beginning all vertices are white
        graph.getVertices().forEach(
            v -> states.put(v, Color.WHITE)
        );

        for (Vertex v : states.keySet()) {
            log.info("V: {}", v);
            if (dfs(graph, v, states)) {
                return true;
            }
        }

        return false;
    }

    public static Stack<Vertex> topologicalSort(@NotNull DirectedGraph graph) {
        Map<Vertex, Enum> states = new HashMap<>();

        // at the beginning all vertices are white
        graph.getVertices().forEach(
            v -> states.put(v, Color.WHITE)
        );

        Stack<Vertex> ordering = new Stack<>();

        for (Vertex v : states.keySet()) {
            log.info("Next vertex: {}", v);

            if (states.get(v) == Color.WHITE) {
                if (dfs(graph, v, states, ordering, 0)) {
                    throw new IllegalArgumentException("Not a directed acyclic graph!");
                }
            }
        }

        return ordering;
    }

    public static Map<Vertex, Integer> getCountAllVertices(@NotNull DirectedGraph graph) {
        Map<Vertex, Integer> counts = new HashMap<>();
        Set<Vertex> visited = new HashSet<>();

        // at the beginning all vertices counts are -1
        graph.getVertices().forEach(
            v -> counts.put(v, -1)
        );

        for (Vertex vertex : counts.keySet()) {
            if (!visited.contains(vertex)) {
                int count = dfs(graph, vertex, counts, visited);
                counts.put(vertex, count);
            }
        }

        return counts;
    }

    public static Map<Vertex, Integer> getTotalWeightsAllVertices(@NotNull DirectedGraph graph) {
        Map<Vertex, Integer> weights = new HashMap<>();

        for (Vertex vertex : graph.getVertices()) {
            Set<Vertex> visited = new HashSet<>();
            int weight = getTotalWeight(graph, vertex, visited);
            weights.put(vertex, weight);
        }

        return weights;
    }

    public static List<WeightVertex> topologicalSortByTotalWeight(@NotNull DirectedGraph graph) {
        List<WeightVertex> result = new ArrayList<>();

        Map<Vertex, Integer> weights = getTotalWeightsAllVertices(graph);
        Map<Vertex, Integer> inDegrees = new HashMap<>(graph.getInDegrees());

        Map<Vertex, Enum> states = new HashMap<>();

        // at the beginning all vertices are white
        graph.getVertices().forEach(
            v -> states.put(v, Color.WHITE)
        );

        PriorityQueue<WeightVertex> queue = new PriorityQueue<>(
            Comparator.comparing(WeightVertex::getTotalWeight)
                .reversed()
        );

        // add roots
        inDegrees.entrySet().stream()
            .filter(e -> e.getValue() == 0)
            .map(e -> new WeightVertex(e.getKey(), weights.get(e.getKey())))
            .forEach(queue::offer);

        while (!queue.isEmpty()) {
            WeightVertex vertex = queue.poll();
            if (states.get(vertex.getVertex()) != Color.BLACK) {
                result.add(vertex);
                states.put(vertex.getVertex(), Color.BLACK);
            }

            for (Vertex neighbour : graph.getListOfNeighbours(vertex.getVertex())) {
                int inDegree = inDegrees.get(neighbour);
                if (states.get(neighbour) != Color.BLACK) {
                    if (inDegree - 1 == 0) {
                        queue.offer(new WeightVertex(neighbour, weights.get(neighbour)));
                    }
                }

                inDegrees.put(neighbour, --inDegree);
            }
        }

        return result;
    }

    private static int getTotalWeight(DirectedGraph graph, Vertex vertex, Set<Vertex> visited) {
        visited.add(vertex);

        if (graph.getListOfNeighbours(vertex).isEmpty()) {
            return graph.getWeight(vertex);
        }

        int weight = 0;

        for (Vertex neighbour : graph.getListOfNeighbours(vertex)) {
            if (!visited.contains(neighbour)) {
                weight += getTotalWeight(graph, neighbour, visited);
            }
        }

        weight += graph.getWeight(vertex);

        return weight;
    }

    private static int dfs(DirectedGraph graph, Vertex v, Map<Vertex, Integer> counts, Set<Vertex> visited) {
        log.info("Current: {}", v);

        visited.add(v);

        Set<Vertex> neighbours = graph.getListOfNeighbours(v);

        if (neighbours.isEmpty()) {
            counts.put(v, 0);
            return 0;
        } else {
            int sum = 0;
            for (Vertex neighbour : neighbours) {
                if (!visited.contains(neighbour)) {
                    sum += dfs(graph, neighbour, counts, visited);
                } else {
                    sum += counts.get(neighbour);
                }
            }

            visited.remove(v);

            return neighbours.size() + sum;
        }
    }

    private static boolean dfs(DirectedGraph graph, Vertex v, Map<Vertex, Enum> states, Stack<Vertex> ordering, int level) {
        log.info("{}Current: {}", Strings.repeat(' ', level * 2), v);

        states.put(v, Color.GREY);

        Set<Vertex> neighbours = graph.getListOfNeighbours(v);

        for (Vertex neighbour : neighbours) {
            if (states.get(neighbour) != Color.BLACK) {
                if (states.get(neighbour) == Color.GREY) {
                    log.info("{}Cycle is found in v {}", Strings.repeat(' ', level * 2), neighbour);
                    return true;
                }

                if (dfs(graph, neighbour, states, ordering, level + 1)) {
                    return true;
                }
            } else {
                log.info("{}Vertex {} has been already visited", Strings.repeat(' ', level * 2), neighbour);
            }
        }

        states.put(v, Color.BLACK);

        ordering.push(v);

        log.info("{}Vertex {} has been added", Strings.repeat(' ', level * 2), v);

        return false;
    }

    private static boolean dfs(DirectedGraph graph, Vertex v, Map<Vertex, Enum> states) {
        log.info("Current: {}", v);

        // move to grey
        states.put(v, Color.GREY);

        Set<Vertex> neighbours = graph.getListOfNeighbours(v);

        for (Vertex neighbour : neighbours) {
            if (states.get(neighbour) != Color.BLACK) {
                if (states.get(neighbour) == Color.GREY) {
                    log.info("Cycle is found in v {}", neighbour);
                    return true;
                }

                if (dfs(graph, neighbour, states)) {
                    return true;
                }
            } else {
                log.info("V: {} is black", neighbour);
            }
        }

        states.put(v, Color.BLACK);

        return false;
    }

    private static int[] graphTraverse(
        DirectedGraph graph,
        Vertex start,
        Supplier<Vertex> supplier,
        Consumer<Vertex> consumer,
        Collection<Vertex> collection
    ) {
        List<Integer> result = new ArrayList<>();
        Set<Vertex> visited = new HashSet<>();

        consumer.accept(start);

        while (!collection.isEmpty()) {
            Vertex newVertex = supplier.get();
            result.add(newVertex.getId());
            graph.getListOfNeighbours(newVertex).stream()
                .filter(neighbour -> !visited.contains(neighbour))
                .forEach(consumer);
            visited.add(newVertex);
        }

        return Ints.toArray(result);
    }

    private enum Color {
        WHITE,
        GREY,
        BLACK
    }

    private record WeightedVertex(Vertex v, int weight) implements Comparable<WeightedVertex> {
        @Override
        public int compareTo(@NotNull WeightedVertex o) {
            return Comparator.comparingInt(WeightedVertex::weight)
                .compare(this, o);
        }
    }
}
