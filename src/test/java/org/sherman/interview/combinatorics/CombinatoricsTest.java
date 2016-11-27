package org.sherman.interview.combinatorics;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Denis Gabaydulin
 * @since 27/11/2015
 */
public class CombinatoricsTest {
    @Test
    public void test() {
        assertEquals(Combinatorics.factorial(1), 1);
        assertEquals(Combinatorics.factorial(2), 2);
        assertEquals(Combinatorics.factorial(3), 6);
        assertEquals(Combinatorics.factorial(11), 39916800);
    }

    @Test
    public void getPermutationsWithRepetitions() {
        assertEquals(Combinatorics.getPermutationsWithRepetitions(2, 2), 4);
        assertEquals(Combinatorics.getPermutationsWithRepetitions(10, 5), 100000);
    }

    @Test
    public void getPermutationsWithoutRepetitions() {
        assertEquals(Combinatorics.getPermutationsWithoutRepetitions(2, 2), 2);
        assertEquals(Combinatorics.getPermutationsWithoutRepetitions(3, 2), 6);
        assertEquals(Combinatorics.getPermutationsWithoutRepetitions(10, 5), 30240);
    }

    @Test
    public void getCombinationsWithRepetitions() {
        assertEquals(Combinatorics.getCombinationsWithRepetitions(2, 2), 3);
        assertEquals(Combinatorics.getCombinationsWithRepetitions(3, 2), 6);
        assertEquals(Combinatorics.getCombinationsWithRepetitions(10, 5), 2002);
    }

    @Test
    public void getCombinationsWithoutRepetitions() {
        assertEquals(Combinatorics.getCombinationsWithoutRepetitions(2, 2), 1);
        assertEquals(Combinatorics.getCombinationsWithoutRepetitions(3, 2), 3);
        assertEquals(Combinatorics.getCombinationsWithoutRepetitions(10, 5), 252);
        assertEquals(Combinatorics.getCombinationsWithoutRepetitions(8, 4), 70);

        assertEquals(Combinatorics.getCombinationsWithoutRepetitions(8, 4), Combinatorics.getPermutationsWithoutRepetitions(8, 4) / Combinatorics.factorial(4));
    }
}
