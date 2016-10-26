package org.sherman.interview.string;

import org.jetbrains.annotations.NotNull;

/**
 * @author Denis Gabaydulin
 * @since 26.10.16
 */
public class Strings {
    private Strings() {
    }

    public static String reverse(@NotNull String string) {
        char[] chars = string.toCharArray();

        for (int i = 0; i < chars.length / 2; i++) {
            char tmp = chars[i];
            chars[i] = chars[chars.length - i - 1];
            chars[chars.length - i - 1] = tmp;
        }

        return new String(chars);
    }
}
