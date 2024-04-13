package org.sherman.interview.java;

import java.util.HashSet;
import org.sherman.java.minperf.BDZAlgorithm;
import org.sherman.java.minperf.universal.LongHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BZDMain {
    private static final Logger logger = LoggerFactory.getLogger(BZDMain.class);

    public static void main(String[] args) {
        var values = new HashSet<Long>();
        for (var i = 1L; i < 10_000_000; i++) {
            values.add(i);
        }
        var hash = new LongHash();
        var data = BDZAlgorithm.generate(hash, values);
        int bitCount = data.position();
        data.seek(0);
        var bdz = BDZAlgorithm.load(hash, data);
        logger.info("Get size: [{}]", bdz.getSize());

        /*assertEquals(bitCount, data.position());
        var test = new BitSet();
        for (long x : values) {
            var key = bdz.evaluate(x);
            assertTrue(key >= 0 && key < 10_000_000);
            assertFalse(test.get(key));
            test.set(key);
            logger.info("[{}]", key);
        }*/
        /*logger.info("Unknown key: [{}]: [{}]", bdz.evaluate(1026L), test.get(bdz.evaluate(1026L)));
        logger.info("Unknown key: [{}]: [{}]", bdz.evaluate(1027L), test.get(bdz.evaluate(1027L)));
        int measureCount = 10;
        for (int i = 0; i < measureCount; i++) {
            for (Long x : values) {
                int index = bdz.evaluate(x);
                if (index > values.size() || index < 0) {
                    Assert.fail("wrong entry: " + x + " " + index);
                }
            }
        }*/
    }
}
