package org.sherman.interview.graph;

import java.util.Objects;

/**
 * @author Denis Gabaydulin
 * @since 18/01/2016
 */
public class DirectedEdge {
    private final Vertex from;
    private final Vertex to;
    private final int weight;

    public DirectedEdge(Vertex from, Vertex to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectedEdge edge = (DirectedEdge) o;
        return weight == edge.weight &&
                Objects.equals(from, edge.from);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                ", weight=" + weight +
                '}';
    }

    public Vertex getFrom() {
        return from;
    }

    public Vertex getTo() {
        return to;
    }

    public int getWeight() {
        return weight;
    }
}
