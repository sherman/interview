package org.sherman.interview.misc;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

/**
 * @author Denis Gabaydulin
 * @since 16.04.17
 */
public class Randomizers {
    private static final Random random = new Random(42);

    private Randomizers() {
    }

    public static int random1_6() {
        return random.nextInt(6) + 1;
    }

    public static class WeightedRandomIterator implements Iterable<Integer> {
        private static final Logger log = LoggerFactory.getLogger(WeightedRandomIterator.class);
        private final Map<Integer, Integer> weights;
        private TreeMap<Integer, Integer> scaled = new TreeMap<>();
        private int sum = 0;

        public WeightedRandomIterator(Map<Integer, Integer> weights) {
            this.weights = weights;

            init();
        }

        private void init() {
            for (Integer weight : weights.keySet()) {
                sum += weight;
            }

            int current = 0;
            for (Integer weight : weights.keySet()) {
                double v = (double) weight / sum;
                current = current + (int) Math.round(v * 100);
                scaled.put(current, weights.get(weight));
            }

            log.info("{}", scaled);
        }

        @NotNull
        @Override
        public Iterator<Integer> iterator() {
            ThreadLocalRandom r = ThreadLocalRandom.current();

            return new Iterator<Integer>() {
                @Override
                public boolean hasNext() {
                    return true;
                }

                @Override
                public Integer next() {
                    int randomIndex = r.nextInt(0, 100);
                    Integer index = scaled.higherKey(randomIndex);
                    //log.info("{}", randomIndex);
                    return scaled.get(index != null ? index : scaled.lastKey());
                }
            };
        }
    }
}
