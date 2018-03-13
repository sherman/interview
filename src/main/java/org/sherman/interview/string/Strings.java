package org.sherman.interview.string;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    public static int getLongestPalindrome(String str) {
        Map<Character, Integer> charsToOccurrences = new HashMap<>();

        str.chars().forEach(
            c -> {
                Integer count = charsToOccurrences.get((char) c);
                if (count == null) {
                    count = 1;
                } else {
                    count++;
                }

                charsToOccurrences.put((char) c, count);
            }
        );

        Optional<Character> mid = charsToOccurrences.keySet().stream()
            .filter(c -> charsToOccurrences.get(c) % 2 == 1)
            .findAny();

        int length = 0;

        length = charsToOccurrences.values().stream()
            .filter(v -> v > 1)
            .reduce(
                0,
                (total, newValue) -> {
                    if (newValue % 2 == 0) {
                        total += newValue;
                    } else {
                        total += newValue - 1;
                    }

                    return total;
                }
            );

        return length + (mid.isPresent() ? 1 : 0);
    }

    public static String getLongestPalindromeV2(String s) {
        Map<Character, Integer> stat = new HashMap<>();

        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            Integer cnt = stat.get(chars[i]);
            if (cnt == null) {
                cnt = 1;
            } else {
                cnt++;
            }

            stat.put(chars[i], cnt);
        }

        Character middle = null;
        StringBuilder result1 = new StringBuilder();
        StringBuilder result2 = new StringBuilder();
        for (Character c : stat.keySet()) {
            int cnt = stat.get(c);
            if (cnt >= 2) {
                for (int i = 0; i < cnt / 2; i++) {
                    result1.append(c);
                    result2.append(c);
                }

                if (cnt % 2 == 1) {
                    middle = c;
                }
            } else {
                middle = c;
            }
        }

        return result1.toString() + (middle != null ? middle : "") + result2.reverse().toString();
    }
}
