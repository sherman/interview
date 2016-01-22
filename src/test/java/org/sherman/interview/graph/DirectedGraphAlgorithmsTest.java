package org.sherman.interview.graph;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

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

        log.info("Shortest path : {}", DirectedGraphAlgorithms.naiveDijkstra(graph, new Vertex(1), new Vertex(5)));
    }
}
