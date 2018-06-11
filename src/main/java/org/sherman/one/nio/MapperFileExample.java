package org.sherman.one.nio;

import com.google.common.primitives.Ints;
import one.nio.mem.MappedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @author sherman
 * @since 12.06.18
 */
public class MapperFileExample {
    private static final Logger log = LoggerFactory.getLogger(MapperFileExample.class);

    public static void main(String[] args) {
        try (MappedFile file = new MappedFile("/dev/shm/testfile", 8 * 512)) {
            ByteBuffer bb = ByteBuffer.wrap(Ints.toByteArray(42));
            bb.flip();
            file.getFile().getChannel().write(bb);

            ByteBuffer bb2 = ByteBuffer.allocate(8);
            byte[] data = new byte[bb2.remaining()];
            file.getFile().getChannel().read(bb2);
            bb2.flip();
            bb2.duplicate().get(data);
            log.info("{}", Ints.fromByteArray(data));

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
