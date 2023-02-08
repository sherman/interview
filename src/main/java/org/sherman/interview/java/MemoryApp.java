package org.sherman.interview.java;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;
import java.lang.foreign.ValueLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;

public class MemoryApp {
    private static final Logger logger = LoggerFactory.getLogger(MemoryApp.class);

    public static void main(String[] args) {
        MemorySegment segment = MemorySegment.allocateNative(1024 * 1024, MemorySession.global());
        ByteBuffer buf = segment.asByteBuffer();
        buf.put((byte) 1);

        logger.info("[{}]", segment.byteSize());
        logger.info("[{}]", segment.toArray(ValueLayout.OfByte.JAVA_BYTE));
    }
}
