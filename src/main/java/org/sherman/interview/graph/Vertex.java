package org.sherman.interview.graph;

import java.util.Objects;

/**
 * @author Denis Gabaydulin
 * @since 18/01/2016
 */
public class Vertex {
    private final int id;

    public Vertex(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return id == vertex.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Vertex{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
