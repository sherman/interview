package org.sherman.interview.java;

import jdk.incubator.vector.FloatVector;
import jdk.incubator.vector.VectorSpecies;

import java.util.Random;

public class VectorApiApp {
    private static final VectorSpecies<Float> SPECIES = FloatVector.SPECIES_256;
    private static final Random random = new Random();

    public static void main(String[] args) {
        var f1 = new float[1024];
        var f2 = new float[1024];
        var f3 = new float[1024];

        for (int i = 0; i < 1024; i++) {
            f1[i] = random.nextInt(Integer.MAX_VALUE);
            f2[i] = random.nextInt(Integer.MAX_VALUE);
            f3[i] = random.nextInt(Integer.MAX_VALUE);
        }

        vectorComputation(f1, f2, f3);
    }

    private static void vectorComputation(float[] a, float[] b, float[] c) {
        for (int i = 0; i < a.length; i += SPECIES.length()) {
            var m = SPECIES.indexInRange(i, a.length);
            // FloatVector va, vb, vc;
            var va = FloatVector.fromArray(SPECIES, a, i, m);
            var vb = FloatVector.fromArray(SPECIES, b, i, m);
            var vc = va
                .mul(va)
                .add(vb.mul(vb))
                .neg();

            vc.intoArray(c, i, m);
        }
    }
}
