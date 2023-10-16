package org.sherman.interview.java;

import com.google.common.collect.ImmutableSet;
import org.sherman.interview.java.minperf.BDZAlgorithm;
import org.sherman.interview.java.minperf.universal.LongHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.BitSet;
import java.util.stream.LongStream;

import static org.testng.Assert.*;

public class BZDMain {
    private static final Logger logger = LoggerFactory.getLogger(BZDMain.class);

    public static void main(String[] args) {
        var values = LongStream.range(1, 1025).boxed().collect(ImmutableSet.toImmutableSet());
        var hash = new LongHash();
        var data = BDZAlgorithm.generate(hash, values);
        int bitCount = data.position();
        data.seek(0);
        var bdz = BDZAlgorithm.load(hash, data);
        assertEquals(bitCount, data.position());
        var test = new BitSet();
        for (long x : values) {
            var key = bdz.evaluate(x);
            assertTrue(key >= 0 && key < 1025);
            assertFalse(test.get(key));
            test.set(key);
            logger.info("[{}]", key);
        }
        int measureCount = 10;
        for (int i = 0; i < measureCount; i++) {
            for (Long x : values) {
                int index = bdz.evaluate(x);
                if (index > values.size() || index < 0) {
                    Assert.fail("wrong entry: " + x + " " + index);
                }
            }
        }
    }
}
