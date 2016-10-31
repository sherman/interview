package org.sherman.interview.graph;

import com.beust.jcommander.internal.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import org.testng.internal.junit.ArrayAsserts;

import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

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

        ArrayAsserts.assertArrayEquals(new int[]{1, 2, 3, 4, 5, 8, 6, 7}, DirectedGraphAlgorithms.dfs(graph, new Vertex(1)));
    }
}
