package org.sherman.benchmark.java;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
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

@Fork(2)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class EmptyFunctionBenchmark {
    private static final int SIZE = 1024 * 1024;
    private static final SomeFunctionInterface interfaceImpl1 = new SomeFunctionInterfaceImpl1();
    private static final SomeFunctionInterface interfaceImpl2 = new SomeFunctionInterfaceImpl2(Hashing.murmur3_128());
    private static final SomeFunctionAbstract abstractImpl1 = new SomeFunctionAbstractImpl1();
    private static final SomeFunctionAbstract abstractImpl2 = new SomeFunctionAbstractImpl2(Hashing.murmur3_128());

    private final String[] data = new String[1024 * 1024];

    @Setup(Level.Iteration)
    public void generateData() {
        for (var i = 0; i < SIZE; i++) {
            data[i] = String.valueOf(i);
        }
    }

    //@Benchmark
    public void baseline(Blackhole blackhole) {
        for (var item : data) {
            if (true) {
                blackhole.consume(item);
            }
        }
    }

    //@Benchmark
    public void interfaceImpl1(Blackhole blackhole) {
        for (var item : data) {
            if (interfaceImpl1.predicate(item)) {
                blackhole.consume(item);
            }
        }
    }

    //@Benchmark
    public void interfaceImpl2(Blackhole blackhole) {
        for (var item : data) {
            if (interfaceImpl2.predicate(item)) {
                blackhole.consume(item);
            }
        }
    }

    //@Benchmark
    public void abstractImpl1(Blackhole blackhole) {
        for (var item : data) {
            if (abstractImpl1.predicate(item)) {
                blackhole.consume(item);
            }
        }
    }

    @Benchmark
    public void abstractImpl2(Blackhole blackhole) {
        for (var item : data) {
            if (abstractImpl2.predicate(item)) {
                blackhole.consume(item);
            }
        }
    }

    interface SomeFunctionInterface {
        boolean predicate(String value);
    }

    private static class SomeFunctionInterfaceImpl1 implements SomeFunctionInterface {
        @Override
        public boolean predicate(String value) {
            return true;
        }
    }

    private static class SomeFunctionInterfaceImpl2 implements SomeFunctionInterface {
        private final HashFunction hashFunction;

        private SomeFunctionInterfaceImpl2(HashFunction hashFunction) {
            this.hashFunction = hashFunction;
        }

        @Override
        public boolean predicate(String value) {
            return Math.abs(hashFunction.newHasher().putBytes(value.getBytes()).hash().asLong()) % 4 == 0;
        }
    }

    private static class SomeFunctionAbstractImpl1 extends SomeFunctionAbstract {
        @Override
        public boolean predicate(String value) {
            return true;
        }
    }

    private static class SomeFunctionAbstractImpl2 extends SomeFunctionAbstract {
        private final HashFunction hashFunction;

        private SomeFunctionAbstractImpl2(HashFunction hashFunction) {
            this.hashFunction = hashFunction;
        }

        @Override
        public boolean predicate(String value) {
            return Math.abs(hashFunction.newHasher().putBytes(value.getBytes()).hash().asLong()) % 4 == 0;
        }
    }

    abstract static class SomeFunctionAbstract {
        abstract public boolean predicate(String value);
    }
}
