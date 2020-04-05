package org.sherman.interview.sort.misc;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * @author Denis M. Gabaydulin
 * @since 25.01.18
 */
public class MiscSortsTests {
    @Test
    public void sort() {
        MiscSorts.User[] users = MiscSorts.sort(MiscSorts.generate(5, 1));
        assertSorted(users);

        users = MiscSorts.sort(MiscSorts.generate(100, 10));
        assertSorted(users);
    }

    private void assertSorted(MiscSorts.User[] users) {
        for (int i = 1; i < users.length; i++) {
            assertTrue(users[i - 1].week <= users[i].week);
        }
    }
}
