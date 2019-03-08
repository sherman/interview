package org.sherman.interview.misc;

import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Iterator;
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

    @Test
    public void weightedRandomGenerator() {
        Randomizers.WeightedRandomIterator iter = new Randomizers.WeightedRandomIterator(
            ImmutableMap.of(
                10, 1,
                22,2,
                33,3
            )
        );

        Map<Integer, Integer> distribution = new HashMap<>();
        distribution.put(1, 0);
        distribution.put(2, 0);
        distribution.put(3, 0);

        Iterator<Integer> iterable = iter.iterator();

        for (int i = 0; i < 1024; i++) {
            int v = iterable.next();
            //log.info("{}", v);
            distribution.put(v, distribution.get(v) + 1);
        }

        log.info("{}", distribution);
    }
}
