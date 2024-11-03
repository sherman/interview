package org.sherman.benchmark.java;

import static com.google.common.primitives.Ints.fromBytes;

import com.google.common.base.Preconditions;
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
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Unsafe;

@Fork(2)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class ReadPrimitiveBenchmark {
    private static final Logger logger = LoggerFactory.getLogger(ReadPrimitiveBenchmark.class);

    private static final int SIZE = 1 << 20;
    private static final VarHandle varHandle = MethodHandles.byteArrayViewVarHandle(int[].class, ByteOrder.BIG_ENDIAN);

    private final byte[] data = new byte[SIZE];
    private final int[] baseline = new int[SIZE / 4];
    private final Unsafe unsafe = JavaInternals.getUnsafe();
    private final ByteBuffer byteBuffer = ByteBuffer.wrap(data);
    private int intIndex = 0;
    private long indexAsLongIndex = 0L;

    private int baseLineIndex = 0;

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
        if (index + 31 >= indexes.length) {
            index = indexes.length - 31;
        }
        intIndex = indexes[index];
        indexAsLongIndex = indexes[index];
        baseLineIndex = index;
    }

    @OperationsPerInvocation(31)
    @Benchmark
    public void readBaseline(Blackhole blackhole) {
        var sum = 0;
        var start = baseLineIndex;
        var end = start + 31;
        for (var i = start; i < end; i++) {
            sum += baseline[i];
        }
        blackhole.consume(sum);
    }

    @OperationsPerInvocation(31)
    @Benchmark
    public void readArray(Blackhole blackhole) {
        var sum = 0;
        var start = intIndex;
        var end = start + 31;
        for (var i = start; i < end; i++) {
            sum += fromBytes(
                data[i],
                data[i + 1],
                data[i + 2],
                data[i + 3]
            );
        }
        blackhole.consume(sum);
    }

    @OperationsPerInvocation(31)
    @Benchmark
    public void readByteBuffer(Blackhole blackhole) {
        var sum = 0;
        var start = intIndex;
        var end = start + 31;
        for (var i = start; i < end; i++) {
            sum += byteBuffer.getInt(i);
        }
        blackhole.consume(sum);
    }

    @OperationsPerInvocation(31)
    @Benchmark
    public void readMethodHandle(Blackhole blackhole) {
        var sum = 0;
        var start = intIndex;
        var end = start + 31;
        for (var i = start; i < end; i++) {
            sum += (int) varHandle.get(data, i);
        }
        blackhole.consume(sum);
    }

    @OperationsPerInvocation(31)
    @Benchmark
    public void readUnsafe(Blackhole blackhole) {
        var sum = 0L;
        var start = indexAsLongIndex;
        var end = start + 31L;
        for (var i = start; i < end; i++) {
            sum += unsafe.getInt(data, i);
        }
        blackhole.consume(sum);
    }
}
