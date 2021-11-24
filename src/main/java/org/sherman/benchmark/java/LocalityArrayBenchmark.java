package org.sherman.benchmark.java;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Fork(2)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class LocalityArrayBenchmark {
    private final Logger logger = LoggerFactory.getLogger(LocalityArrayBenchmark.class);
    private long[] data1 = new long[1_000_000];
    private long[] data2 = new long[1_000_000];
    private long[] data3 = new long[1_000_000];

    @Setup
    public void setup() {
        for (int i = 0; i < 1_000_000; i++) {
            data1[i] = i;
            data2[i] = i;
            data3[i] = i;
        }

        Arrays.sort(data2);
        shuffleArray(data3);
    }

    @Benchmark
    public void baseline(Blackhole blackhole) {
        var sum = 0L;
        for (var item : data1) {
            sum += item;
        }
        blackhole.consume(sum);
    }

    @Benchmark
    public void sort(Blackhole blackhole) {
        var sum = 0L;
        for (var item : data2) {
            sum += item;
        }
        blackhole.consume(sum);
    }

    @Benchmark
    public void shuffle(Blackhole blackhole) {
        var sum = 0L;
        for (var item : data3) {
            sum += item;
        }
        blackhole.consume(sum);
    }

    private static void shuffleArray(long[] array) {
        int index;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            if (index != i) {
                array[index] ^= array[i];
                array[i] ^= array[index];
                array[index] ^= array[i];
            }
        }
    }
}
