package org.sherman.benchmark.java;

import com.google.common.collect.ImmutableList;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Fork(2)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class ListBenchmark {
    @Benchmark
    public void jdkArray(Blackhole blackhole) {
        blackhole.consume(Arrays.asList(1));
    }

    @Benchmark
    public void immutableArray(Blackhole blackhole) {
        blackhole.consume(ImmutableList.of(1));
    }

    @Benchmark
    public void jdkImmutable(Blackhole blackhole) {
        blackhole.consume(List.of(1));
    }
}
