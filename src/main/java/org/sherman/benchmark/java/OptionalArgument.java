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
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@Fork(2)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class OptionalArgument {
    private final Arguments arguments = new Arguments();
    private final Random random = new Random();
    private static final String CONSTANT = "I'm really happy to do this test!";

    @Benchmark
    public void testIntegerArg(Blackhole blackhole) {
        int value = random.nextInt(2);
        blackhole.consume(value == 0 ? null : arguments.nullableSimple(value));
    }

    @Benchmark
    public void testIntegerOptional(Blackhole blackhole) {
        int value = random.nextInt(2);
        blackhole.consume(value == 0 ? Optional.empty() : arguments.optionalSimple(Optional.of(value)));
    }

    @Benchmark
    public void testComplexArg(Blackhole blackhole) {
        int value = random.nextInt(2);
        blackhole.consume(value == 0 ? null : arguments.nullableComplex(CONSTANT));
    }

    @Benchmark
    public void testComplexOptional(Blackhole blackhole) {
        int value = random.nextInt(2);
        blackhole.consume(value == 0 ? Optional.empty() : arguments.optionalComplex(Optional.of(CONSTANT)));
    }

    private static class Arguments {
        public int nullableSimple(Integer arg) {
            if (arg != null) {
                return arg;
            } else {
                return -2;
            }
        }

        public int optionalSimple(Optional<Integer> arg) {
            return arg.orElse(-2);
        }

        public int nullableComplex(String arg) {
            if (arg != null) {
                return arg.length();
            } else {
                return 0;
            }
        }

        public int optionalComplex(Optional<String> arg) {
            return arg.map(String::length).orElse(0);
        }
    }
}
