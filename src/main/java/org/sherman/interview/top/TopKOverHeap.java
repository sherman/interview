package org.sherman.interview.top;

import java.util.PriorityQueue;

public class TopKOverHeap<T extends Comparable<T>> {
    private final PriorityQueue<T> internal;
    private final int top;

    public TopKOverHeap(int top) {
        this.internal = new PriorityQueue<>();
        this.top = top;
    }

    public boolean add(T value) {
        assert top > 0;
        if (internal.size() == top) {
            var top = internal.peek();
            if (top.compareTo(value) > 0) {
                return false;
            } else {
                internal.poll();
            }
        }

        internal.add(value);

        return true;
    }

    public T poll() {
        return internal.poll();
    }

    public boolean isEmpty() {
        return internal.isEmpty();
    }
}
