package org.sherman.interview.java;

import one.nio.mem.DirectMemory;
import one.nio.util.JavaInternals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Unsafe;

public class OffheapMemorySemanticsApp {
    private static final Logger logger = LoggerFactory.getLogger(OffheapMemorySemanticsApp.class);

    public static void main(String[] args) {
        var array = new OffheapArray(256);
        logger.info("[{}]", array.get(1));
        array.set(1, 5);
        logger.info("[{}]", array.get(1));
    }

    private static class OffheapArray {
        public static final long EMPTY = 0;
        public static final long REMOVED = 0x8000000000000000L;

        private static final Unsafe unsafe = JavaInternals.unsafe;
        private static final long sizeOffset = JavaInternals.fieldOffset(OffheapArray.class, "size");

        private final long valuesAddress;
        private int size = 0;

        public OffheapArray(int capacity) {
            this.valuesAddress = DirectMemory.allocateAndClear(sizeInBytes(capacity), this);
        }

        public void set(int index, long value) {
            unsafe.putLongVolatile(null, valuesAddress + (long) index * Long.BYTES, value);
            unsafe.putInt(this, sizeOffset, size + 1);
        }

        public long get(int index) {
            return unsafe.getLongVolatile(null, valuesAddress + (long) index * Long.BYTES);
        }

        private static long sizeInBytes(int capacity) {
            return (long) capacity * Long.BYTES;
        }
    }
}
