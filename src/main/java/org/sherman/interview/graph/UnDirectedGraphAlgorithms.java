package org.sherman.interview.graph;

import com.google.common.collect.Sets;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author Denis Gabaydulin
 * @since 09.04.17
 */
public class UnDirectedGraphAlgorithms {
    private static final Logger log = LoggerFactory.getLogger(UnDirectedGraphAlgorithms.class);

    private UnDirectedGraphAlgorithms() {
    }

    public static int getConnectedComponents(@NotNull UnDirectedGraph g) {
        Set<Vertex> visited = new HashSet<>();
        Set<Vertex> vertexes = g.getAllVertices();

        int components = 0;

        while (!Sets.difference(vertexes, visited).isEmpty()) {
            components++;
            vertexes.stream()
                    .filter(v -> !visited.contains(v))
                    .findFirst()
                    .ifPresent(
                            v -> dfs(g, visited, v)
                    );
        }

        return components;
    }

    private static void dfs(UnDirectedGraph g, Set<Vertex> visited, Vertex start) {
        Stack<Vertex> toVisit = new Stack<>();
        toVisit.push(start);

        while (!toVisit.isEmpty()) {
            Vertex v = toVisit.pop();
            visited.add(v);

            g.getListOfNeighbours(v).stream().filter(adj -> !visited.contains(adj))
                    .forEach(toVisit::push);
        }
    }
}
