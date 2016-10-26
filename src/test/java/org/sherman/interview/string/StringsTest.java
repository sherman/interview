package org.sherman.interview.string;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Denis Gabaydulin
 * @since 26.10.16
 */
public class StringsTest {

    @Test
    public void reverse() {
        assertEquals(Strings.reverse("abcd"), "dcba");
        assertEquals(Strings.reverse("abc"), "cba");
        assertEquals(Strings.reverse("a"), "a");
    }

    @Test
    public void isPalindrome() {
        assertEquals(Strings.isPalindrome("aba"), true);
        assertEquals(Strings.isPalindrome("a"), true);
        assertEquals(Strings.isPalindrome("abacddcaba"), true);
        assertEquals(Strings.isPalindrome("abacdccaba"), false);
    }
}
