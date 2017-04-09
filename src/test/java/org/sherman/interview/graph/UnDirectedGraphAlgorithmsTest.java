package org.sherman.interview.graph;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Denis Gabaydulin
 * @since 09.04.17
 */
public class UnDirectedGraphAlgorithmsTest {
    @Test
    public void getConnectedComponents() {
        UnDirectedGraph g = new UnDirectedGraph();
        g.addEdge(new Vertex(1), new Vertex(2));
        g.addEdge(new Vertex(1), new Vertex(3));
        g.addEdge(new Vertex(3), new Vertex(4));
        g.addEdge(new Vertex(5), new Vertex(6));
        g.addEdge(new Vertex(6), new Vertex(7));
        g.addEdge(new Vertex(8), new Vertex(9));

        assertEquals(UnDirectedGraphAlgorithms.getConnectedComponents(g), 3);
    }
}
