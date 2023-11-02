package org.sherman.benchmark.java;

import com.google.common.primitives.Ints;
import one.nio.util.JavaInternals;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import sun.misc.Unsafe;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static com.google.common.primitives.Ints.fromBytes;

@Fork(2)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class ReadPrimitiveBenchmark {
    private static final int SIZE = 1 << 20;
    private static final VarHandle varHandle = MethodHandles.byteArrayViewVarHandle(int[].class, ByteOrder.nativeOrder());
    private final byte[] data = new byte[SIZE];
    private final Unsafe unsafe = JavaInternals.getUnsafe();
    private final ByteBuffer byteBuffer = ByteBuffer.wrap(data);
    private int intIndex = 0;
    private long indexAsLongIndex = 0L;

    @Setup
    public void generate() {
        for (int i = 0; i < SIZE / 4; i++) {
            var value = Ints.toByteArray(i);
            for (var b : value) {
                data[i] = b;
            }
        }

        var index = ThreadLocalRandom.current().nextInt((SIZE / 4) - 32);
        intIndex = index;
        indexAsLongIndex = index;
    }

    @Benchmark
    public void readArray(Blackhole blackhole) {
        var index = intIndex;
        blackhole.consume(
            fromBytes(
                data[index],
                data[index + 1],
                data[index + 2],
                data[index + 3]
            ));
    }

    @Benchmark
    public void readByteBuffer(Blackhole blackhole) {
        blackhole.consume(byteBuffer.getInt(intIndex));
    }

    @Benchmark
    public void readMethodHandle(Blackhole blackhole) {
        blackhole.consume((int) varHandle.get(data, intIndex));
    }

    @Benchmark
    public void readUnsafe(Blackhole blackhole) {
        blackhole.consume(unsafe.getInt(data, indexAsLongIndex));
    }
}
