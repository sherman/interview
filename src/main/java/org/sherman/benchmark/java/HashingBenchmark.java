package org.sherman.benchmark.java;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Fork(2)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class HashingBenchmark {
    private final HashFunction sha256 = Hashing.sha256();
    private final HashFunction crc = Hashing.crc32();

    private static final int SIZE = 10;
    private final byte[] data = new byte[SIZE];
    private final String[] dataStr = new String[SIZE];

    @Setup
    public void generate() {
        Random r = new Random();
        for (int i = 0; i < SIZE; i++) {
            data[i] = (byte) (r.nextInt(26) + 'a');
            byte[] buffer = new byte[26];
            for (int k = 0; k < 20; k++) {
                buffer[k] = (byte) (r.nextInt(26) + 'a');
            }
            dataStr[i] = new String(buffer, StandardCharsets.UTF_8);
        }
    }

    //@Benchmark
    public void crc32(Blackhole blackhole) {
        Hasher hasher2 = crc.newHasher();
        hasher2.putBytes(data);
        blackhole.consume(hasher2.hash().asInt());
    }

    //@Benchmark
    public void sha256(Blackhole blackhole) {
        Hasher hasher1 = sha256.newHasher();
        hasher1.putBytes(data);
        blackhole.consume(hasher1.hash().asInt());
    }

    @Benchmark
    public void crc32Str(Blackhole blackhole) {
        Hasher hasher2 = crc.newHasher();
        hasher2.putBytes(dataStr[ThreadLocalRandom.current().nextInt(SIZE)].getBytes());
        blackhole.consume(hasher2.hash().asInt());
    }

    @Benchmark
    public void sha256Str(Blackhole blackhole) {
        Hasher hasher1 = sha256.newHasher();
        hasher1.putBytes(dataStr[ThreadLocalRandom.current().nextInt(SIZE)].getBytes());
        blackhole.consume(hasher1.hash().asInt());
    }


}
