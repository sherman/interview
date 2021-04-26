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

    /**
     * avgt   10  2436.728 ±  55.429  MB/sec
     * @param blackhole
     */
    @Benchmark
    public void mapToIntArray1(Blackhole blackhole) {
        int[] result = in.stream()
            .mapToInt(x -> x)
            .toArray();

        blackhole.consume(result);
    }

    /**
     * avgt   10  2969.175 ±  31.942  MB/sec
     * @param blackhole
     */
    @Benchmark
    public void mapToIntArray2(Blackhole blackhole) {
        Integer[] result = in.toArray(Integer[]::new);
        blackhole.consume(result);
    }
}
