package org.sherman.interview.misc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Denis Gabaydulin
 * @since 17/02/2016
 */
public class PrimeGenerator {
    private PrimeGenerator() {
    }

    public static List<Integer> getPrimes(int max) {
        List<Integer> primes = new ArrayList<>();

        for (int i = 3; i < max; i = i + 2) {
            if (isPrime(i, primes)) {
                primes.add(i);
            }
        }

        return primes;
    }

    private static boolean isPrime(int candidate, List<Integer> primes) {
        if (candidate % 3 == 0 || primes.stream().map(prime -> candidate % prime).filter(value -> value == 0).findFirst().isPresent()) {
            return false;
        } else {
            return true;
        }
    }
}
