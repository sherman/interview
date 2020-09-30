package org.sherman.benchmark.java;

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
import org.sherman.interview.NativeFunctions;
import org.sherman.interview.misc.Fibonacci;

@Fork(2)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class NativeFunctionsBenchmark {
    private static final int SIZE = 1024;
    private final int[] values = new int[SIZE];
    private final Random random = new Random();

    @Setup
    public void generate() {
        for (int i = 0; i < SIZE; i++) {
            values[i] = random.nextInt(SIZE);
            if (values[i] == 0) {
                values[i] = 1;
            }
        }
    }

    //@Benchmark
    public void nativeAdd(Blackhole blackhole) {
        blackhole.consume(NativeFunctions.add(values[random.nextInt(SIZE)], values[random.nextInt(SIZE)]));
    }

    //@Benchmark
    public void add(Blackhole blackhole) {
        blackhole.consume(add(values[random.nextInt(SIZE)], values[random.nextInt(SIZE)]));
    }

    //@Benchmark
    public void nativeFibonacci(Blackhole blackhole) {
        blackhole.consume(NativeFunctions.fibonacci(values[random.nextInt(SIZE)]));
    }

    //@Benchmark
    public void fibonacci(Blackhole blackhole) {
        blackhole.consume(Fibonacci.getFibIterative(values[random.nextInt(SIZE)]));
    }

    @Benchmark
    public void nativeFibonacciBig(Blackhole blackhole) {
        blackhole.consume(NativeFunctions.fibonacci(10000));
    }

    @Benchmark
    public void fibonacciBig(Blackhole blackhole) {
        blackhole.consume(Fibonacci.getFibIterative(10000));
    }

    private static int add(int a, int b) {
        return a + b;
    }
}
