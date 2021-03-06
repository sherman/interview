package org.sherman.benchmark.java;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.sherman.interview.java.cache.HashTable;
import org.sherman.interview.java.cache.HashTableImpl;
import org.sherman.interview.java.cache.HashTableLongLong;
import org.sherman.interview.java.cache.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Fork(2)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class HashTableBenchmark {
    private static final int SIZE = 1024;
    private static final Function<Object, Integer> func = Utils::hash;
    private final HashTable<Long, Long> hashTable = new HashTableImpl(SIZE, func);
    private final HashTableLongLong hashTableNoAlloc = new HashTableLongLong(SIZE);
    private final HashTableLongLong hashTableFilledNoAlloc = new HashTableLongLong(SIZE);
    private final Map<Long, Long> baseLine = new HashMap<>();
    private final long[] values = new long[SIZE];
    private final Random random = new Random();
    private final static long VALUE = 42L;

    @Setup
    public void generate() {
        for (int i = 0; i < SIZE; i++) {
            values[i] = random.nextInt(SIZE / 4);
            if (values[i] == 0) {
                values[i] = 1;
            }

            hashTableFilledNoAlloc.put(i + 1, values[i]);
        }
    }

    @Benchmark
    public void putBaseLine(Blackhole blackhole) {
        baseLine.put(values[ThreadLocalRandom.current().nextInt(SIZE)], VALUE);
    }

    @Benchmark
    public void put(Blackhole blackhole) {
        hashTable.put(values[ThreadLocalRandom.current().nextInt(SIZE)], VALUE);
    }

    @Benchmark
    public void putNoAlloc(Blackhole blackhole) {
        hashTableNoAlloc.put(values[ThreadLocalRandom.current().nextInt(SIZE)], VALUE);
    }

    //@Benchmark
    public void getNoAlloc(Blackhole blackhole) {
        blackhole.consume(hashTableFilledNoAlloc.get(ThreadLocalRandom.current().nextInt(SIZE)));
    }
}
