package org.sherman.benchmark.java;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.time.Duration;
import org.sherman.interview.NativeFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IoBenchmark {
    private static final Logger logger = LoggerFactory.getLogger(IoBenchmark.class);
    private static final int BUFFER_SIZE = 8192 * 8;
    private static final VarHandle LONG_BYTE_ARRAY_VIEW = MethodHandles.byteArrayViewVarHandle(long[].class, ByteOrder.LITTLE_ENDIAN);

    public static void main(String[] args) throws IOException, InterruptedException {
        // warm up
        File file = new File("/tmp/warmup");
        file.delete();
        Files.touch(file);

        RandomAccessFile raf = new RandomAccessFile(file, "r");

        long size1 = 1 << 20; // 1 mb
        long size2 = 10 << 20; // 64 mb
        long size3 = 256 << 20; // 256 mb, crc 1557498981 (crc32c 1905533883)

        // calc max
        boolean calcMax = true;
        // use posix read (JNI version)
        boolean useJni = true;

        // write a data to file
        long size = size3;
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
            if (useJni) {
                logger.info("Total read: {}", readDataFilePosixRead(file, calcMax));
            } else {
                logger.info("Total read: {}", readDataFileNioOffheap(file, calcMax));
            }
        }

        int num = 64;
        long s = System.nanoTime();
        for (int i = 0; i < num; i++) {
            long max = 0L;
            if (useJni) {
                max = readDataFilePosixRead(file, calcMax);
            } else {
                max = readDataFileNioOffheap(file, calcMax);
            }
            if (calcMax) {
                Preconditions.checkArgument(max == 3544386989794013488L);
            } else {
                Preconditions.checkArgument(max == size);
            }
        }

        logger.info("Avg: {}", Duration.ofNanos((System.nanoTime() - s) / num).toNanos() / 1000);
        logger.info("Avg (mills): {}", Duration.ofNanos((System.nanoTime() - s) / num).toMillis());

        raf.close();
    }

    private static long readDataFileNio(File file, boolean crc) throws IOException {
        byte[] buffer = new byte[BUFFER_SIZE];
        long readTotal = 0;
        HashFunction hashFunction = Hashing.crc32c();
        Hasher hasher = hashFunction.newHasher();
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            int bytes = 0;
            while ((bytes = raf.read(buffer)) != -1) {
                readTotal += bytes;
                if (crc) {
                    // use putBytes because JVM has intrinsic for it
                    hasher.putBytes(buffer);
                }
            }
        }
        if (crc) {
            return hasher.hash().asInt();
        } else {
            return readTotal;
        }
    }

    private static long readDataFileNioOffheap(File file, boolean calcMax) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocateDirect(BUFFER_SIZE);
        long max = 0L;
        long readTotal = 0;
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            FileChannel channel = raf.getChannel();
            int bytes = 0;
            while ((bytes = channel.read(buffer)) != -1) {
                readTotal += bytes;
                if (calcMax) {
                    buffer.flip();
                    var totalLength = BUFFER_SIZE / Long.BYTES;
                    for (var i = 0; i < totalLength; i++) {
                        var item = buffer.getLong();
                        max = Math.max(max, item);
                    }
                }
                buffer.clear();
            }
        }
        if (calcMax) {
            return max;
        } else {
            return readTotal;
        }
    }

    private static long readDataFilePosixRead(File file, boolean calcMax) throws IOException {
        byte[] buffer = new byte[BUFFER_SIZE];
        long readTotal = 0;
        long max = 0L;
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            int fd = PlatformDependent.getFd(raf.getFD());
            int bytes = 0;
            while ((bytes = NativeFunctions.posixRead(fd, buffer)) != 0) {
                readTotal += bytes;
                if (calcMax) {
                    var totalLength = BUFFER_SIZE / Long.BYTES;
                    for (var i = 0; i < totalLength; i++) {
                        var item = (long) LONG_BYTE_ARRAY_VIEW.get(buffer, i * Long.BYTES);
                        max = Math.max(max, item);
                    }
                }
            }
        }
        if (calcMax) {
            return max;
        } else {
            return readTotal;
        }
    }
}