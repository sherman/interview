package org.sherman.interview.string;

import com.google.common.collect.ImmutableList;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

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

    @Test
    public void isAnagramBySort() {
        assertEquals(Strings.isAnagramBySort("ab", "ab"), true);
        assertEquals(Strings.isAnagramBySort("ab", "ba"), true);
        assertEquals(Strings.isAnagramBySort("abba", "acca"), false);
        assertEquals(Strings.isAnagramBySort("ccccaaabbbb ", "bbbbccccaaa"), true);
    }

    @Test
    public void isAnagramByHash() {
        assertEquals(Strings.isAnagramByHash("abz", "abz"), true);
        assertEquals(Strings.isAnagramByHash("ab", "ba"), true);
        assertEquals(Strings.isAnagramByHash("abba", "acca"), false);
        assertEquals(Strings.isAnagramByHash("ccccaaabbbb z", "bbbbccccaaaz"), true);
    }

    @Test
    public void getLongestPalindrome() {
        assertEquals(Strings.getLongestPalindrome("abccccdd"), 7);
        assertEquals(Strings.getLongestPalindrome("bb"), 2);
    }

    @Test
    public void getLongestPalindromeV2() {
        assertEquals(Strings.getLongestPalindromeV2("abccccdd"), "ccdbdcc");
        assertEquals(Strings.getLongestPalindromeV2("bb"), "bb");
    }

    @Test
    public void areStringsEquals() {
        assertTrue(Strings.areStringEquals("aacc", "ccaa", 2));
        assertFalse(Strings.areStringEquals("aacc", "ccaa", 1));
        assertFalse(Strings.areStringEquals("aacc", "ccaa", 3));
        assertTrue(Strings.areStringEquals("abcd", "acbd", 1));
        assertFalse(Strings.areStringEquals("dbca", "acbd", 1));
    }

    @Test
    public void groupShiftedStrings() {
        assertEquals(
                Strings.groupShiftedStrings(new String[]{"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"}),
                ImmutableList.of(
                        ImmutableList.of("abc", "bcd", "xyz"),
                        ImmutableList.of("az", "ba"),
                        ImmutableList.of("acef"),
                        ImmutableList.of("a", "z")
                )
        );

        assertEquals(
                Strings.groupShiftedStrings(new String[]{"acd", "dfg", "wyz", "yab", "mop", "bdfh", "a", "x", "moqs"}),
                ImmutableList.of(
                        ImmutableList.of("bdfh", "moqs"),
                        ImmutableList.of("a", "x"),
                        ImmutableList.of("acd", "dfg", "wyz", "yab", "mop")
                )
        );
    }

    @Test
    public void getSubstring() {
        assertEquals(Strings.getSubstring("abcde", "cde"), "cde");
        assertEquals(Strings.getSubstring("abcde", "dec"), "cde");
        assertEquals(Strings.getSubstring("a", "bb"), null);
        assertEquals(Strings.getSubstring("abcde", "acd"), null);
        assertEquals(Strings.getSubstring("abcde", null), null);
    }
}
