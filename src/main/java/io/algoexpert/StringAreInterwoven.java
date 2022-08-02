package io.algoexpert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StringAreInterwoven {
    private static final Logger logger = LoggerFactory.getLogger(StringAreInterwoven.class);

    public static boolean interweavingStrings(String one, String two, String three) {
        if (three.length() != one.length() + two.length()) {
            return false;
        }

        return areInterwoven(one, two, three, 0, 0);
    }

    private static boolean areInterwoven(String one, String two, String three, int i, int j) {
        var k = i + j;
        if (k == three.length()) {
            return true;
        }

        logger.info(
            "[{}]:[{}] [{}][{}] [{}][{}]",
            i, i < one.length() ? one.charAt(i) : "",
            j, j < two.length() ?two.charAt(j) : "",
            k, three.charAt(k)
        );

        if (i < one.length() && one.charAt(i) == three.charAt(k)) {
            if (areInterwoven(one, two, three, i + 1, j)) {
                return true;
            }
        }

        if (j < two.length() && two.charAt(j) == three.charAt(k)) {
            if (areInterwoven(one, two, three, i, j + 1)) {
                return true;
            }
        }

        return false;
    }

    @Test
    public void test() {
        Assert.assertTrue(interweavingStrings("aaa", "aaab", "aaabaaa"));
    }
}
