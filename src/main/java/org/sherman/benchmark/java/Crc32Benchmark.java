package org.sherman.benchmark.java;

import java.util.concurrent.TimeUnit;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
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
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class Crc32Benchmark {
    private static final int SIZE = 1 << 20;
    private final byte[] data = new byte[SIZE];

    private final HashFunction hashFunction = Hashing.crc32();
    private Hasher hasher;

    @Setup
    public void generate() {
        for (int i = 0; i < SIZE; i++) {
            data[i] = '1';
        }

        hasher = hashFunction.newHasher();
    }

    @Benchmark
    public void putBytes(Blackhole blackhole) {
        hasher.putBytes(data);
        blackhole.consume(hasher.hash().asInt());
    }

    @Benchmark
    public void putByte(Blackhole blackhole) {
        for (int i = 0; i < data.length; i++) {
            hasher.putByte(data[i]);
        }
        blackhole.consume(hasher.hash().asInt());
    }
}
