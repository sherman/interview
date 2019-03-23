package org.sherman.benchmark.one.hash;

import org.sherman.benchmark.one.memory.MemoryFragmentation;

import java.util.ArrayList;
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

    public static List<MemoryFragmentation.Value> generateValues(int n) {
        List<MemoryFragmentation.Value> values = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            MemoryFragmentation.Value v = new MemoryFragmentation.Value();
            v.v1 = r.nextLong();
            v.v2 = String.valueOf(r.nextLong());
            v.v3 = r.nextInt();
            v.v4 = r.nextBoolean();
            values.add(v);
        }

        return values;
    }
}
