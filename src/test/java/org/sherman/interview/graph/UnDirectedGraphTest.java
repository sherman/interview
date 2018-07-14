package org.sherman.interview.graph;

import org.testng.annotations.Test;

import static com.beust.jcommander.internal.Lists.newArrayList;
import static org.testng.Assert.assertEquals;

public class UnDirectedGraphTest {
    @Test
    public void addEdge() {
        UnDirectedGraph graph = new UnDirectedGraph();
        graph.addEdge(new Vertex(1), new Vertex(2));
        graph.addEdge(new Vertex(2), new Vertex(3));
        graph.addEdge(new Vertex(2), new Vertex(4));

        assertEquals(graph.getListOfNeighbours(new Vertex(1)), newArrayList(new Vertex(2)));
        assertEquals(graph.getListOfNeighbours(new Vertex(2)), newArrayList(new Vertex(1), new Vertex(3), new Vertex(4)));
    }
}
