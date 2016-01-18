package org.sherman.interview.graph;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static com.beust.jcommander.internal.Lists.newArrayList;
import static org.testng.Assert.assertEquals;


/**
 * @author Denis Gabaydulin
 * @since 18/01/2016
 */
public class DirectedGraphTest {
    private static final Logger log = LoggerFactory.getLogger(DirectedGraphTest.class);

    @Test
    public void addEdge() {
        DirectedGraph graph = new DirectedGraph();
        graph.addEdge(new DirectedEdge(new Vertex(1), new Vertex(2), 1));
        graph.addEdge(new DirectedEdge(new Vertex(2), new Vertex(3), 2));
        graph.addEdge(new DirectedEdge(new Vertex(2), new Vertex(4), 3));

        assertEquals(graph.getListOfNeighbours(new Vertex(1)), newArrayList(new Vertex(2)));
        assertEquals(graph.getListOfNeighbours(new Vertex(2)), newArrayList(new Vertex(3), new Vertex(4)));
    }
}
