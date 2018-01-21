package org.sherman.interview.misc;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class ReservoirSampling {
    private static final Logger log = LoggerFactory.getLogger(ReservoirSampling.class);

    public static int[] getSamples(@NotNull int[] stream, int n, int k) {
        int[] reservoir = new int[k];

        for (int i = 0; i < k; i++) {
            reservoir[i] = stream[i];
        }

        Random random = new Random(42L);

        for (int i = k + 1; i < n; i++) {
            int j = random.nextInt(i + 1);

            if (j < k) {
                reservoir[j] = stream[i];
            } else {
                log.info("j {} >= k {}", j, k);
            }
        }

        return reservoir;
    }
}
