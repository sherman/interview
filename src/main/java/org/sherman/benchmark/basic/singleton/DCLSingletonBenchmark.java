package org.sherman.benchmark.basic.singleton;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * @author Denis Gabaydulin
 * @since 21.12.16
 */
@Fork(2)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class DCLSingletonBenchmark {

    private DCLSingleton singleton1;
    private Synchronized singleton2;

    @Setup
    public void setup() {
        singleton1 = new DCLSingleton();
        singleton2 = new Synchronized();
    }

    @Benchmark
    public Object DCL() {
        return singleton1.getInstance();
    }

    @Benchmark
    public Object sync() {
        return singleton2.getInstance();
    }

    public static class DCLSingleton {
        private volatile Object instance;

        public Object getInstance() {
            if (instance == null) {
                synchronized (this) {
                    if (instance == null) {
                        instance = new Object();
                    }
                }
            }
            return instance;
        }
    }

    public static class Synchronized {
        private Object instance;

        public Object getInstance() {
            synchronized (this) {
                if (instance == null) {
                    instance = new Object();
                }
                return instance;
            }
        }
    }
}
