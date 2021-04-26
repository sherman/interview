package org.sherman.benchmark.java;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Fork(2)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class StreamBenchmark {
    private final int size = 1000;
    private List<Integer> in;

    @Setup
    public void setup() {
        in = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            in.add(i);
        }
    }

    @Benchmark
    public void mapToInt(Blackhole blackhole) {
        int[] result = in.stream()
            .mapToInt(x -> x)
            .toArray();

        blackhole.consume(result);
    }
}
