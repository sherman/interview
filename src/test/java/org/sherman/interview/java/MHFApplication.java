package org.sherman.interview.java;

import com.google.common.primitives.Ints;
import it.unimi.dsi.bits.TransformationStrategies;
import it.unimi.dsi.sux4j.mph.GOV4Function;
import it.unimi.dsi.sux4j.mph.LcpMonotoneMinimalPerfectHashFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.stream.IntStream;

public class MHFApplication {
    private static final Logger logger = LoggerFactory.getLogger(MHFApplication.class);

    public static void main(String[] args) throws IOException {
        var mhf = new LcpMonotoneMinimalPerfectHashFunction.Builder<byte[]>()
            .keys(IntStream.range(1, 1025).boxed().map(Ints::toByteArray).toList())
            .numKeys(1024)
            .transform(TransformationStrategies.byteArray())
            .signed(1024)
            .build();

        for (var i = 1; i <= 1024; i++) {
            logger.info("[{}]", mhf.getLong(Ints.toByteArray(i)));
        }

        logger.info("[{}]", mhf.getLong(Ints.toByteArray(2056)));
        logger.info("[{}]", mhf.getLong(Ints.toByteArray(2057)));

        var gov = new GOV4Function.Builder<byte[]>()
            .keys(IntStream.range(1, 1048577).boxed().map(Ints::toByteArray).toList())
            .transform(TransformationStrategies.byteArray())
            .signed(64)
            .build();

        for (var i = 1; i <= 1048576; i++) {
            logger.info("[{}]", gov.getLong(Ints.toByteArray(i)));
        }

        logger.info("[{}]", gov.getLong(Ints.toByteArray(1048579)));
        logger.info("[{}]", gov.getLong(Ints.toByteArray(1048580)));
    }
}
