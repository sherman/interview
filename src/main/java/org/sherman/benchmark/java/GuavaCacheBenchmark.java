package org.sherman.benchmark.java;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import joptsimple.internal.Strings;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Fork(2)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class GuavaCacheBenchmark {
    private final Cache<String, Long> defaultCache = CacheBuilder
        .newBuilder()
        .maximumSize(10000)
        .concurrencyLevel(4)
        .build();
    private final Cache<String, Long> concurrentCache = CacheBuilder.newBuilder()
        .maximumSize(10000)
        .concurrencyLevel(8)
        .build();

    private final String[] words = new String[50000];

    @Setup
    public void generate() {
        for (int i = 0; i < words.length; i++) {
            var key = Strings.repeat(
                (char) ThreadLocalRandom.current().nextInt(255),
                Math.max(1, ThreadLocalRandom.current().nextInt(30))
            );
            words[i] = key;
        }
    }

    @Benchmark
    public void defaultCache() {
        var key = words[ThreadLocalRandom.current().nextInt(words.length)];
        var value = defaultCache.getIfPresent(key);
        if (value == null) {
            defaultCache.put(key, 42L);
        }
    }

    @Benchmark
    public void concurrentCache() {
        var key = words[ThreadLocalRandom.current().nextInt(words.length)];
        var value = concurrentCache.getIfPresent(key);
        if (value == null) {
            concurrentCache.put(key, 42L);
        }
    }
}
