package org.sherman.interview.java.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.function.Function;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class LongLongHashTableTest {
    private static final Logger logger = LoggerFactory.getLogger(LongLongHashTableTest.class);

    private final Function<Object, Integer> func = Utils::hash;
    private final Function<Object, Integer> noHash = a -> 1;

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void storeZeroKey() {
        HashTable<Long, Long> hashTable = new HashTableImpl(4, func);
        hashTable.put(0L, 32L);
    }

    @Test
    public void singleEntry() {
        HashTable<Long, Long> hashTable = new HashTableImpl(1, func);
        hashTable.put(1L, 32L);
        Assert.assertEquals(hashTable.get(1L), Long.valueOf(32));
        Assert.assertNull(hashTable.get(42L));
        hashTable.close();
    }

    @Test
    public void multipleEntries() {
        HashTable<Long, Long> hashTable = new HashTableImpl(4, func);
        hashTable.put(1L, 32L);
        hashTable.put(2L, 33L);
        hashTable.put(42L, 34L);
        Assert.assertEquals(hashTable.get(1L), Long.valueOf(32));
        Assert.assertEquals(hashTable.get(2L), Long.valueOf(33));
        Assert.assertEquals(hashTable.get(42L), Long.valueOf(34));
        hashTable.close();
    }

    @Test
    public void insertAtBeginning() {
        HashTable<Long, Long> hashTable = new HashTableImpl(4, noHash);
        hashTable.put(3L, 3L);
        hashTable.put(2L, 2L);
        hashTable.put(4L, 4L);
        hashTable.put(1L, 1L);
        Assert.assertEquals(hashTable.get(1L), Long.valueOf(1));
        Assert.assertEquals(hashTable.get(2L), Long.valueOf(2));
        Assert.assertEquals(hashTable.get(3L), Long.valueOf(3));
        Assert.assertEquals(hashTable.get(4L), Long.valueOf(4));
        hashTable.close();
    }

    @Test
    public void update() {
        HashTable<Long, Long> hashTable = new HashTableImpl(1, func);
        hashTable.put(1L, 32L);
        Assert.assertEquals(hashTable.get(1L), Long.valueOf(32));
        hashTable.put(1L, 33L);
        Assert.assertEquals(hashTable.get(1L), Long.valueOf(33));
        hashTable.close();
    }

    @Test
    public void multipleUpdates() {
        HashTable<Long, Long> hashTable = new HashTableImpl(1, func);
        hashTable.put(1L, 32L);
        Assert.assertEquals(hashTable.get(1L), Long.valueOf(32));
        hashTable.remove(1L);
        hashTable.put(1L, 33L);
        Assert.assertEquals(hashTable.get(1L), Long.valueOf(33));
        hashTable.close();
    }

    @Test
    public void notEnoughSpace() {
        HashTable<Long, Long> hashTable = new HashTableImpl(1, func);
        hashTable.put(1L, 32L);
        Assert.assertEquals(hashTable.get(1L), Long.valueOf(32));
        try {
            hashTable.put(2L, 33L);
        } catch (IllegalStateException e) {
            assertEquals(e.getMessage(), "Not enough space!");
            hashTable.close();
            return;
        }
        fail();
    }

    @Test
    public void remove() {
        HashTable<Long, Long> hashTable = new HashTableImpl(10, func);
        hashTable.put(1L, 32L);
        Assert.assertEquals(hashTable.get(1L), Long.valueOf(32));
        Assert.assertEquals(hashTable.remove(1L), Long.valueOf(32));
        Assert.assertNull(hashTable.get(1L));
        hashTable.close();
    }

    @Test
    public void removeWithCollision() {
        HashTable<Long, Long> hashTable = new HashTableImpl(10, noHash);
        hashTable.put(1L, 32L);
        hashTable.put(2L, 33L);
        hashTable.put(3L, 34L);
        Assert.assertEquals(hashTable.get(1L), Long.valueOf(32));
        Assert.assertEquals(hashTable.get(2L), Long.valueOf(33));
        Assert.assertEquals(hashTable.get(3L), Long.valueOf(34));
        Assert.assertEquals(hashTable.remove(2L), Long.valueOf(33));
        Assert.assertNull(hashTable.get(2L));
        Assert.assertEquals(hashTable.get(1L), Long.valueOf(32));
        Assert.assertEquals(hashTable.get(3L), Long.valueOf(34));
        hashTable.close();
    }

    @Test
    public void removeAtBeginning() {
        HashTable<Long, Long> hashTable = new HashTableImpl(4, noHash);
        hashTable.put(3L, 3L);
        hashTable.put(2L, 2L);
        hashTable.put(4L, 4L);
        hashTable.put(1L, 1L);
        Assert.assertEquals(hashTable.get(1L), Long.valueOf(1));
        Assert.assertEquals(hashTable.get(2L), Long.valueOf(2));
        Assert.assertEquals(hashTable.get(3L), Long.valueOf(3));
        Assert.assertEquals(hashTable.get(4L), Long.valueOf(4));
        hashTable.remove(1L);
        Assert.assertNull(hashTable.get(1L));
        hashTable.close();
    }
}
