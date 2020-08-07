package org.sherman.benchmark.java;

import java.util.Optional;
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
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class OptionalArgumentScalarization {
    private final Arguments arguments = new Arguments();
    private final Random random = new Random();
    private int[] values = new int[256];

    @Setup
    public void generate() {
        for (int i = 0; i < 256; i++) {
            values[i] = random.nextInt(256);
        }
    }

    @Benchmark
    public void testSimple(Blackhole blackhole) {
        for (int i = 0; i < 256; i++) {
            int value = values[i];
            blackhole.consume(value < 128 ? arguments.simple(null) : arguments.simple(value));
        }
    }

    @Benchmark
    public void testOptional(Blackhole blackhole) {
        for (int i = 0; i < 256; i++) {
            int value = values[i];
            blackhole.consume(value < 128 ? arguments.optional(Optional.empty()) : arguments.optional(Optional.of(value)));
        }
    }

    private static class Arguments {
        public int simple(Integer arg) {
            return arg != null ? arg * arg : -2;
        }

        public int optional(Optional<Integer> arg) {
            return arg.map(v -> v * v).orElse(-2);
        }
    }
}
