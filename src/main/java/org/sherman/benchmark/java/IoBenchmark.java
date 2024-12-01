package org.sherman.benchmark.java;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.Duration;
import org.sherman.interview.NativeFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IoBenchmark {
    private static final Logger logger = LoggerFactory.getLogger(IoBenchmark.class);
    private static final int BUFFER_SIZE = 8192 * 8;

    public static void main(String[] args) throws IOException, InterruptedException {
        // warm up
        File file = new File("/tmp/warmup");
        file.delete();
        Files.touch(file);

        RandomAccessFile raf = new RandomAccessFile(file, "r");

        long size1 = 1 << 20; // 1 mb
        long size2 = 10 << 20; // 64 mb
        long size3 = 256 << 20; // 256 mb, crc 1557498981

        // calc crc (might be slow)
        boolean crc = false;
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

            data[k++] = '1';
        }

        Files.asCharSink(file, Charsets.UTF_8, FileWriteMode.APPEND)
            .write(new String(data));

        logger.info("Writes: [{}]", writes);

        for (int i = 0; i < 64; i++) {
            if (useJni) {
                logger.info("Total read: {}", readDataFilePosixRead(file, crc));
            } else {
                logger.info("Total read: {}", readDataFileNio(file, crc));
            }
        }

        int num = 64;
        long s = System.nanoTime();
        for (int i = 0; i < num; i++) {
            long crcOrLength = 0;
            if (useJni) {
                crcOrLength = readDataFilePosixRead(file, crc);
            } else {
                crcOrLength = readDataFileNio(file, crc);
            }
            if (crc) {
                Preconditions.checkArgument(crcOrLength == 1557498981);
            } else {
                Preconditions.checkArgument(crcOrLength == size);
            }
        }

        logger.info("Avg: {}", Duration.ofNanos((System.nanoTime() - s) / num).toNanos() / 1000);
        logger.info("Avg (mills): {}", Duration.ofNanos((System.nanoTime() - s) / num).toMillis());

        raf.close();
    }

    private static long readDataFileNio(File file, boolean crc) throws IOException {
        byte[] buffer = new byte[BUFFER_SIZE];
        long readTotal = 0;
        HashFunction hashFunction = Hashing.crc32();
        Hasher hasher = hashFunction.newHasher();
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            int bytes = 0;
            while ((bytes = raf.read(buffer)) != -1) {
                readTotal += bytes;
                if (crc) {
                    // do not use putBytes because JVM has intrinsic for it
                    //hasher.putBytes(buffer);
                    for (int i = 0; i < bytes; i++) {
                        hasher.putByte(buffer[i]);
                    }
                }
            }
        }
        if (crc) {
            return hasher.hash().asInt();
        } else {
            return readTotal;
        }
    }

    private static long readDataFilePosixRead(File file, boolean crc) throws IOException {
        byte[] buffer = new byte[BUFFER_SIZE];
        long readTotal = 0;
        HashFunction hashFunction = Hashing.crc32();
        Hasher hasher = hashFunction.newHasher();
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            int fd = PlatformDependent.getFd(raf.getFD());
            int bytes = 0;
            while ((bytes = NativeFunctions.posixRead(fd, buffer)) != 0) {
                readTotal += bytes;
                if (crc) {
                    // do not use putBytes because JVM has intrinsic for it
                    //hasher.putBytes(buffer);
                    for (int i = 0; i < bytes; i++) {
                        hasher.putByte(buffer[i]);
                    }
                }
            }
        }
        if (crc) {
            return hasher.hash().asInt();
        } else {
            return readTotal;
        }
    }
}
