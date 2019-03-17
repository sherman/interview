package org.sherman.interview.misc;

import org.sherman.benchmark.one.hash.ArraySetHeapNoThread;
import org.sherman.benchmark.one.hash.ArraySetNoThread;
import org.sherman.benchmark.one.hash.Generators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ArraySetHeapNoThreadTest {
    private static final Logger log = LoggerFactory.getLogger(ArraySetHeapNoThreadTest.class);

    @Test
    public void putKey() {
        ArraySetHeapNoThread set = new ArraySetHeapNoThread(32);
        set.putKey(42);
        set.putKey(43);

        assertEquals(set.getKey(42), 10);
        assertEquals(set.getKey(43), 11);
        assertEquals(set.getKey(44), -1);
    }

    @Test
    public void putMoreKeys() {
        ArraySetHeapNoThread set = new ArraySetHeapNoThread(2048);
        for (long elt : Generators.generate(1024)) {
            set.putKey(elt);
        }

        log.info("{}", set.probes() / set.size());
    }
}
