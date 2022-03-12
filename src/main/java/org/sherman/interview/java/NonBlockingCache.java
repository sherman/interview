package org.sherman.interview.java;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class NonBlockingCache {
    private final Segment left;
    private final Segment right;
    private volatile Segment active;

    public NonBlockingCache(int size) {
        left = new Segment(new long[size]);
        right = new Segment(new long[size]);
        active = left;
    }

    public synchronized void update(Map<Integer, Long> update) {
        if (active == left) {
            swap(left, right, update);
        } else {
            swap(right, left, update);
        }
    }

    private void swap(Segment oldSegment, Segment newSegment, Map<Integer, Long> update) {
        // update data in the next active segment
        for (var entry : update.entrySet()) {
            newSegment.data[entry.getKey()] = entry.getValue();
        }

        active = newSegment;
        waitForReaders();

        // update data in the previously active segment
        for (var entry : update.entrySet()) {
            oldSegment.data[entry.getKey()] = entry.getValue();
        }
    }

    private void waitForReaders() {
        while (active.readers.get() > 0) {
            Thread.yield();
        }
    }

    public long[] getData(int[] indexes) {
        try {
            active.readers.incrementAndGet();

            var result = new long[indexes.length];
            for (int i = 0; i < indexes.length; i++) {
                result[i] = active.data[indexes[i]];
            }
            return result;
        } finally {
            active.readers.decrementAndGet();
        }
    }

    private static class Segment {
        private final AtomicInteger readers = new AtomicInteger();
        private final long[] data;

        private Segment(long[] data) {
            this.data = data;
        }
    }
}
