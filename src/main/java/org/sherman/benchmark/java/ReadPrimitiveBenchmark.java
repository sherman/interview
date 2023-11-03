package org.sherman.benchmark.java;

import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import one.nio.util.JavaInternals;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(ReadPrimitiveBenchmark.class);

    private static final int SIZE = 1 << 8;
    private static final VarHandle varHandle = MethodHandles.byteArrayViewVarHandle(int[].class, ByteOrder.BIG_ENDIAN);

    private final byte[] data = new byte[SIZE];
    private final int[] baseline = new int[SIZE / 4];
    private final Unsafe unsafe = JavaInternals.getUnsafe();
    private final ByteBuffer byteBuffer = ByteBuffer.wrap(data);
    private int intIndex = 0;
    private long indexAsLongIndex = 0L;

    private final int[] indexes = new int[SIZE / 4];

    @Setup
    public void generate() {
        var k = 0;
        var valueIterator = 0;
        for (int i = 0; i < SIZE; i += 4) {
            var value = Ints.toByteArray(valueIterator);
            for (var m = 0; m < 4; m++) {
                data[i + m] = value[m];
            }
            baseline[k] = valueIterator;
            indexes[k] = i;
            //logger.info("[{}] [{}]", baseline[k], valueIterator);
            valueIterator++;
            k++;
        }

        // check data
        k = 0;
        for (int i = 0; i < data.length; i += 4) {
            var expected = baseline[k];
            var vhVal = (int) varHandle.get(data, i);
            var val = Ints.fromBytes(data[i], data[i + 1], data[i + 2], data[i + 3]);

            Preconditions.checkState(vhVal == expected, "Invalid data " + i);
            Preconditions.checkState(val == expected, "Invalid data " + i);
            k++;
        }

        var index = ThreadLocalRandom.current().nextInt(indexes.length);
        intIndex = indexes[index];
        indexAsLongIndex = indexes[index];
    }

    @Benchmark
    public void readBaseline(Blackhole blackhole) {
        blackhole.consume(baseline[intIndex]);
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
            )
        );
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
