package org.sherman.interview.misc;

import java.util.Random;

/**
 * @author Denis Gabaydulin
 * @since 16.04.17
 */
public class Randomizers {
    private static final Random random = new Random(42);

    private Randomizers() {
    }

    public static int random1_6() {
        return random.nextInt(6) + 1;
    }
}
