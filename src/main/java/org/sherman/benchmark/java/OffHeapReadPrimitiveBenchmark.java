package org.sherman.benchmark.java;

import com.google.common.io.Files;
import com.google.common.primitives.Ints;
import one.nio.mem.MappedFile;
import one.nio.util.JavaInternals;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Unsafe;

import java.io.File;
import java.io.IOException;
import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static com.google.common.primitives.Ints.fromBytes;
import static java.lang.foreign.ValueLayout.JAVA_BYTE;

@Fork(2)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class OffHeapReadPrimitiveBenchmark {
    private static final Logger logger = LoggerFactory.getLogger(OffHeapReadPrimitiveBenchmark.class);

    private static final ValueLayout.OfByte LAYOUT = JAVA_BYTE;

    private static final VarHandle VAR_HANDLE = JAVA_BYTE.arrayElementVarHandle();

    private static final int SIZE = 1 << 20;
    private static final SequenceLayout SEQUENCE_LAYOUT = MemoryLayout.sequenceLayout(SIZE / 4, ValueLayout.JAVA_INT);
    private final byte[] data = new byte[SIZE];
    private static final Unsafe unsafe = JavaInternals.getUnsafe();
    private MemorySegment memorySegment;
    private MappedFile file;
    private long base;
    private long intsAsLong = 0;

    @Setup(Level.Trial)
    public void generate() throws IOException {
        var file = new File("/tmp/bytes_file");
        file.delete();
        var k = 0;
        for (int i = 0; i < SIZE / 4; i++) {
            var value = Ints.toByteArray(i);
            for (var b : value) {
                data[k] = b;
                k++;
            }
            intsAsLong++;
        }

        Files.write(data, file);
        this.file = new MappedFile(file.getAbsolutePath(), MappedFile.MAP_RO);
        var fc = FileChannel.open(file.toPath(), StandardOpenOption.READ);
        this.memorySegment = fc.map(FileChannel.MapMode.READ_ONLY, 0, file.length(), Arena.global());
        this.base = this.file.getAddr();
    }

    @TearDown(Level.Trial)
    public void closeResources() {
        file.close();
    }

    //@Benchmark
    public void readUnsafe(Blackhole blackhole) {
        /*for (var i = 0; i < SIZE; i++) {
            blackhole.consume(unsafe.getByte(base + i));
        }*/
        blackhole.consume(unsafe.getByte(base + 0));
    }

    @Benchmark
    public void readSegment(Blackhole blackhole) {
        var index = ThreadLocalRandom.current().nextLong(intsAsLong) * 4;
        var b1 = (byte) VAR_HANDLE.get(memorySegment, index);
        var b2 = (byte) VAR_HANDLE.get(memorySegment, index + 1);
        var b3 = (byte) VAR_HANDLE.get(memorySegment, index + 2);
        var b4 = (byte) VAR_HANDLE.get(memorySegment, index + 3);
        //logger.info("[{}]", fromBytes(b1, b2, b3, b4));
        blackhole.consume(fromBytes(b1, b2, b3, b4));
    }
}
