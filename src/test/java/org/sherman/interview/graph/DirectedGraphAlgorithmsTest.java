package org.sherman.interview.graph;

import com.beust.jcommander.internal.Lists;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import org.testng.internal.junit.ArrayAsserts;

import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.reverse;
import static org.testng.Assert.*;

/**
 * @author Denis Gabaydulin
 * @since 18/01/2016
 */
public class DirectedGraphAlgorithmsTest {
    private static final Logger log = LoggerFactory.getLogger(DirectedGraphAlgorithmsTest.class);

    @Test
    public void naiveDijkstra() {
        DirectedGraph graph = new DirectedGraph();
        graph.addEdge(new DirectedEdge(new Vertex(1), new Vertex(2), 7));
        graph.addEdge(new DirectedEdge(new Vertex(1), new Vertex(3), 9));
        graph.addEdge(new DirectedEdge(new Vertex(1), new Vertex(6), 14));
        graph.addEdge(new DirectedEdge(new Vertex(2), new Vertex(3), 10));
        graph.addEdge(new DirectedEdge(new Vertex(2), new Vertex(4), 15));
        graph.addEdge(new DirectedEdge(new Vertex(3), new Vertex(4), 11));
        graph.addEdge(new DirectedEdge(new Vertex(3), new Vertex(6), 2));
        graph.addEdge(new DirectedEdge(new Vertex(4), new Vertex(5), 6));
        graph.addEdge(new DirectedEdge(new Vertex(5), new Vertex(6), 9));

        assertEquals(
            DirectedGraphAlgorithms.naiveDijkstra(graph, new Vertex(1), new Vertex(5)).stream()
                .map(Vertex::getId)
                .collect(Collectors.toList()),
            Lists.newArrayList(1, 3, 4, 5)
        );

        assertEquals(
            DirectedGraphAlgorithms.naiveDijkstra(graph, new Vertex(2), new Vertex(3)).stream()
                .map(Vertex::getId)
                .collect(Collectors.toList()),
            Lists.newArrayList(2, 3)
        );

        assertEquals(
            DirectedGraphAlgorithms.naiveDijkstra(graph, new Vertex(1), new Vertex(4)).stream()
                .map(Vertex::getId)
                .collect(Collectors.toList()),
            Lists.newArrayList(1, 3, 4)
        );
    }

    @Test
    public void bfs() {
        DirectedGraph graph = new DirectedGraph();
        graph.addEdge(new DirectedEdge(new Vertex(1), new Vertex(2), 1));
        graph.addEdge(new DirectedEdge(new Vertex(1), new Vertex(3), 1));
        graph.addEdge(new DirectedEdge(new Vertex(3), new Vertex(4), 1));
        graph.addEdge(new DirectedEdge(new Vertex(3), new Vertex(5), 1));
        graph.addEdge(new DirectedEdge(new Vertex(2), new Vertex(6), 1));
        graph.addEdge(new DirectedEdge(new Vertex(2), new Vertex(7), 1));
        graph.addEdge(new DirectedEdge(new Vertex(5), new Vertex(8), 1));

        ArrayAsserts.assertArrayEquals(new int[]{1, 2, 3, 6, 7, 4, 5, 8}, DirectedGraphAlgorithms.bfs(graph, new Vertex(1)));
    }

    @Test
    public void dfs() {
        DirectedGraph graph = new DirectedGraph();
        graph.addEdge(new DirectedEdge(new Vertex(1), new Vertex(2), 1));
        graph.addEdge(new DirectedEdge(new Vertex(1), new Vertex(3), 1));
        graph.addEdge(new DirectedEdge(new Vertex(3), new Vertex(4), 1));
        graph.addEdge(new DirectedEdge(new Vertex(3), new Vertex(5), 1));
        graph.addEdge(new DirectedEdge(new Vertex(2), new Vertex(6), 1));
        graph.addEdge(new DirectedEdge(new Vertex(2), new Vertex(7), 1));
        graph.addEdge(new DirectedEdge(new Vertex(5), new Vertex(8), 1));

        ArrayAsserts.assertArrayEquals(new int[]{1, 3, 5, 8, 4, 2, 7, 6}, DirectedGraphAlgorithms.dfs(graph, new Vertex(1)));
    }

