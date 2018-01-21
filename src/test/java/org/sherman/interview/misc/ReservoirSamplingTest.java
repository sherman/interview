package org.sherman.interview.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.stream.IntStream;

import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

public class ReservoirSamplingTest {
    private static final Logger log = LoggerFactory.getLogger(ReservoirSamplingTest.class);

    @Test
    public void getSamples() {
        assertArrayEquals(ReservoirSampling.getSamples(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}, 12, 6), new int[]{1, 2, 3, 9, 5, 11});
        assertArrayEquals(ReservoirSampling.getSamples(IntStream.range(1, 1024).toArray(), 1024, 32), new int[]{552, 271, 687, 515, 682, 188, 718, 382, 204, 434, 135, 858, 891, 591, 833, 301, 609, 112, 225, 621, 379, 586, 152, 612, 941, 297, 1016, 460, 164, 256, 570, 631});
    }
}
