package org.sherman.benchmark.java;

import static java.lang.foreign.ValueLayout.JAVA_BYTE;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SymbolLookup;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.time.Duration;
import java.util.Locale;
import one.nio.util.JavaInternals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Unsafe;

public class MAdviceBenchmark {
    private static final Logger logger = LoggerFactory.getLogger(MAdviceBenchmark.class);
    private static final int BUFFER_SIZE = 8192 * 8;

    private static final ValueLayout.OfLong LAYOUT_LE_LONG =
        ValueLayout.JAVA_LONG.withOrder(ByteOrder.LITTLE_ENDIAN);

    private static final VarHandle VAR_HANDLE = LAYOUT_LE_LONG.arrayElementVarHandle();

    private static final Arena GLOBAL = Arena.global();
    private static final MethodHandle METHOD_HANDLE_MADVICE;
    private static final Unsafe unsafe = JavaInternals.getUnsafe();

    /**
     * No further special treatment.
     */
    public static final int POSIX_MADV_NORMAL = 0;

    /**
     * Expect random page references.
     */
    public static final int POSIX_MADV_RANDOM = 1;

    /**
     * Expect sequential page references.
     */
    public static final int POSIX_MADV_SEQUENTIAL = 2;

    /**
     * Will need these pages.
     */
    public static final int POSIX_MADV_WILLNEED = 3;

    /**
     * Don't need these pages.
     */
    public static final int POSIX_MADV_DONTNEED = 4;

    static {
        var linker = Linker.nativeLinker();
        var stdlib = linker.defaultLookup();
        MethodHandle adviseHandle = null;
        try {
            adviseHandle = lookupMadvise(linker, stdlib);
        } catch (UnsupportedOperationException | IllegalCallerException e) {
            logger.error("Can't find mh", e);
        } catch (RuntimeException | Error e) {
            throw e;
        } catch (Throwable e) {
            throw new AssertionError(e);
        }
        METHOD_HANDLE_MADVICE = adviseHandle;
    }

    private static MethodHandle lookupMadvise(Linker linker, SymbolLookup stdlib) {
        return findFunction(
            linker,
            stdlib,
            "posix_madvise",
            FunctionDescriptor.of(
                ValueLayout.JAVA_INT,
                ValueLayout.ADDRESS,
                ValueLayout.JAVA_LONG,
                ValueLayout.JAVA_INT));
    }

    private static MethodHandle lookupGetPageSize(Linker linker, SymbolLookup stdlib) {
        return findFunction(linker, stdlib, "getpagesize", FunctionDescriptor.of(ValueLayout.JAVA_INT));
    }

    private static MethodHandle findFunction(
        Linker linker, SymbolLookup lookup, String name, FunctionDescriptor desc) {
        final MemorySegment symbol =
            lookup
                .find(name)
                .orElseThrow(
                    () ->
                        new UnsupportedOperationException(
                            "Platform has no symbol for '" + name + "' in libc."));
        return linker.downcallHandle(symbol, desc);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        // warm up
        File file = new File("/tmp/warmup");
        file.delete();
        Files.touch(file);

        RandomAccessFile raf = new RandomAccessFile(file, "r");

        long size1 = 1 << 20; // 1 mb
        long size2 = 10 << 20; // 64 mb
        long size3 = 256 << 20; // 256 mb, crc 1557498981 (crc32c 1905533883)
        long size4 = 1024 << 20; // 256 mb, crc 1557498981 (crc32c 1905533883)

        // calc max
        boolean calcMax = true;
        // use posix read (JNI version)
        boolean useJni = true;

        // write a data to file
        long size = size4;
        int k = 0;
        int writes = 0;
        char[] data = new char[BUFFER_SIZE];
        for (long i = 0; i < size; i++) {
            if (i > 0 && i % data.length == 0) {
                Files.asCharSink(file, Charsets.UTF_8, FileWriteMode.APPEND)
                    .write(new String(data));
                k = 0;
                writes++;
            }
            data[k++] = ((i % 2 == 0) ? '0' : '1'); // 0 or 1
        }

        Files.asCharSink(file, Charsets.UTF_8, FileWriteMode.APPEND)
            .write(new String(data));

        logger.info("Writes: [{}]", writes);

        for (int i = 0; i < 64; i++) {
            logger.info("Total read: {}", readMappedFile(file));
        }

        int num = 64;
        long s = System.nanoTime();
        for (int i = 0; i < num; i++) {
            var max = readMappedFile(file);
            Preconditions.checkArgument(max == 3544386989794013488L);
        }

        logger.info("Avg: {}", Duration.ofNanos((System.nanoTime() - s) / num).toNanos() / 1000);
        logger.info("Avg (mills): {}", Duration.ofNanos((System.nanoTime() - s) / num).toMillis());

        raf.close();
    }

    private static long readMappedFile(File file) throws IOException {
        var max = 0L;
        try (var raf = new RandomAccessFile(file, "r")) {
            var channel = raf.getChannel();
            var segment = channel.map(FileChannel.MapMode.READ_ONLY, 0, file.length(), GLOBAL);
            madvise(segment, POSIX_MADV_NORMAL);
            var totalLength = file.length() / Long.BYTES;
            for (var i = 0L; i < totalLength; i++) {
                var value = (long) VAR_HANDLE.get(segment, i);
                max = Math.max(max, value);
            }
            return max;
        }
    }

    private static void madvise(MemorySegment segment, int advice) throws IOException {
        if (segment.byteSize() == 0L) {
            return;
        }
        final int ret;
        try {
            ret = (int) METHOD_HANDLE_MADVICE.invokeExact(segment, segment.byteSize(), advice);
        } catch (Throwable th) {
            throw new AssertionError(th);
        }
        if (ret != 0) {
            throw new IOException(
                String.format(
                    Locale.ENGLISH,
                    "Call to posix_madvise with address=0x%08X and byteSize=%d failed with return code %d.",
                    segment.address(),
                    segment.byteSize(),
                    ret));
        }
    }
}