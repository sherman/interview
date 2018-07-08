package org.sherman.benchmark.one.hash;

import one.nio.mem.OutOfMemoryException;
import one.nio.util.JavaInternals;
import sun.misc.Unsafe;

public class ArraySet {
    protected static final Unsafe unsafe = JavaInternals.unsafe;
    protected static final long sizeOffset = JavaInternals.fieldOffset(ArraySet.class, "size");
    protected static final long eltsOffset = JavaInternals.fieldOffset(ArraySet.class, "elts");

    public static final long EMPTY = 0;

    protected volatile int size;
    protected int capacity;
    protected int maxSteps;
    protected long keys;

    private final long[] elts;

    public ArraySet(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.capacity = capacity;
        this.maxSteps = (int) Math.sqrt(capacity);
        this.elts = new long[capacity];
    }

    public final int size() {
        return size;
    }

    public final int capacity() {
        return capacity;
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
            long cur = keyAt(index);
            if (cur == EMPTY) {
                if (!unsafe.compareAndSwapLong(elts, (long) (2 + index) * 8, cur, key)) {
                    continue;
                }
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

    public final void setKeyAt(int index, long value) {
        unsafe.putOrderedLong(null, keys + (long) index * 8, value);
    }

    public void clear() {
        unsafe.setMemory(keys, (long) capacity * 8, (byte) 0);
    }

    protected void incrementSize() {
        for (;;) {
            int current = size;
            if (unsafe.compareAndSwapInt(this, sizeOffset, current, current + 1)) {
                return;
            }
        }
    }

    protected static int hash(long key) {
        return ((int) key ^ (int) (key >>> 21) ^ (int) (key >>> 42)) & 0x7fffffff;
    }
}
