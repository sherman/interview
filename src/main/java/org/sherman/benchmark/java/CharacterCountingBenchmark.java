package org.sherman.benchmark.java;

import jdk.incubator.vector.ByteVector;
import jdk.incubator.vector.VectorOperators;
import jdk.incubator.vector.VectorSpecies;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static jdk.incubator.vector.VectorOperators.EQ;

@Fork(2)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class CharacterCountingBenchmark {
    private static final VectorSpecies<Byte> SPECIES = ByteVector.SPECIES_256;

    private final Logger logger = LoggerFactory.getLogger(CharacterCountingBenchmark.class);
    private byte[] data;

    @Setup(Level.Iteration)
    public void generateData() {
        Random r = new Random();
        data = new byte[256];
        for (int i = 0; i < 256; i++) {
            data[i] = (byte) (r.nextInt(26) + 'a');
        }
    }

    @Benchmark
    public void countChar(Blackhole blackhole) {
        int c = 'a';
        int total = 0;
        for (int i = 0; i < 256; i++) {
            if (data[i] == c) {
                ++total;
            }
        }

        blackhole.consume(total);
    }

    @Benchmark
    public void countCharVector(Blackhole blackhole) {
        int c = 'a';
        int total = 0;
        var bound = SPECIES.loopBound(data.length);

        for (int i = 0 ;i < bound; i += SPECIES.length()) {
            var chunk = ByteVector.fromArray(SPECIES, data, i);
            var result = chunk.compare(EQ, c);
            total += result.toVector().abs().reduceLanesToLong(VectorOperators.ADD);
        }

        blackhole.consume(total);
    }
}
