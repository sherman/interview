package org.sherman.benchmark.one.nalim;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@Fork(2)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class MathBenchmark {
    private final Random r = new Random();
    private static final int SIZE = 1024;
    private final int[] data = new int[SIZE];

    @Setup
    public void generate() {
        for (int i = 0; i < SIZE; i++) {
            data[i] = i;
        }
    }

    @Benchmark
    public void javaSquare(Blackhole blackhole) {
        for (var i = 0; i < data.length; i++) {
            blackhole.consume(square(data[i]));
        }
    }

    @Benchmark
    public void nalimSquare(Blackhole blackhole) {
        for (var i = 0; i < data.length; i++) {
            blackhole.consume(Math.square(data[i]));
        }
    }

    private static int square(int value) {
        return value * value;
    }
}