    @Test
    public void noCycle() {
        DirectedGraph graph = new DirectedGraph();
        graph.addEdge(new DirectedEdge(new Vertex(1), new Vertex(2), 1));
        graph.addEdge(new DirectedEdge(new Vertex(1), new Vertex(3), 1));
        graph.addEdge(new DirectedEdge(new Vertex(3), new Vertex(4), 1));
        graph.addEdge(new DirectedEdge(new Vertex(3), new Vertex(5), 1));
        graph.addEdge(new DirectedEdge(new Vertex(2), new Vertex(6), 1));
        graph.addEdge(new DirectedEdge(new Vertex(2), new Vertex(7), 1));
        graph.addEdge(new DirectedEdge(new Vertex(5), new Vertex(8), 1));
        graph.addEdge(new DirectedEdge(new Vertex(4), new Vertex(5), 1));

        assertFalse(DirectedGraphAlgorithms.hasCycle(graph));
    }

    @Test
    public void hasCycle() {
        DirectedGraph graph = new DirectedGraph();
        graph.addEdge(new DirectedEdge(new Vertex(1), new Vertex(2), 1));
        graph.addEdge(new DirectedEdge(new Vertex(1), new Vertex(3), 1));
        graph.addEdge(new DirectedEdge(new Vertex(3), new Vertex(4), 1));
        graph.addEdge(new DirectedEdge(new Vertex(3), new Vertex(5), 1));
        graph.addEdge(new DirectedEdge(new Vertex(2), new Vertex(6), 1));
        graph.addEdge(new DirectedEdge(new Vertex(2), new Vertex(7), 1));
        graph.addEdge(new DirectedEdge(new Vertex(5), new Vertex(8), 1));
        graph.addEdge(new DirectedEdge(new Vertex(5), new Vertex(1), 1));

        assertTrue(DirectedGraphAlgorithms.hasCycle(graph));
    }

    @Test
    public void hasCycle2() {
        DirectedGraph graph = new DirectedGraph();
        graph.addEdge(new DirectedEdge(new Vertex(1), new Vertex(2), 1));
        graph.addEdge(new DirectedEdge(new Vertex(1), new Vertex(3), 1));
        graph.addEdge(new DirectedEdge(new Vertex(3), new Vertex(4), 1));
        graph.addEdge(new DirectedEdge(new Vertex(3), new Vertex(5), 1));
        graph.addEdge(new DirectedEdge(new Vertex(2), new Vertex(6), 1));
        graph.addEdge(new DirectedEdge(new Vertex(2), new Vertex(7), 1));
        graph.addEdge(new DirectedEdge(new Vertex(5), new Vertex(8), 1));
        graph.addEdge(new DirectedEdge(new Vertex(8), new Vertex(3), 1));

        assertTrue(DirectedGraphAlgorithms.hasCycle(graph));
    }

    @Test
    public void topologicalSort() {
        DirectedGraph graph = new DirectedGraph();
        graph.addEdge(new DirectedEdge(new Vertex(5), new Vertex(11), 1));
        graph.addEdge(new DirectedEdge(new Vertex(7), new Vertex(11), 1));
        graph.addEdge(new DirectedEdge(new Vertex(7), new Vertex(8), 1));
        graph.addEdge(new DirectedEdge(new Vertex(3), new Vertex(8), 1));
        graph.addEdge(new DirectedEdge(new Vertex(3), new Vertex(10), 1));
        graph.addEdge(new DirectedEdge(new Vertex(8), new Vertex(9), 1));
        graph.addEdge(new DirectedEdge(new Vertex(11), new Vertex(2), 1));
        graph.addEdge(new DirectedEdge(new Vertex(11), new Vertex(9), 1));
        graph.addEdge(new DirectedEdge(new Vertex(11), new Vertex(10), 1));

        Stack<Vertex> ordering = DirectedGraphAlgorithms.topologicalSort(graph);

        assertEquals(reverse(ordering.stream().map(Vertex::getId).collect(Collectors.toList())), ImmutableList.of(7, 5, 11, 3, 10, 8, 9, 2));
    }

    @Test
    public void topologicalSort2() {
        DirectedGraph graph = new DirectedGraph();
        graph.addEdge(new DirectedEdge(new Vertex(5), new Vertex(2), 1));
        graph.addEdge(new DirectedEdge(new Vertex(5), new Vertex(0), 1));
        graph.addEdge(new DirectedEdge(new Vertex(4), new Vertex(0), 1));
        graph.addEdge(new DirectedEdge(new Vertex(4), new Vertex(1), 1));
        graph.addEdge(new DirectedEdge(new Vertex(2), new Vertex(3), 1));
        graph.addEdge(new DirectedEdge(new Vertex(3), new Vertex(1), 1));

        Stack<Vertex> ordering = DirectedGraphAlgorithms.topologicalSort(graph);

        assertEquals(reverse(ordering.stream().map(Vertex::getId).collect(Collectors.toList())), ImmutableList.of(5, 4, 0, 2, 3, 1));
    }

