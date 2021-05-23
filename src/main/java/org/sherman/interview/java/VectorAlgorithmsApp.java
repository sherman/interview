package org.sherman.interview.java;

import jdk.incubator.vector.ByteVector;
import jdk.incubator.vector.VectorOperators;
import jdk.incubator.vector.VectorSpecies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

import static jdk.incubator.vector.VectorOperators.EQ;

public class VectorAlgorithmsApp {
    private static final Logger logger = LoggerFactory.getLogger(VectorAlgorithmsApp.class);

    private static final VectorSpecies<Byte> SPECIES = ByteVector.SPECIES_256;

    public static void main(String[] args) {
        logger.info("Zero vector: [{}]", SPECIES.zero());
        logger.info("[{}]", SPECIES.zero().compare(EQ, 0).allTrue());
        logger.info("[{}]", SPECIES.zero().compare(EQ, 1).anyTrue());

        Random r = new Random();
        byte[] data = new byte[256];
        for (int i = 0; i < 256; i++) {
            data[i] = (byte) (r.nextInt(26) + 'a');
        }

        int total = 0;
        for (int i = 0; i < 256; i++) {
            if (data[i] == 'a') {
                ++total;
            }
        }

        logger.info("[{}]", total);

        var bound = SPECIES.loopBound(data.length);

        var total2 = 0;
        for (int i = 0 ;i < bound; i += SPECIES.length()) {
            var chunk = ByteVector.fromArray(SPECIES, data, i);
            var result = chunk.compare(EQ, 'a');
            total2 += result.toVector().abs().reduceLanesToLong(VectorOperators.ADD);
        }

        logger.info("[{}]", total2);
    }
}
