package org.sherman.benchmark.java;

import static com.google.common.primitives.Ints.fromBytes;

import com.google.common.primitives.Ints;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import one.nio.util.JavaInternals;
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
import sun.misc.Unsafe;

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
    private int ints = 0;
    private long intsAsLong = 0L;

    @Setup
    public void generate() {
        for (int i = 0; i < SIZE / 4; i++) {
            var value = Ints.toByteArray(i);
            for (var b : value) {
                data[i] = b;
            }
            ints++;
            intsAsLong++;
        }
    }

    @Benchmark
    public void readArray(Blackhole blackhole) {
        var index = ThreadLocalRandom.current().nextInt(ints);
        blackhole.consume(fromBytes(data[index], data[index + 1], data[index + 2], data[index + 3]));
    }

    @Benchmark
    public void readByteBuffer(Blackhole blackhole) {
        var index = ThreadLocalRandom.current().nextInt(ints);
        blackhole.consume(byteBuffer.getInt(index));
    }

    @Benchmark
    public void readMethodHandle(Blackhole blackhole) {
        var index = ThreadLocalRandom.current().nextInt(ints);
        blackhole.consume((int) varHandle.get(data, index));
    }

    @Benchmark
    public void readUnsafe(Blackhole blackhole) {
        long index = ThreadLocalRandom.current().nextLong(intsAsLong);
        blackhole.consume(unsafe.getInt(data, index));
    }
}
