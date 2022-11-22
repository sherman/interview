package org.sherman.benchmark.java;

import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import jdk.incubator.foreign.MemoryAccess;
import jdk.incubator.foreign.MemoryHandles;
import jdk.incubator.foreign.MemorySegment;
import jdk.incubator.foreign.ResourceScope;
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
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class OffheapReadPrimitiveBenchmark {
    private static final int SIZE = 1 << 20;
    private static final VarHandle varHandle = MemoryHandles.varHandle(byte.class, ByteOrder.nativeOrder());
    private final byte[] data = new byte[SIZE];
    private final Unsafe unsafe = JavaInternals.getUnsafe();
    private final Random random = new Random();
    private MemorySegment memorySegment;
    private MappedFile file;
    private int bytes = 0;
    private long base;

    @Setup(Level.Trial)
    public void generate() throws IOException {
        var file = new File("/tmp/bytes_file");
        file.delete();
        for (var i = 0; i < data.length; i++) {
            data[i] = (byte) random.nextInt(255);
            bytes++;
        }
        Files.write(data, file);
        this.file = new MappedFile(file.getAbsolutePath(), MappedFile.MAP_RO);
        this.memorySegment = MemorySegment.mapFile(
            file.toPath(),
            0,
            file.length(),
            FileChannel.MapMode.READ_ONLY,
            ResourceScope.newSharedScope()
        );
        this.base = this.file.getAddr();
    }

    @TearDown(Level.Trial)
    public void closeResources() {
        file.close();
    }

    @Benchmark
    public void readUnsafe(Blackhole blackhole) {
        int index = ThreadLocalRandom.current().nextInt(bytes);
        blackhole.consume(unsafe.getByte(base + index));
    }

    @Benchmark
    public void readSegment(Blackhole blackhole) {
        int index = ThreadLocalRandom.current().nextInt(bytes);
        blackhole.consume(MemoryAccess.getByteAtOffset(memorySegment, index));
    }
}
