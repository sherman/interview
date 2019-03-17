package org.sherman.interview.misc;

import org.sherman.benchmark.one.hash.ArraySet;
import org.sherman.benchmark.one.hash.ArraySetNoThread;
import org.sherman.benchmark.one.hash.Generators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ArraySetNoThreadTest {
    private static final Logger log = LoggerFactory.getLogger(ArraySetNoThreadTest.class);

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
        ArraySetNoThread set = new ArraySetNoThread(2048);
        for (long elt : Generators.generate(1024)) {
            set.putKey(elt);
        }

        log.info("{}", set.probes() / set.size());
    }
}
