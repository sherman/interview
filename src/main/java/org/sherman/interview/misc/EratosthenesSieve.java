package org.sherman.interview.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Denis Gabaydulin
 * @since 19.10.16
 */
public class EratosthenesSieve {
    private static final Logger log = LoggerFactory.getLogger(EratosthenesSieve.class);

    private EratosthenesSieve() {
    }

    public static List<Integer> gerPrimes(int n) {
        Boolean[] primes = new Boolean[n + 1];

        // fill primes candidates
        for (int i = 0; i < n; i++) {
            primes[i] = true;
        }

        for (int j = 2; j * j <= n; j++) {
            log.info("j: {}", j);

            if (primes[j]) {
                primes[j] = false;

                for (int i = j * 2; i <= n; i += j) {
                    log.info("i: {}", i);

                    primes[i] = false;
                }
            }
        }

        List<Integer> result = new ArrayList<>();

        for (int i = 2; i <= n; i++) {
            if (primes[i]) {
                result.add(i);
            }
        }

        return result;
    }
}
