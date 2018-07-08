package org.sherman.benchmark.one.hash;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Generators {
    private static final Random r = new Random(42L);

    private Generators() {
    }

    public static List<Long> generate(int n) {
        return LongStream.range(1, n + 1)
            .boxed()
            .map(l -> r.nextLong())
            .collect(Collectors.toList());
    }
}
