package org.sherman.interview.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;

public class MemoryApp {
    private static final Logger logger = LoggerFactory.getLogger(MemoryApp.class);

    public static void main(String[] args) {
        MemorySegment segment = Arena.global().allocate(1024 * 1024);
        ByteBuffer buf = segment.asByteBuffer();
        buf.put((byte) 1);

        logger.info("[{}]", segment.byteSize());
        logger.info("[{}]", segment.toArray(ValueLayout.OfByte.JAVA_BYTE));
    }
}
