package org.sherman.interview.java;

import jdk.incubator.foreign.MemorySegment;
import jdk.incubator.foreign.ResourceScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;

public class MemoryApp {
    private static final Logger logger = LoggerFactory.getLogger(MemoryApp.class);

    public static void main(String[] args) {
        MemorySegment segment = MemorySegment.allocateNative(1024 * 1024, ResourceScope.globalScope());
        ByteBuffer buf = segment.asByteBuffer();
        buf.put((byte) 1);

        logger.info("[{}]", segment.byteSize());
        logger.info("[{}]", segment.toByteArray()[0]);
    }
}
