package org.sherman.interview.java.cache;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class HashTableLongLongTest {
    private static final Logger logger = LoggerFactory.getLogger(HashTableLongLongTest.class);

    private final Function<Object, Integer> func = Utils::hash;
    private final Function<Object, Integer> noHash = a -> 1;

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void storeZeroKey() {
        HashTableLongLong hashTable = new HashTableLongLong(4);
        hashTable.put(0L, 32L);
    }

    @Test
    public void singleEntry() {
        HashTableLongLong hashTable = new HashTableLongLong(1);
        hashTable.put(1L, 32L);
        Assert.assertEquals(hashTable.get(1L), 32L);
        Assert.assertEquals(hashTable.get(42L), -1L);
        hashTable.close();
    }

    @Test
    public void multipleEntries() {
        HashTableLongLong hashTable = new HashTableLongLong(4);
        hashTable.put(1L, 32L);
        hashTable.put(2L, 33L);
        hashTable.put(42L, 34L);
        Assert.assertEquals(hashTable.get(1L), 32L);
        Assert.assertEquals(hashTable.get(2L), 33L);
        Assert.assertEquals(hashTable.get(42L), 34L);
        hashTable.close();
    }

    @Test
    public void insertAtBeginning() {
        HashTableLongLong hashTable = new HashTableLongLong(4, false);
        hashTable.put(3L, 3L);
        hashTable.put(2L, 2L);
        hashTable.put(4L, 4L);
        hashTable.put(1L, 1L);
        Assert.assertEquals(hashTable.get(1L), 1L);
        Assert.assertEquals(hashTable.get(2L), 2L);
        Assert.assertEquals(hashTable.get(3L), 3L);
        Assert.assertEquals(hashTable.get(4L), 4L);
        hashTable.close();
    }

    @Test
    public void update() {
        HashTableLongLong hashTable = new HashTableLongLong(1);
        hashTable.put(1L, 32L);
        Assert.assertEquals(hashTable.get(1L), 32L);
        hashTable.put(1L, 33L);
        Assert.assertEquals(hashTable.get(1L), 33L);
        hashTable.close();
    }

    @Test
    public void multipleUpdates() {
        HashTableLongLong hashTable = new HashTableLongLong(1);
        hashTable.put(1L, 32L);
        Assert.assertEquals(hashTable.get(1L), 32L);
        hashTable.remove(1L);
        hashTable.put(1L, 33L);
        Assert.assertEquals(hashTable.get(1L), 33L);
        hashTable.close();
    }

    @Test
    public void notEnoughSpace() {
        HashTableLongLong hashTable = new HashTableLongLong(1);
        hashTable.put(1L, 32L);
        Assert.assertEquals(hashTable.get(1L), 32L);
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
        HashTableLongLong hashTable = new HashTableLongLong(10);
        hashTable.put(1L, 32L);
        Assert.assertEquals(hashTable.get(1L), 32L);
        Assert.assertEquals(hashTable.remove(1L), 32L);
        Assert.assertEquals(hashTable.get(1L), -1L);
        hashTable.close();
    }

    @Test
    public void removeWithCollision() {
        HashTableLongLong hashTable = new HashTableLongLong(10, false);
        hashTable.put(1L, 32L);
        hashTable.put(2L, 33L);
        hashTable.put(3L, 34L);
        Assert.assertEquals(hashTable.get(1L), 32L);
        Assert.assertEquals(hashTable.get(2L), 33L);
        Assert.assertEquals(hashTable.get(3L), 34L);
        Assert.assertEquals(hashTable.remove(2L), 33L);
        Assert.assertEquals(hashTable.get(2L), -1L);
        Assert.assertEquals(hashTable.get(1L), 32L);
        Assert.assertEquals(hashTable.get(3L), 34L);
        hashTable.close();
    }

    @Test
    public void removeAtBeginning() {
        HashTableLongLong hashTable = new HashTableLongLong(4, false);
        hashTable.put(3L, 3L);
        hashTable.put(2L, 2L);
        hashTable.put(4L, 4L);
        hashTable.put(1L, 1L);
        Assert.assertEquals(hashTable.get(1L), 1L);
        Assert.assertEquals(hashTable.get(2L), 2L);
        Assert.assertEquals(hashTable.get(3L), 3L);
        Assert.assertEquals(hashTable.get(4L), 4L);
        hashTable.remove(1L);
        Assert.assertEquals(hashTable.get(1L), -1L);
        hashTable.close();
    }
}
