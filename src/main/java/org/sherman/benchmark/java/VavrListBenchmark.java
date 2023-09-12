package org.sherman.benchmark.java;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Fork(2)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class VavrListBenchmark {
    private List<Integer> baseline = new ArrayList<>();
    private io.vavr.collection.Array<Integer> vavrList = io.vavr.collection.Array.of();

    private final List<Integer> data = new ArrayList<>();

    @Setup
    public void setUp() {
        for (var i = 0; i < 50000; i++) {
            data.add(i);
        }
    }

    @Benchmark
    public void addVavr(Blackhole blackhole) {
        if (vavrList.size() == 0) {
            vavrList.appendAll(data);
        } else {
            vavrList = io.vavr.collection.Array.of();
        }
        blackhole.consume(vavrList);
    }

    @Benchmark
    public void addBaseline(Blackhole blackhole) {
        if (baseline.size() == 0) {
            baseline.addAll(data);
        } else {
            baseline = new ArrayList<>();
        }
        blackhole.consume(baseline);
    }
}
