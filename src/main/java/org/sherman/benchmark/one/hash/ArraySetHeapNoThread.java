package org.sherman.benchmark.one.hash;

import one.nio.mem.OutOfMemoryException;
import one.nio.util.JavaInternals;
import sun.misc.Unsafe;

public class ArraySetHeapNoThread {
    public static final long EMPTY = 0;

    private int size;
    private int capacity;
    private int maxSteps;

    private final long[] elts;

    private int probes;

    public ArraySetHeapNoThread(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.capacity = capacity;
        this.maxSteps = capacity;
        this.elts = new long[capacity];
    }

    public final int size() {
        return size;
    }

    public final int capacity() {
        return capacity;
    }

    public final int probes() {
        return probes;
    }

    public final int getKey(long key) {
        int step = 1;
        int index = hash(key) % capacity;

        do {
            long cur = keyAt(index);
            if (cur == key) {
                return index;
            } else if (cur == EMPTY) {
                return -1;
            }

            if ((index += step) >= capacity) index -= capacity;
        } while (++step <= maxSteps);

        return -1;
    }

    public final int putKey(long key) {
        int step = 1;
        int index = hash(key) % capacity;

        do {
            probes++;
            long cur = keyAt(index);
            if (cur == EMPTY) {
                elts[index] = key;
                incrementSize();
                return index;
            } else if (cur == key) {
                return index;
            }

            if ((index += step) >= capacity) index -= capacity;
        } while (++step <= maxSteps);

        throw new OutOfMemoryException("No room for a new key");
    }

    public final long keyAt(int index) {
        return elts[index];
    }

    protected void incrementSize() {
        size++;
    }

    protected static int hash(long key) {
        //return (((int) key ^ (int)(key >>> 32)) * MAGIC) >>> 32 - P;
        return ((int) key ^ (int) (key >>> 21) ^ (int) (key >>> 42)) & 0x7fffffff;
    }
}
