package org.sherman.interview.misc;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Denis M. Gabaydulin
 * @since 06.03.18
 */
public class MajorityElementTest {
    @Test
    public void findMajorityElement() {
        assertEquals(MajorityElement.findMajorityElement(new int[]{1, 1, 2}), 1);
        assertEquals(MajorityElement.findMajorityElement(new int[]{1, 2, 1, 2, 1, 1, 2, 3, 1}), 1);
    }
}
