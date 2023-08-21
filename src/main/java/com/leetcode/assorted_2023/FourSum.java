package com.leetcode.assorted_2023;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

public class FourSum {
    private static final Logger logger = LoggerFactory.getLogger(FourSum.class);

    public static List<List<Integer>> fourNumberSum(int[] array, long targetSum) {
        if (array.length < 4) {
            return List.of();
        }

        var pairs = new HashMap<Long, Set<Pair>>();
        var counts = new HashMap<Integer, Integer>();

        for (var i = 0; i < array.length; i++) {
            var count = counts.getOrDefault(array[i], 0);
            counts.put(array[i], count + 1);

            for (var j = 0; j < array.length; j++) {
                if (i != j) {
                    long sum = array[i] + array[j];
                    var candidates = pairs.computeIfAbsent(sum, ignored -> new HashSet<>());
                    var pair = array[i] >= array[j] ? new Pair(array[j], array[i]) : new Pair(array[i], array[j]);
                    candidates.add(pair);
                }
            }
        }

        var result = new TreeSet<Four>();

        for (var entry : pairs.entrySet()) {
            logger.info("[{}]: [{}]", entry.getKey(), entry.getValue());
            var a = entry.getKey();
            var b = pairs.get(targetSum - a);
            var leftValue = entry.getValue();
            if (b != null) {
                for (var e : leftValue) {
                    for (var ee : b) {
                        var r = new int[4];
                        r[0] = e.a;
                        r[1] = e.b;
                        r[2] = ee.a;
                        r[3] = ee.b;
                        Arrays.sort(r);
                        Map<Integer, Integer> c = (Map<Integer, Integer>) counts.clone();
                        var invalid = false;
                        for (var element : r) {
                            if (!invalid) {
                                var current = c.get(element);
                                if (current - 1 >= 0) {
                                    c.put(element, current - 1);
                                } else {
                                    invalid = true;
                                }
                            }
                        }
                        if (!invalid) {
                            result.add(Four.fromArray(r));
                        }
                    }
                }
            }
        }

        for (var x : result) {
            logger.info("[{}]", x);
        }

        var res = new ArrayList<List<Integer>>();
        for (var r : result) {
            res.add(r.toList());
        }
        return res;
    }

    private record Pair(int a, int b) {
    }

    private record Four(int a, int b, int c, int d) implements Comparable<Four> {
        public static Four fromArray(int[] r) {
            return new Four(r[0], r[1], r[2], r[3]);
        }

        @Override
        public int compareTo(@NotNull Four o) {
            return Comparator.comparingInt(Four::a)
                .thenComparingInt(Four::b)
                .thenComparingInt(Four::c)
                .thenComparingInt(Four::d)
                .compare(this, o);
        }

        public Integer[] toArray() {
            return new Integer[]{a, b, c, d};
        }

        public List<Integer> toList() {
            return List.of(a, b, c, d);
        }
    }

    @Test
    public void test() {
        /*var result = fourNumberSum(new int[]{1000000000, 1000000000, 1000000000, 1000000000}, -294967296);
        Assert.assertEquals(
            List.of(),
            result
        );*/

        var result2 = fourNumberSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
        Assert.assertEquals(
            List.of(
                List.of(-2, -1, 1, 2),
                List.of(-2, 0, 0, 2),
                List.of(-1, 0, 0, 1)
            ),
            result2
        );

        var result3 = fourNumberSum(new int[]{1, 1}, 2);
        Assert.assertEquals(
            List.of(),
            result3
        );
    }
}
