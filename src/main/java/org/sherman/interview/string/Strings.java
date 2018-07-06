package org.sherman.interview.string;

import com.google.common.collect.ImmutableList;
import org.apache.commons.math3.util.Pair;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

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

    public static boolean areStringEquals(String one, String two, int swaps) {
        if (one == null || two == null) {
            return false;
        }

        if (one.length() != two.length()) {
            return false;
        }

        int expectedSwaps = 2 * swaps;
        char[] chars1 = one.toCharArray();
        char[] chars2 = two.toCharArray();

        Map<Pair<Character, Character>, Integer> swapInfo = new HashMap<>();

        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] != chars2[i]) {
                Pair<Character, Character> swap = new Pair<>(chars1[i], chars2[i]);
                Integer counter = swapInfo.get(swap);
                counter = counter == null ? 1 : counter + 1;
                swapInfo.put(swap, counter);
                expectedSwaps--;
            }
        }

        if (expectedSwaps != 0) {
            return false;
        }

        for (Pair<Character, Character> swap : swapInfo.keySet()) {
            Integer counter1 = swapInfo.get(swap);
            Integer counter2 = swapInfo.get(new Pair<>(swap.getValue(), swap.getKey()));

            if (!Objects.equals(counter1, counter2)) {
                return false;
            }
        }

        return true;
    }

    public static List<List<String>> groupShiftedStrings(String[] strings) {
        Map<Pair<Integer, List<Integer>>, List<String>> index = new HashMap<>();

        for (String string : strings) {
            log.info("{}", string);

            int length = string.length();
            List<Integer> intervalKey = new ArrayList<>();
            char[] chars = string.toCharArray();

            for (int i = 1; i < string.length(); i++) {
                if (chars[i - 1] <= chars[i]) {
                    intervalKey.add(Math.abs(chars[i - 1] - chars[i]));
                } else {
                    intervalKey.add('z' - chars[i - 1] + 'a' - chars[i] + 1);
                }
            }

            Pair<Integer, List<Integer>> key = new Pair<>(length, intervalKey);
            List<String> values = index.get(key);
            if (values == null) {
                index.put(key, new ArrayList<>());
                values = index.get(key);
            }
            values.add(string);

            log.info("{}", intervalKey);
        }

        log.info("{}", index);

        return new ArrayList<>(index.values());
    }
}
