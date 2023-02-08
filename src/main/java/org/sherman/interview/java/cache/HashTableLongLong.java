package org.sherman.interview.java.cache;

import com.google.common.base.Preconditions;
import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;
import java.lang.foreign.SequenceLayout;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HashTableLongLong {
    private static final Logger logger = LoggerFactory.getLogger(HashTableLongLong.class);

    private static final String KEY_ID = "key";
    private static final String VALUE_ID = "value";
    private static final long NO_KEY = 0;
    private static final long DELETED_KEY = -1;
    private static final long NO_VALUE = -1; // FIXME: what if the value is bellow 0?

    private final MemorySegment cacheMemory;
    private final int maxSize;
    private final MemoryAddress base;

    private final VarHandle keyHandle;
    private final VarHandle valueHandle;
    private final boolean noHash;

    public HashTableLongLong(int size) {
        this(size, false);
    }

    public HashTableLongLong(int size, boolean noHash) {
        int keySize = 64;
        int valueSize = 64;
        Preconditions.checkArgument(valueSize == Utils.nextPowerOfTwo(valueSize), "Element size must be power of two!");
        Preconditions.checkArgument(size > 0, "Cache size must be > 0!");

        size = (int) Utils.nextPowerOfTwo(size);
        long memorySize = size * keySize + size * valueSize;
        this.cacheMemory = MemorySegment.allocateNative(memorySize, MemorySession.global());
        base = cacheMemory.address();

        Preconditions.checkArgument(memorySize >= keySize + valueSize, "Not enough cache memory for store at least one element!");

        maxSize = size;

        MemoryLayout entryLayout = MemoryLayout.structLayout(
            MemoryLayout.valueLayout(long.class, ByteOrder.BIG_ENDIAN).withName(KEY_ID),
            MemoryLayout.valueLayout(long.class, ByteOrder.BIG_ENDIAN).withName(VALUE_ID)
        );

        SequenceLayout hashMapLayout = MemoryLayout.sequenceLayout(maxSize, entryLayout);
        keyHandle = hashMapLayout.varHandle(MemoryLayout.PathElement.sequenceElement(), MemoryLayout.PathElement.groupElement(KEY_ID));
        valueHandle = hashMapLayout.varHandle(MemoryLayout.PathElement.sequenceElement(), MemoryLayout.PathElement.groupElement(VALUE_ID));

        this.noHash = noHash;
    }

    public void put(long key, long value) {
        Preconditions.checkArgument(key != NO_KEY, "Key " + NO_KEY + " is not supported!");
        Preconditions.checkArgument(key != DELETED_KEY, "Key " + DELETED_KEY + " is not supported!");

        int start = (maxSize - 1) & (noHash ? noHash() : hashLong(key));

        int slot = start;
        long keyElement = NO_KEY;
        while (slot < maxSize) {
            keyElement = (long) keyHandle.get(base, slot);
            if (keyElement == NO_KEY || keyElement == DELETED_KEY || keyElement == key) {
                break;
            }
            slot++;
        }

        boolean notFound = keyElement != NO_KEY && keyElement != DELETED_KEY && keyElement != key;

        if (notFound) {
            // lets try to find slot at the beginning
            int max = slot;
            slot = 0;
            keyElement = NO_KEY;
            while (slot < max) {
                keyElement = (long) keyHandle.get(base, slot);
                if (keyElement == NO_KEY || keyElement == DELETED_KEY || keyElement == key) {
                    break;
                }
                slot++;
            }
        }

        notFound = keyElement != NO_KEY && keyElement != DELETED_KEY && keyElement != key;

        if (notFound) {
            throw new IllegalStateException("Not enough space!");
        }

        keyHandle.set(base, slot, key);
        valueHandle.set(base, slot, value);
    }

    public long get(long key) {
        //logger.debug("============== [{}]", key);
        int slot = (maxSize - 1) & (noHash ? noHash() : hashLong(key));
        long value = findValue(key, slot, maxSize);
        if (value == NO_VALUE) {
            return findValue(key, 0, slot);
        } else {
            return value;
        }
    }

    private long findValue(long key, int start, int max) {
        int slot = start;
        long keyElement = NO_KEY;
        while (slot < max) {
            keyElement = (long) keyHandle.get(base, slot);
            //logger.debug("Key: [{}]", keyElement);

            if (keyElement == NO_KEY) {
                return NO_VALUE;
            }

            if (keyElement == key) {
                return (long) valueHandle.get(base, slot);
            }
            slot++;
        }

        return NO_VALUE;
    }

    public long remove(long key) {
        int slot = (maxSize - 1) & (noHash ? noHash() : hashLong(key));
        long value = removeValue(key, slot, maxSize);
        if (value == NO_VALUE) {
            return removeValue(key, 0, slot);
        } else {
            return value;
        }
    }

    private long removeValue(Long key, int start, int max) {
        long keyElement = NO_KEY;
        int slot = start;
        while (slot < max) {
            keyElement = (long) keyHandle.get(base, slot);
            if (keyElement == key) {
                long value = (long) valueHandle.get(base, slot);
                keyHandle.set(base, slot, DELETED_KEY);
                return value;

            }
            slot++;
        }

        return NO_VALUE;
    }

    public void close() {
        // TODO: how to close resource?
    }

    private static int noHash() {
        return 1;
    }

    private static int hashLong(long key) {
        int h;
        return key == 0 ? 0 : (h = Long.hashCode(key)) ^ (h >>> 16);
    }
}
