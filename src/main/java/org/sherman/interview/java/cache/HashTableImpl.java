package org.sherman.interview.java.cache;

import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.util.function.Function;

import com.google.common.base.Preconditions;
import jdk.incubator.foreign.MemoryAddress;
import jdk.incubator.foreign.MemoryLayout;
import jdk.incubator.foreign.MemorySegment;
import jdk.incubator.foreign.SequenceLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HashTableImpl implements HashTable<Long, Long> {
    private static final Logger logger = LoggerFactory.getLogger(HashTableImpl.class);

    private static final String KEY_ID = "key";
    private static final String VALUE_ID = "value";
    private static final long NO_KEY = 0;
    private static final long DELETED_KEY = -1;

    private final MemorySegment cacheMemory;
    private final int maxSize;
    private final MemoryAddress base;

    private final VarHandle keyHandle;
    private final VarHandle valueHandle;

    private final Function<Object, Integer> func;

    protected HashTableImpl(int size, Function<Object, Integer> func) {
        this.func = func;
        int keySize = 64;
        int valueSize = 64;
        Preconditions.checkArgument(valueSize == Utils.nextPowerOfTwo(valueSize), "Element size must be power of two!");
        Preconditions.checkArgument(size > 0, "Cache size must be > 0!");

        size = (int) Utils.nextPowerOfTwo(size);
        long memorySize = size * keySize + size * valueSize;
        this.cacheMemory = MemorySegment.allocateNative(memorySize);
        base = cacheMemory.baseAddress();

        Preconditions.checkArgument(memorySize >= keySize + valueSize, "Not enough cache memory for store at least one element!");

        maxSize = size;

        MemoryLayout entryLayout = MemoryLayout.ofStruct(
                MemoryLayout.ofValueBits(keySize, ByteOrder.BIG_ENDIAN).withName(KEY_ID),
                MemoryLayout.ofValueBits(valueSize, ByteOrder.BIG_ENDIAN).withName(VALUE_ID)
        );

        SequenceLayout hashMapLayout = MemoryLayout.ofSequence(maxSize, entryLayout);
        keyHandle = hashMapLayout.varHandle(long.class, MemoryLayout.PathElement.sequenceElement(), MemoryLayout.PathElement.groupElement(KEY_ID));
        valueHandle = hashMapLayout.varHandle(long.class, MemoryLayout.PathElement.sequenceElement(), MemoryLayout.PathElement.groupElement(VALUE_ID));

    }

    @Override
    public void put(Long key, Long value) {
        Preconditions.checkArgument(key != null, "Key must no be null!");
        Preconditions.checkArgument(key != NO_KEY, "Key " + NO_KEY + " is not supported!");
        Preconditions.checkArgument(key != DELETED_KEY, "Key " + DELETED_KEY + " is not supported!");

        int slot = (maxSize - 1) & func.apply(key);
        long keyElement = NO_KEY;
        while (slot < maxSize) {
            keyElement = (long) keyHandle.get(base, slot);
            if (keyElement == NO_KEY || keyElement == DELETED_KEY || keyElement == key) {
                break;
            }
            slot++;
        }

        if (keyElement != NO_KEY && keyElement != DELETED_KEY && keyElement != key) {
            throw new IllegalStateException("Not enough space!");
        }

        keyHandle.set(base, slot, key);
        valueHandle.set(base, slot, value);
    }

    @Override
    public Long get(Long key) {
        logger.debug("============== [{}]", key);
        int slot = (maxSize - 1) & func.apply(key);
        long keyElement = NO_KEY;
        while (slot < maxSize) {
            keyElement = (long) keyHandle.get(base, slot);
            logger.debug("Key: [{}]", keyElement);

            if (keyElement == NO_KEY) {
                return null;
            }

            if (keyElement == key) {
                return (long) valueHandle.get(base, slot);
            }
            slot++;
        }

        return null;
    }

    @Override
    public Long remove(Long key) {
        int slot = (maxSize - 1) & func.apply(key);
        long keyElement = NO_KEY;
        while (slot < maxSize) {
            keyElement = (long) keyHandle.get(base, slot);
            if (keyElement == key) {
                long value = (long) valueHandle.get(base, slot);
                keyHandle.set(base, slot, DELETED_KEY);
                return value;

            }
            slot++;
        }

        return null;
    }

    @Override
    public void close() {
        cacheMemory.close();
    }
}