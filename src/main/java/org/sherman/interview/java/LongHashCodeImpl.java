package org.sherman.interview.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class LongHashCodeImpl {
    private static final Logger logger = LoggerFactory.getLogger(LongHashCodeImpl.class);

    @Test
    public void hashCodeTest() {
        LongValue v = new LongValue(-10000000);
        logger.info("h: {}", v.hashCode());
    }

    private static class LongValue {
        private final long value;

        private LongValue(long value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return (int) (value ^ value >>> 32);
        }
    }
}
