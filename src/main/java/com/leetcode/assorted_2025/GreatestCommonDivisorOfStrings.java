package com.leetcode.assorted_2025;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GreatestCommonDivisorOfStrings {
    private static final Logger logger = LoggerFactory.getLogger(GreatestCommonDivisorOfStrings.class);

    public String gcdOfStrings(String str1, String str2) {
        for (var i = 0; i < str2.length(); i++) {
            var s2 = str2.substring(i);
            if (
                str1.length() % s2.length() == 0
                    && str2.length() % s2.length() == 0
                    && gcdOfStringsInternal(str1, s2)
                    && gcdOfStringsInternal(str2, s2)
            ) {
                return s2;
            }
        }

        return "";
    }

    private static boolean gcdOfStringsInternal(String str1, String str2) {
        var p2 = 0;
        var failed = false;
        var totalVisited = 0;
        for (var i = 0; i < str1.length(); i++) {
            logger.info("{} {}", p2, i);

            var c1 = str1.charAt(i);
            var c2 = str2.charAt(p2);

            if (c1 != c2) {
                failed = true;
                break;
            }

            if (p2 == str2.length() - 1) {
                p2 = 0;
            } else {
                p2++;
            }
            totalVisited++;
        }

        if (!failed && p2 == 0) {
            logger.info("R: {} {}", p2, totalVisited);
            return true;
        }

        return false;
    }

    @Test
    public void test() {
        Assert.assertEquals(gcdOfStrings("AAAAAAAAAAAAAAAAAA", "AAACCCAAAAAA"), "");
        Assert.assertEquals(gcdOfStrings("TAUXXTAUXXTAUXXTAUXXTAUXX", "TAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXX"), "TAUXX");
        Assert.assertEquals(gcdOfStrings("INFO", "CODE"), "");
        Assert.assertEquals(gcdOfStrings("ABCABC", "ABC"), "ABC");
        Assert.assertEquals(gcdOfStrings("ABABAB", "ABAB"), "AB");
    }

}