    @Test
    public void getCountAllVertices() {
        DirectedGraph graph = new DirectedGraph();
        graph.addEdge(new DirectedEdge(new Vertex(5), new Vertex(11), 1));
        graph.addEdge(new DirectedEdge(new Vertex(7), new Vertex(11), 1));
        graph.addEdge(new DirectedEdge(new Vertex(7), new Vertex(8), 1));
        graph.addEdge(new DirectedEdge(new Vertex(3), new Vertex(8), 1));
        graph.addEdge(new DirectedEdge(new Vertex(3), new Vertex(10), 1));
        graph.addEdge(new DirectedEdge(new Vertex(8), new Vertex(9), 1));
        graph.addEdge(new DirectedEdge(new Vertex(11), new Vertex(2), 1));
        graph.addEdge(new DirectedEdge(new Vertex(11), new Vertex(9), 1));
        graph.addEdge(new DirectedEdge(new Vertex(11), new Vertex(10), 1));

        Map<Vertex, Integer> count = DirectedGraphAlgorithms.getCountAllVertices(graph);
        log.info("{}", count);

        assertEquals(
            count,
            new ImmutableMap.Builder<>()
                .put(new Vertex(5), 4)
                .put(new Vertex(3), 3)
                .put(new Vertex(7), 6)
                .put(new Vertex(8), 1)
                .put(new Vertex(9), 0)
                .put(new Vertex(11), 3)
                .put(new Vertex(10), 0)
                .put(new Vertex(2), 0)
                .build()
        );
    }

    @Test
    public void getNumberOfRoutes() {
        DirectedGraph g = new DirectedGraph();
        g.addEdge(new Vertex(1), new Vertex(2));
        g.addEdge(new Vertex(1), new Vertex(3));
        g.addEdge(new Vertex(2), new Vertex(4));
        g.addEdge(new Vertex(2), new Vertex(5));
        g.addEdge(new Vertex(3), new Vertex(6));
        g.addEdge(new Vertex(3), new Vertex(7));
        g.addEdge(new Vertex(4), new Vertex(8));
        g.addEdge(new Vertex(5), new Vertex(8));
        g.addEdge(new Vertex(6), new Vertex(8));
        g.addEdge(new Vertex(7), new Vertex(8));
        assertEquals(DirectedGraphAlgorithms.getNumberOfRoutes(g, new Vertex(1), new Vertex(8)), 4);

        g = new DirectedGraph();
        g.addEdge(new Vertex(1), new Vertex(2));
        g.addEdge(new Vertex(1), new Vertex(3));
        g.addEdge(new Vertex(2), new Vertex(3));
        g.addEdge(new Vertex(2), new Vertex(4));
        g.addEdge(new Vertex(3), new Vertex(4));
        assertEquals(DirectedGraphAlgorithms.getNumberOfRoutes(g, new Vertex(1), new Vertex(4)), 3);

        g = new DirectedGraph();
        g.addEdge(new Vertex(1), new Vertex(2));
        g.addEdge(new Vertex(2), new Vertex(3));
        g.addEdge(new Vertex(3), new Vertex(4));
        g.addEdge(new Vertex(4), new Vertex(5));
        g.addEdge(new Vertex(3), new Vertex(6));
        g.addEdge(new Vertex(5), new Vertex(7));
        g.addEdge(new Vertex(6), new Vertex(7));
        g.addEdge(new Vertex(4), new Vertex(7));
        assertEquals(DirectedGraphAlgorithms.getNumberOfRoutes(g, new Vertex(1), new Vertex(7)), 3);
    }

    @Test
    public void getNumberOfRoutesDp() {
        DirectedGraph g = new DirectedGraph();
        g.addEdge(new Vertex(2), new Vertex(1));
        g.addEdge(new Vertex(3), new Vertex(1));
        g.addEdge(new Vertex(4), new Vertex(2));
        g.addEdge(new Vertex(5), new Vertex(2));
        g.addEdge(new Vertex(6), new Vertex(3));
        g.addEdge(new Vertex(7), new Vertex(3));
        g.addEdge(new Vertex(8), new Vertex(4));
        g.addEdge(new Vertex(8), new Vertex(5));
        g.addEdge(new Vertex(8), new Vertex(6));
        g.addEdge(new Vertex(8), new Vertex(7));
        assertEquals(DirectedGraphAlgorithms.getNumberOfRoutesDp(g, new Vertex(1), new Vertex(8)), 4);
    }

