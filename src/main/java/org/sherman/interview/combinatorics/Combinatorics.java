package org.sherman.interview.combinatorics;

/**
 * @author Denis Gabaydulin
 * @since 27/11/2015
 */
public class Combinatorics {
    private Combinatorics() {
    }

    public static long factorial(int n) {
        if (n == 0) {
            return 1;
        }

        if (n == 1) {
            return 1;
        }

        return n * factorial(n - 1);
    }

    public static long getPermutationsWithoutRepetitions(int n, int k) {
        return factorial(n) / factorial(n - k);
    }

    public static long getPermutationsWithRepetitions(int n, int k) {
        return (long) Math.pow(n ,k);
    }

    public static long getCombinationsWithRepetitions(int n, int k) {
        return factorial(n + k - 1) / (factorial(k) * factorial(n - 1));
    }

    public static long getCombinationsWithoutRepetitions(int n, int k) {
        return factorial(n) / (factorial(k) * factorial(n - k));
    }
}
