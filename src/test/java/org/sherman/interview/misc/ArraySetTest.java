package org.sherman.interview.misc;

import org.sherman.benchmark.one.hash.ArraySet;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ArraySetTest {
    @Test
    public void putKey() {
        ArraySet set = new ArraySet(32);
        set.putKey(42);
        set.putKey(43);

        assertEquals(set.getKey(42), 10);
        assertEquals(set.getKey(43), 11);
        assertEquals(set.getKey(44), -1);
    }
}
