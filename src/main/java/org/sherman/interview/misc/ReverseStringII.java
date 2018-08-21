package org.sherman.interview.misc;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ReverseStringII {

    /**
     * Reverse words in a string
     *
     * Blue sky is cool -> cool is sky Blue
     */
    public static String reverseString(String s) {
        String reverted = reverse(s);

        char[] chars = reverted.toCharArray();
        int j = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                String revertedWord = reverse(reverted.substring(j, i));
                char[] sub = revertedWord.toCharArray();
                for (int k = 0; k < sub.length; k++) {
                    chars[j + k] = sub[k];
                }
                j = i + 1;
            }
        }

        if (j != chars.length - 1) {
            String revertedWord = reverse(reverted.substring(j, chars.length));
            char[] sub = revertedWord.toCharArray();
            for (int k = 0; k < sub.length; k++) {
                chars[j + k] = sub[k];
            }
        }

        return new String(chars);
    }

    private static String reverse(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            char temp = chars[i];
            chars[i] = chars[chars.length - i - 1];
            chars[chars.length - i - 1] = temp;
        }
        return new String(chars);
    }

    @Test
    public void reverseString() {
        assertEquals(reverseString("Blue sky is cool"), "cool is sky Blue");
        assertEquals(reverseString("Blue sky is "), " is sky Blue");
        assertEquals(reverseString(" Blue sky is"), "is sky Blue ");
    }
}
