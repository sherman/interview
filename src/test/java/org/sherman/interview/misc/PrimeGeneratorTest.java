package org.sherman.interview.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Denis Gabaydulin
 * @since 17/02/2016
 */
public class PrimeGeneratorTest {
    private static final Logger log = LoggerFactory.getLogger(PrimeGeneratorTest.class);

    @Test
    public void getPrimes() {
        log.info("{}", PrimeGenerator.getPrimes(10000));
    }
}
