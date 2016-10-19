package org.sherman.interview.misc;

import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;

/**
 * @author Denis Gabaydulin
 * @since 19.10.16
 */
public class EratosthenesSieveTest {
    @Test
    public void getPrimes() {
        assertEquals(EratosthenesSieve.gerPrimes(20), Arrays.asList(5, 7, 11, 13, 17, 19));
    }
}
