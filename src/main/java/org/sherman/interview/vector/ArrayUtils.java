package org.sherman.interview.vector;

import com.google.common.base.Preconditions;
import jdk.incubator.vector.IntVector;
import jdk.incubator.vector.VectorOperators;
import jdk.incubator.vector.VectorSpecies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArrayUtils {
    private static final Logger logger = LoggerFactory.getLogger(ArrayUtils.class);

    private static final VectorSpecies<Integer> SPECIES = IntVector.SPECIES_128;

    private ArrayUtils() {
    }

    public static boolean hasIntersectionScalar(int[] left, int[] right) {
        boolean hasIntersection = false;
        int i = 0;
        int j = 0;
        while (i < left.length && j < right.length) {
            if (left[i] == right[j]) {
                return true;
            } else if (left[i] > right[j]) {
                j++;
            } else {
                i++;
            }
        }

        return false;
    }

    public static boolean hasIntersectionVector(int[] left, int[] right) {
        Preconditions.checkArgument(left.length % SPECIES.length() == 0);
        Preconditions.checkArgument(right.length % SPECIES.length() == 0);

        var bound1 = SPECIES.loopBound(left.length);
        var bound2 = SPECIES.loopBound(right.length);

        int i = 0;
        int j = 0;
        while (i < bound1 && j < bound2) {
            var chunk = IntVector.fromArray(SPECIES, left, i);
            var rightVector = IntVector.fromArray(SPECIES, right, j);

            if (handleChunk(chunk, rightVector)) {
                return true;
            }

            var chunkLast = chunk.lane(SPECIES.length() - 1);
            var rightLast = rightVector.lane(SPECIES.length() - 1);

            if (chunkLast > rightLast) {
                j += SPECIES.length();
            } else if (chunkLast < rightLast) {
                i += SPECIES.length();
            } else {
                j += SPECIES.length();
                i += SPECIES.length();
            }
        }

        //logger.info("[{}]", counter);

        return false;
    }

    private static boolean handleChunk(IntVector chunk1, IntVector rightVector) {
        var v1 = rightVector.lane(0);
        var v2 = rightVector.lane(1);
        var v3 = rightVector.lane(2);
        var v4 = rightVector.lane(3);

        /*var v5 = rightVector.lane(4);
        var v6 = rightVector.lane(5);
        var v7 = rightVector.lane(6);
        var v8 = rightVector.lane(7);*/

        var r1 = chunk1.eq(v1);
        var r2 = chunk1.eq(v2);
        var r3 = chunk1.eq(v3);
        var r4 = chunk1.eq(v4);

        /*var r5 = chunk1.eq(v5);
        var r6 = chunk1.eq(v6);
        var r7 = chunk1.eq(v7);
        var r8 = chunk1.eq(v8);*/

        /*var r5 = chunk1.eq(rightVector.lane(4));
        var r6 = chunk1.eq(rightVector.lane(5));
        var r7 = chunk1.eq(rightVector.lane(6));
        var r8 = chunk1.eq(rightVector.lane(7));*/

        /*var res = r1.or(r2).or(r3).or(r4).or(r5).or(r6).or(r7).or(r8)
            .anyTrue();*/
        var res = r1.or(r2).or(r3).or(r4)
            .anyTrue();

        // return chunk1.eq(v1).or(chunk1.eq(v2)).or(chunk1.eq(v3)).or(chunk1.eq(v4)).anyTrue();

        if (res) {
            return true;
        }

        return false;
    }

    public static long sumVector(int[] data) {
        IntVector sum = IntVector.zero(IntVector.SPECIES_128);
        for (int i = 0; i < data.length - 4; i += 8) {
            var a = IntVector.fromArray(IntVector.SPECIES_128, data, i);
            var b = IntVector.fromArray(IntVector.SPECIES_128, data, i + 4);
            sum = sum.add(a.add(b));
        }
        return sum.reduceLanes(VectorOperators.ADD);
    }
}
