package org.sherman.benchmark.hash;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Generators {
    private static final Random r = new Random(42L);

    private Generators() {
    }

    public static List<Elt> generate(int n) {
        return LongStream.range(1, n + 1)
            .boxed()
            .map(l -> new Elt(l, r.nextLong(), r.nextLong(), r.nextLong(), r.nextLong()))
            .collect(Collectors.toList());
    }
}
