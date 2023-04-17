package org.sherman.benchmark.java;

import static java.lang.foreign.ValueLayout.JAVA_BYTE;

import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentScope;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import one.nio.mem.MappedFile;
import one.nio.util.JavaInternals;
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
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import sun.misc.Unsafe;

@Fork(2)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class OffHeapReadPrimitiveBenchmark {
    private static final ValueLayout.OfByte LAYOUT_BYTE = JAVA_BYTE;
    private static final int SIZE = 1 << 20;
    private final byte[] data = new byte[SIZE];
    private final long[] idx = new long[SIZE];
    private static final Unsafe unsafe = JavaInternals.getUnsafe();
    private final Random random = new Random();
    private MemorySegment memorySegment;
    private MappedFile file;
    private long base;

    @Setup(Level.Trial)
    public void generate() throws IOException {
        var file = new File("/tmp/bytes_file");
        file.delete();
        var idx = new ArrayList<Long>();
        for (var i = 0; i < data.length; i++) {
            data[i] = (byte) random.nextInt(255);
            idx.add((long) i);
        }

        Collections.shuffle(idx);
        var tmp = idx.stream().mapToLong(v -> v).toArray();
        System.arraycopy(tmp, 0, this.idx, 0, tmp.length);

        Files.write(data, file);
        this.file = new MappedFile(file.getAbsolutePath(), MappedFile.MAP_RO);
        var fc = FileChannel.open(file.toPath(), StandardOpenOption.READ);
        this.memorySegment = fc.map(FileChannel.MapMode.READ_ONLY, 0, file.length(), SegmentScope.global());
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
        blackhole.consume(memorySegment.get(LAYOUT_BYTE, 0L));
        /*for (var i = 0; i < SIZE; i++) {
            blackhole.consume(memorySegment.get(LAYOUT_BYTE, i));
        }*/
    }
}
