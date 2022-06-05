package org.sherman.benchmark.java;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.sherman.interview.vector.ArrayUtils;

@Fork(2)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class BasicSIMDOperations {
    private int[] data = new int[1024];

    @Setup(Level.Trial)
    public void generateData() {
        for (var i = 0; i < data.length; i++) {
            data[i] = ThreadLocalRandom.current().nextInt(1_000);
        }
    }

    @Benchmark
    public void sumScalar(Blackhole blackhole) {
        long sum = 0;
        for (int i = 0; i < data.length; i++) {
            sum += data[i];
        }
        blackhole.consume(sum);
    }

    @Benchmark
    public void sumVector(Blackhole blackhole) {
        blackhole.consume(ArrayUtils.sumVector(data));
    }
}