    @Test
    public void getTotalWeightsAllVertices() {
        DirectedGraph g = new DirectedGraph();
        g.addEdge(new DirectedEdge(new Vertex(1), new Vertex(2), 1));
        g.addEdge(new DirectedEdge(new Vertex(1), new Vertex(3), 1));
        g.addEdge(new DirectedEdge(new Vertex(3), new Vertex(4), 1));
        g.addEdge(new DirectedEdge(new Vertex(5), new Vertex(6), 1));
        g.addEdge(new DirectedEdge(new Vertex(6), new Vertex(7), 1));
        g.addEdge(new DirectedEdge(new Vertex(7), new Vertex(8), 1));

        assertEquals(
            DirectedGraphAlgorithms.getTotalWeightsAllVertices(g),
            new ImmutableMap.Builder<>()
                .put(new Vertex(1), 3)
                .put(new Vertex(2), 1)
                .put(new Vertex(3), 2)
                .put(new Vertex(4), 1)
                .put(new Vertex(5), 3)
                .put(new Vertex(6), 3)
                .put(new Vertex(7), 2)
                .put(new Vertex(8), 1)
                .build()
        );
    }

    @Test
    public void topologicalSortByTotalWeight() {
        DirectedGraph g = new DirectedGraph();
        g.addEdge(new DirectedEdge(new Vertex(1), new Vertex(2), 1));
        g.addEdge(new DirectedEdge(new Vertex(1), new Vertex(3), 1));
        g.addEdge(new DirectedEdge(new Vertex(3), new Vertex(4), 1));
        g.addEdge(new DirectedEdge(new Vertex(5), new Vertex(6), 1));
        g.addEdge(new DirectedEdge(new Vertex(6), new Vertex(7), 1));
        g.addEdge(new DirectedEdge(new Vertex(7), new Vertex(8), 1));
        g.addEdge(new DirectedEdge(new Vertex(9), new Vertex(8), 1));

        List<WeightVertex> weightVertices = DirectedGraphAlgorithms.topologicalSortByTotalWeight(g);
        assertEquals(
            weightVertices,
            new ImmutableList.Builder<>()
                .add(new WeightVertex(new Vertex(1), 3))
                .add(new WeightVertex(new Vertex(5), 3))
                .add(new WeightVertex(new Vertex(6), 3))
                .add(new WeightVertex(new Vertex(3), 2))
                .add(new WeightVertex(new Vertex(7), 2))
                .add(new WeightVertex(new Vertex(4), 1))
                .add(new WeightVertex(new Vertex(2), 1))
                .add(new WeightVertex(new Vertex(9), 1))
                .add(new WeightVertex(new Vertex(8), 1))
                .build()
        );

        g = new DirectedGraph();
        g.addEdge(new DirectedEdge(new Vertex(1), new Vertex(2), 10));
        g.addEdge(new DirectedEdge(new Vertex(1), new Vertex(3), 1));
        g.addEdge(new DirectedEdge(new Vertex(3), new Vertex(4), 2));
        g.addEdge(new DirectedEdge(new Vertex(3), new Vertex(5), 1));
        g.addEdge(new DirectedEdge(new Vertex(4), new Vertex(7), 2));
        g.addEdge(new DirectedEdge(new Vertex(5), new Vertex(6), 1));
        g.addEdge(new DirectedEdge(new Vertex(7), new Vertex(8), 1));
        g.addEdge(new DirectedEdge(new Vertex(6), new Vertex(8), 1));
        g.addEdge(new DirectedEdge(new Vertex(6), new Vertex(9), 1));

        weightVertices = DirectedGraphAlgorithms.topologicalSortByTotalWeight(g);
        assertEquals(
            weightVertices,
            new ImmutableList.Builder<>()
                .add(new WeightVertex(new Vertex(1), 19))
                .add(new WeightVertex(new Vertex(2), 10))
                .add(new WeightVertex(new Vertex(3), 9))
                .add(new WeightVertex(new Vertex(4), 5))
                .add(new WeightVertex(new Vertex(5), 4))
                .add(new WeightVertex(new Vertex(7), 3))
                .add(new WeightVertex(new Vertex(6), 3))
                .add(new WeightVertex(new Vertex(8), 1))
                .add(new WeightVertex(new Vertex(9), 1))
                .build()
        );
    }
}
