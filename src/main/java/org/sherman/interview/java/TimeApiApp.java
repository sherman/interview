package org.sherman.interview.java;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.InstantSource;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeApiApp {
    private static final String BINARY_LONG_ALL_ZEROS = "0".repeat(Long.SIZE);

    private static final Logger logger = LoggerFactory.getLogger(TimeApiApp.class);

    public static void main(String[] args) throws InterruptedException {
        var clock = new HybridClock(System.currentTimeMillis() * 1_000_000);

        for (var i = 0; i < 50000; i++) {
            var now = clock.now();
            TimeUnit.MICROSECONDS.sleep(100);
            //Thread.sleep(1);

            if (i == 10000) {
                //clock.adjust(250000000);
            }
            //logger.info("T: [{}]", toBinaryString(now & 0xFFFFFFFFFFFFF000L));
        }
    }

    public static String toBinaryString(long value) {
        return value == 0
            ? BINARY_LONG_ALL_ZEROS
            : "0".repeat(Long.numberOfLeadingZeros(value)) + Long.toBinaryString(value);
    }

    private static class HybridClock {
        private long lastTimestamp = -1;
        private Clock microseondsClock = Clock.tick(Clock.systemUTC(), Duration.ofNanos(1000));
        private int counter = 0;

        public HybridClock(long lastTimestamp) {
            this.lastTimestamp = lastTimestamp;
        }

        public void adjust(long nanos) {
            microseondsClock = Clock.offset(
                microseondsClock,
                Duration.ofNanos(nanos)
            );
        }

        public long now() {
            var now = microseondsClock.instant();
            var nanos = Duration.ofSeconds(now.getEpochSecond()).toNanos() + now.getNano();
            nanos = nanos & 0xFFFFFFFFFFFF0000L;
            if (lastTimestamp >= nanos) {
                lastTimestamp++;
                nanos = lastTimestamp;
                logger.info("Incremented: [{}] [{}]", lastTimestamp, counter);
                logger.info("T: [{}] [{}]", nanos, toBinaryString(nanos & 0xFFFFFFFFFFFF0000L));
                counter++;
            } else {
                lastTimestamp = nanos;
                counter = 0;
            }
            return nanos;
        }
    }
}
