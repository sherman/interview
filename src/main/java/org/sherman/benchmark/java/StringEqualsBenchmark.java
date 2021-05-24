package org.sherman.benchmark.java;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Fork(2)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class StringEqualsBenchmark {
    @Param({"4", "40", "400", "4000"})
    private int length;

    String data1;
    String data2;

    @Setup(Level.Iteration)
    public void generateData() {
        Random r = new Random();
        char[] chars1 = new char[length];
        char[] chars2 = new char[length];
        for (int i = 0; i < length; i++) {
            chars1[i] = (char) (r.nextInt(26) + 'a');
            chars2[i] = (char) (r.nextInt(26) + 'a');
        }
        data1 = new String(chars1);
        data2 = new String(chars1);
    }

    @Benchmark
    public void equals(Blackhole blackhole) {
        blackhole.consume(data1.equals(data2));
    }

    @Benchmark
    public void equalsIgnoreCase(Blackhole blackhole) {
        blackhole.consume(data1.equalsIgnoreCase(data2));
    }


}
