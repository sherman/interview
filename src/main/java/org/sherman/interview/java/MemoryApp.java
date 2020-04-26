package org.sherman.interview.java;

import java.nio.ByteBuffer;

import jdk.incubator.foreign.MemorySegment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemoryApp {
    private static final Logger logger = LoggerFactory.getLogger(MemoryApp.class);

    public static void main(String[] args) {
        try (MemorySegment segment = MemorySegment.allocateNative(1024 * 1024)) {
            ByteBuffer buf = segment.asByteBuffer();
            buf.put((byte) 1);

            logger.info("[{}]", segment.byteSize());
            logger.info("[{}]", segment.toByteArray()[0]);
        }
    }
}
