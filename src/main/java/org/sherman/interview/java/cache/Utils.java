package org.sherman.interview.java.cache;

import com.google.common.base.Preconditions;

public class Utils {
    private Utils() {
    }

    public static long nextPowerOfTwo(long n) {
        Preconditions.checkArgument(n >= 0, "Expected non-negative value!");
        n--;
        n |= n >> 1;
        n |= n >> 2;
        n |= n >> 4;
        n |= n >> 8;
        n |= n >> 16;
        n |= n >> 32;
        n++;

        return n;
    }

    /**
     * Copied from java.util.HashMap
     */
    public static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
