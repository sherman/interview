package org.sherman.interview.java;

import jdk.incubator.vector.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static jdk.incubator.vector.VectorOperators.EQ;

public class VectorAlgorithmsApp {
    private static final Logger logger = LoggerFactory.getLogger(VectorAlgorithmsApp.class);

    private static final VectorSpecies<Byte> SPECIES = ByteVector.SPECIES_256;
    private static final VectorSpecies<Integer> SMALL = IntVector.SPECIES_128;

    public static void main(String[] args) {
        int[] search = new int[]{4, 100};

        int[] data = new int[]{1, 2, 3, 4, 5, 1, 3, 8};
        //logger.info("1: [{}]", IntVector.fromArray(SMALL, data, 0)); // 1, 2
        /*logger.info("2: [{}]", IntVector.fromArray(SMALL, data, 2)); // 3, 4
        logger.info("3: [{}]", IntVector.fromArray(SMALL, data, 4)); // 5, 6
        logger.info("4: [{}]", IntVector.fromArray(SMALL, data, 6)); // 7, 8*/


        var chunk = IntVector.fromArray(SMALL, data, 0);
        var chunk33 = IntVector.fromArray(SMALL, data, 4);
        //var searchV = IntVector.fromArray(SMALL, search, 0);


        /*logger.info("max: [{}]", chunk33.compare(GT, chunk).toVector());

        logger.info("1: [{}]", IntVector.fromArray(SMALL, data, 0));
        var chunk2 = chunk.rearrange(VectorShuffle.fromValues(chunk.species(), 1, 2, 3, 0));
        logger.info("2: [{}]", chunk2);
        var chunk3 = chunk2.rearrange(VectorShuffle.fromValues(chunk.species(), 2, 3, 0, 1));
        logger.info("3: [{}]", chunk3);
        var chunk4 = chunk3.rearrange(VectorShuffle.fromValues(chunk.species(), 3, 0, 1, 2));
        logger.info("4: [{}]", chunk4);*/


        var vector = SMALL.fromArray(data, 0);
        var v2 = vector.rearrange(VectorShuffle.fromValues(chunk.species(), 3, 0, 1, 2));

        logger.info("[{}]", v2);
        //logger.info("[{}]", v2.rearrange(VectorShuffle.fromValues(chunk.species(), 3, 0, 1, 2)));
        //logger.info("[{}]", v2.rearrange(v2.species().iotaShuffle(0, 1, true)));
        //logger.info("[{}]", v2.rearrange(v2.species().shuffleFromValues(3, 0, 1, 2)));
        //logger.info("[{}]", v2.rearrange(VectorShuffle.fromValues(chunk.species(), 3, 0, 1, 2)));
        //logger.info("[{}]", v2.rearrange(VectorShuffle.fromValues(chunk.species(), 3, 0, 1, 2)));
        /*var v3= v2.slice(3, v2);
        var v4= v3.slice(3, v3);
        logger.info("[{}]", v3);
        logger.info("[{}]", v4);*/
        logger.info("[{}]", v2.compare(EQ, 6, VectorMask.fromValues(SMALL, true, true, true, true)).toVector().maskAll(false).anyTrue());
        logger.info("[{}]", v2.compare(EQ, 6, VectorMask.fromValues(SMALL, true, true, true ,true)).anyTrue());
        /*var bound = SMALL.loopBound(data.length);

        boolean hasIntersection = false;
        for (int i = 0 ;i < bound; i += SMALL.length()) {
            var chunk = IntVector.fromArray(SMALL, data, i);
            if (chunk.compare(EQ, searchV).anyTrue()) {
                hasIntersection = true;
                break;
            }
            if (i > 0) {
                var prevChunk = IntVector.fromArray(SMALL, data, i - 1);
                if (prevChunk.compare(EQ, searchV).anyTrue()) {
                    hasIntersection = true;
                    break;
                }
            }
        }

        logger.info("[{}]", hasIntersection);*/

        /*var original = chunk.rearrange(VectorShuffle.fromValues(chunk.species(), 0, 1)); // 3, 4
        var shuffled = chunk.rearrange(VectorShuffle.fromValues(chunk.species(), 1, 0)); // 4, 3
        logger.info("[{}]", original.compare(EQ, searchV).anyTrue()); // false
        logger.info("[{}]", shuffled.compare(EQ, searchV).anyTrue()); // true*/

        /*logger.info("Zero vector: [{}]", SPECIES.zero());
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
            chunk.rearrange(VectorShuffle.fromValues(chunk.species(), 0, 1));
        }

        logger.info("[{}]", total2);*/
    }
}
