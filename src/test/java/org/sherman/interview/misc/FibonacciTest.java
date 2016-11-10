package org.sherman.interview.misc;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Denis Gabaydulin
 * @since 10/11/2016
 */
public class FibonacciTest {
    @Test
    public void getFib() {
        assertEquals(Fibonacci.getFib(0), 0);
        assertEquals(Fibonacci.getFib(1), 1);
        assertEquals(Fibonacci.getFib(10), 55);
    }

    @Test
    public void getFibIterative() {
        assertEquals(Fibonacci.getFibIterative(0), 0);
        assertEquals(Fibonacci.getFibIterative(1), 1);
        assertEquals(Fibonacci.getFibIterative(10), 55);
    }

    @Test
    public void getFibMemoized() {
        assertEquals(Fibonacci.getFibMemoized(10), 55);
    }
}
