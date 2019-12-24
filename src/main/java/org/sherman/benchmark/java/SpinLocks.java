package org.sherman.benchmark.java;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.sherman.interview.java.SimpleLock;
import org.sherman.interview.java.TestAndSetLock;
import org.sherman.interview.java.TestAndTestAndSetLock;

@Fork(2)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class SpinLocks {

    private SimpleLock testAndSetLock = new TestAndSetLock();
    private SimpleLock testAndTestAndSetLock = new TestAndTestAndSetLock();

    @Benchmark
    public void testAndSetLock(Blackhole blackhole) {
        testAndSetLock.lock();
        try {
            blackhole.consume(42L);
        } finally {
            testAndSetLock.unlock();
        }
    }

    @Benchmark
    public void testAndTestAndSetLock(Blackhole blackhole) {
        testAndTestAndSetLock.lock();
        try {
            blackhole.consume(42L);
        } finally {
            testAndTestAndSetLock.unlock();
        }
    }
}
