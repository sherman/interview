package org.sherman.interview.misc;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.beust.jcommander.internal.Lists;
import com.google.common.collect.ImmutableRangeMap;
import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class ProbabilityFun {
    private static final Logger log = LoggerFactory.getLogger(ProbabilityFun.class);

    private final Random random = new Random();

    @Test
    public void basicProbabilities() {
        Map<Color, Integer> conditions = new HashMap<>();
        conditions.put(Color.BLUE, 2);
        conditions.put(Color.RED, 1);
        conditions.put(Color.WHITE, 1);

        ImmutableRangeMap.Builder<Integer, Color> probabilities = ImmutableRangeMap.builder();

        int total = 0;
        int lastWeight = 0;
        for (Map.Entry<Color, Integer> condition : conditions.entrySet()) {
            total += condition.getValue();
            probabilities.put(Range.closedOpen(lastWeight, total), condition.getKey());
            lastWeight = total;
        }

        RangeMap<Integer, Color> probability = probabilities.build();

        log.info("{}", probability);

        Map<Color, Integer> stat = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            int weight = random.nextInt(total);
            Color randomColor = probability.get(weight);
            stat.putIfAbsent(randomColor, 0);
            stat.put(randomColor, stat.get(randomColor) + 1);
        }

        log.info("{}", stat);
    }

    @Test
    public void probabilityGettingSecondBlue() {
        List<Color> items = Lists.newArrayList(Color.BLUE, Color.BLUE, Color.RED, Color.WHITE);

        Map<Boolean, Integer> stat = new HashMap<>();
        for (int i = 0; i < 1000000; i++) {
            Collections.shuffle(items);
            int index1 = random.nextInt(items.size());
            int index2 = random.nextInt(items.size());
            Color color1 = items.get(index1);
            Color color2 = items.get(index2);

            if (color1 != color2 || color1 != Color.BLUE) {
                stat.putIfAbsent(false, 0);
                stat.put(false, stat.get(false) + 1);
            } else {
                stat.putIfAbsent(true, 0);
                stat.put(true, stat.get(true) + 1);
            }
        }

        log.info("{}", stat);
    }

    private enum Color {
        BLUE,
        RED,
        WHITE
    }
}
