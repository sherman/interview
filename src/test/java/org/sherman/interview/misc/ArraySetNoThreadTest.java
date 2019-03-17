package org.sherman.interview.misc;

import org.sherman.benchmark.one.hash.ArraySet;
import org.sherman.benchmark.one.hash.ArraySetNoThread;
import org.sherman.benchmark.one.hash.Generators;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ArraySetNoThreadTest {
    @Test
    public void putKey() {
        ArraySetNoThread set = new ArraySetNoThread(32);
        set.putKey(42);
        set.putKey(43);

        assertEquals(set.getKey(42), 10);
        assertEquals(set.getKey(43), 11);
        assertEquals(set.getKey(44), -1);
    }

    @Test
    public void putMoreKeys() {
        ArraySetNoThread set = new ArraySetNoThread(1400);
        for (long elt : Generators.generate(1200)) {
            set.putKey(elt);
        }
    }
}
