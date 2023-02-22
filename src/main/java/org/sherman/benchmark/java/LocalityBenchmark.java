package org.sherman.benchmark.java;

import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.longs.LongList;
import java.util.Random;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Fork(2)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class LocalityBenchmark {
    private final Logger logger = LoggerFactory.getLogger(LocalityBenchmark.class);
    private List<Long> data1 = new ArrayList<>(1_000_000);
    private List<Long> data2 = new ArrayList<>(1_000_000);
    private List<Long> data3 = new ArrayList<>(1_000_000);

    private LongList data4 = new LongArrayList();

    private Random r = new Random();

    @Setup
    public void setup() {
        for (long i = 0; i < 1_000_000L; i++) {
            var value = r.nextLong(Integer.MAX_VALUE);
            data1.add(value);
            data2.add(value);
            data3.add(value);
            data4.add(value);
        }

        Collections.sort(data4);
        Collections.shuffle(data3);
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
        for (var item : data4) {
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
}
