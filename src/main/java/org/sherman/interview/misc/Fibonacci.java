package org.sherman.interview.misc;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Denis Gabaydulin
 * @since 10/11/2016
 */
public class Fibonacci {
    private static final Logger log = LoggerFactory.getLogger(Fibonacci.class);

    private static final Map<Integer, Integer> cache = new HashMap<>();

    private Fibonacci() {
    }

    public static int getFib(int x) {
        if (x == 0) {
            return 0;
        }

        if (x == 1) {
            return 1;
        }

        return getFib(x - 1) + getFib(x - 2);
    }

    public static int getFibMemoized(int x) {
        if (x == 0) {
            return 0;
        }

        if (x == 1) {
            return 1;
        }

        Integer result = cache.get(x);

        if (result != null) {
            return result;
        }

        result = getFibMemoized(x - 1) + getFibMemoized(x - 2);

        cache.put(x, result);

        return result;
    }

    public static long getFibIterative(int x) {
        long a = 0;
        long b = 1;

        if (x == 0) {
            return a;
        }

        if (x == 1) {
            return b;
        }

        for (int i = 2; i <= x; i++) {
            long next = a + b;
            a = b;
            b = next;
        }

        return b;
    }
}
