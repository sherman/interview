package org.sherman.interview.misc;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Denis Gabaydulin
 * @since 24/11/2016
 */
public class CompactArrayTest {
    @Test
    public void compactArray() {
        assertEquals(CompactArray.compactArray(new int[]{1, 1, 1, 2, 2, 2, 3, 3}), new int[]{1, 3, 2, 3, 3, 2, -1, -1});
    }

    @Test
    public void compactArrayV2() {
        assertEquals(CompactArray.compactArrayV2(new int[]{1, 1, 1, 2, 2, 2, 3, 3, 4, 4}), new int[]{1, 3, 2, 3, 3, 2, 4, 2});
    }

    @Test
    public void runLengthEncoding() {
        assertEquals(CompactArray.runLengthEncoding(new int[]{1, 1, 1, 2, 2, 2, 3, 3, 4, 4}), new int[]{1, 3, 2, 3, 3, 2, 4, 2});
        assertEquals(CompactArray.runLengthEncoding(new int[]{1}), new int[]{1, 1});
        assertEquals(CompactArray.runLengthEncoding(new int[]{1, 1, 1}), new int[]{1, 3});
        assertEquals(CompactArray.runLengthEncoding(new int[]{1, 1, 2}), new int[]{1, 2, 2, 1});
        assertEquals(CompactArray.runLengthEncoding(new int[]{1, 2, 3}), new int[]{1, 1, 2, 1, 3, 1});
    }

    @Test
    public void compactArrayV3() {
        assertEquals(CompactArray.compactArrayV3(new int[]{1, 1, 1, 2, 2, 2, 3, 3, 4, 4}), new int[]{1, 2, 3, 4});
        assertEquals(CompactArray.compactArrayV3(new int[]{1}), new int[]{1});
        assertEquals(CompactArray.compactArrayV3(new int[]{1, 1, 1}), new int[]{1});
        assertEquals(CompactArray.compactArrayV3(new int[]{1, 1, 2}), new int[]{1, 2});
        assertEquals(CompactArray.compactArrayV3(new int[]{1, 2, 3}), new int[]{1, 2, 3});
    }
}
