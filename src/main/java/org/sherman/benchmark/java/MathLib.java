package org.sherman.benchmark.java;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;
import one.nio.util.ByteArrayBuilder;
import one.nio.util.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MathLib {
    private static final Logger logger = LoggerFactory.getLogger(Math.class);

    public static final boolean IS_SUPPORTED = isSupportedOs() && loadNativeLibrary();

    static {
        if (!MathLib.IS_SUPPORTED) {
            throw new IllegalArgumentException("Math native lib is not supported!");
        }
    }

    private static boolean isSupportedOs() {
        return System.getProperty("os.name").toLowerCase().contains("linux") &&
            System.getProperty("os.arch").contains("64");
    }

    private static boolean loadNativeLibrary() {
        try {
            InputStream in = MathLib.class.getResourceAsStream("/util.so");
            if (in == null) {
                logger.error("Cannot find native IO library");
                return false;
            }

            ByteArrayBuilder libData = readStream(in);
            in.close();

            String tmpDir = System.getProperty("java.io.tmpdir", "/tmp");
            File dll = new File(tmpDir, "util." + crc32(libData) + ".so");
            if (!dll.exists() || dll.length() != libData.length() && dll.delete()) {
                FileOutputStream out = new FileOutputStream(dll);
                out.write(libData.buffer(), 0, libData.length());
                out.close();
            }

            String libraryPath = dll.getAbsolutePath();
            System.load(libraryPath);
            return true;
        } catch (Throwable e) {
            logger.error("Cannot load native IO library", e);
            return false;
        }
    }

    private static String crc32(ByteArrayBuilder builder) {
        CRC32 crc32 = new CRC32();
        crc32.update(builder.buffer(), 0, builder.length());
        return Hex.toHex((int) crc32.getValue());
    }

    private static ByteArrayBuilder readStream(InputStream in) throws IOException {
        byte[] buffer = new byte[64000];
        ByteArrayBuilder builder = new ByteArrayBuilder(buffer.length);
        for (int bytes; (bytes = in.read(buffer)) > 0; ) {
            builder.append(buffer, 0, bytes);
        }
        return builder;
    }
}
