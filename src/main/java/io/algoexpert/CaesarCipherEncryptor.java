package io.algoexpert;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CaesarCipherEncryptor {
    public static String caesarCypherEncryptor(String str, int key) {
        var builder = new StringBuilder();
        for (var c : str.toCharArray()) {
            builder.append(shift(c, key));
        }
        return builder.toString();
    }

    private static char shift(char c, int shift) {
        var multiplier = shift > 26 ? shift % 26 : shift;
        var current = c - 97;
        if (current + multiplier >= 26) {
            return (char) (current + multiplier - 26 + 97);
        } else {
            return (char) (current + multiplier + 97);
        }
    }

    @Test
    public void test() {
        Assert.assertEquals(caesarCypherEncryptor("abc", 52), "abc");
        Assert.assertEquals(caesarCypherEncryptor("xzy", 2), "zba");
        Assert.assertEquals(caesarCypherEncryptor("xyz", 2), "zab");
        Assert.assertEquals(caesarCypherEncryptor("xyz", 28), "zab");
    }
}
