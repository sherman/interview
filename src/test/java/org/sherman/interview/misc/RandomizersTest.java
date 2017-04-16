package org.sherman.interview.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.sherman.interview.misc.Randomizers.random1_6;

/**
 * @author Denis Gabaydulin
 * @since 16.04.17
 */
public class RandomizersTest {
    private static final Logger log = LoggerFactory.getLogger(Randomizers.class);

    @Test
    public void exploreRandom1_6() {
        Map<Integer, AtomicInteger> probes = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            int v = random1_6();
            AtomicInteger tmp;
            AtomicInteger counter = probes.putIfAbsent(v, tmp = new AtomicInteger());
            if (counter == null) {
                counter = tmp;
            }
            counter.incrementAndGet();
        }

        log.info("{}", probes);
    }

    @Test
    public void exploreRandom1_2() {
        Map<Integer, AtomicInteger> probes = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            int v = random1_6() % 2;
            AtomicInteger tmp;
            AtomicInteger counter = probes.putIfAbsent(v, tmp = new AtomicInteger());
            if (counter == null) {
                counter = tmp;
            }
            counter.incrementAndGet();
        }

        log.info("{}", probes);
    }

    @Test
    public void exploreRandom1_12() {
        Map<Integer, AtomicInteger> probes = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            int v = random1_6() + 6 * (random1_6() % 2);
            AtomicInteger tmp;
            AtomicInteger counter = probes.putIfAbsent(v, tmp = new AtomicInteger());
            if (counter == null) {
                counter = tmp;
            }
            counter.incrementAndGet();
        }

        log.info("{}", probes);
    }
}
