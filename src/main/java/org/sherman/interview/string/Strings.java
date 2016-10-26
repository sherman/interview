package org.sherman.interview.string;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @author Denis Gabaydulin
 * @since 26.10.16
 */
public class Strings {
    private static final Logger log = LoggerFactory.getLogger(Strings.class);

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

    public static boolean isPalindrome(@NotNull String string) {
        char[] chars = string.toCharArray();

        for (int i = 0; i < chars.length / 2; i++) {
            if (chars[i] != chars[chars.length - i - 1]) {
                return false;
            }
        }

        return true;
    }

    public static boolean isAnagramBySort(@NotNull String one, @NotNull String two) {
        char space = ' ';
        int[] oneSorted = one.chars().filter(c -> c != space).sorted().toArray();
        int[] twoSorted = two.chars().filter(c -> c != space).sorted().toArray();

        return Arrays.equals(oneSorted, twoSorted);
    }

    public static boolean isAnagramByHash(@NotNull String one, @NotNull String two) {
        int counts[] = new int[26];

        one.chars().filter(c -> c - 97 > 0 && c - 97 < 26).forEach(
                c -> counts[c - 97]++
        );

        log.info("{}", counts);

        two.chars().filter(c -> c - 97 > 0 && c - 97 < 26).forEach(
                c -> counts[c - 97]--
        );

        log.info("{}", counts);

        return !Arrays.stream(counts)
                .filter(c -> c != 0)
                .findFirst()
                .isPresent();
    }
}
