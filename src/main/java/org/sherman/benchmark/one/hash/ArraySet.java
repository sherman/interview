package org.sherman.benchmark.one.hash;

import one.nio.mem.OutOfMemoryException;
import one.nio.util.JavaInternals;
import sun.misc.Unsafe;

public class ArraySet {
    private static final Unsafe unsafe = JavaInternals.unsafe;
    private static final long sizeOffset = JavaInternals.fieldOffset(ArraySet.class, "size");
    private static final long base = JavaInternals.getUnsafe().arrayBaseOffset(long[].class);

    public static final long EMPTY = 0;

    private volatile int size;
    private int capacity;
    private int maxSteps;

    private final int shift;

    private final long[] elts;

    public ArraySet(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.capacity = capacity;
        this.maxSteps = (int) Math.sqrt(capacity);
        this.elts = new long[capacity];

        int scale = unsafe.arrayIndexScale(long[].class);
        shift = 31 - Integer.numberOfLeadingZeros(scale);

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
                if (!unsafe.compareAndSwapLong(elts, byteOffset(index), cur, key)) {
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

    private long byteOffset(int i) {
        return ((long) i << shift) + base;
    }
}
