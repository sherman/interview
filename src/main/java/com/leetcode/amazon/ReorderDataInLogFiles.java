package com.leetcode.amazon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

import org.testng.annotations.Test;
import org.testng.internal.junit.ArrayAsserts;

public class ReorderDataInLogFiles {
    private final Pattern digit = Pattern.compile("\\d+");

    public String[] reorderLogFiles(String[] logs) {
        List<String> stringLogs = new ArrayList<>();
        List<String> digitLogs = new ArrayList<>();
        for (int i = 0; i < logs.length; i++) {
            String log = logs[i];
            if (!isDigit(log.split("[ ]")[1])) {
                stringLogs.add(log);
            } else {
                digitLogs.add(log);
            }
        }

        Collections.sort(stringLogs, Comparator.comparing(s -> {
            int space = s.indexOf(" ");
            return s.substring(space) + s.substring(0, space - 1);
        }));
        String[] src = stringLogs.toArray(String[]::new);

        String[] result = new String[logs.length];
        System.arraycopy(src, 0, result, 0, src.length);

        String[] src2 = digitLogs.toArray(String[]::new);
        System.arraycopy(src2, 0, result, src.length, src2.length);

        return result;
    }

    private boolean isDigit(String s) {
        return digit.matcher(s).matches();
    }

    @Test
    public void test() {
        ArrayAsserts.assertArrayEquals(
                reorderLogFiles(new String[]{"dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"}),
                new String[]{"let1 art can", "let3 art zero", "let2 own kit dig", "dig1 8 1 5 1", "dig2 3 6"}
        );

        ArrayAsserts.assertArrayEquals(
                reorderLogFiles(new String[]{"a1 9 2 3 1", "g1 act car", "zo4 4 7", "ab1 off key dog", "a8 act zoo", "a2 act car"}),
                new String[]{"a2 act car", "g1 act car", "a8 act zoo", "ab1 off key dog", "a1 9 2 3 1", "zo4 4 7"}
        );

        ArrayAsserts.assertArrayEquals(
                reorderLogFiles(new String[]{"6p tzwmh ige mc", "ns 566543603829", "ubd cujg j d yf", "ha6 1 938 376 5", "3yx 97 666 56 5", "d 84 34353 2249", "0 tllgmf qp znc", "s 1088746413789", "ys0 splqqxoflgx", "uhb rfrwt qzx r", "u lrvmdt ykmox", "ah4 4209164350", "rap 7729 8 125", "4 nivgc qo z i", "apx 814023338 8"}),
                new String[]{"ubd cujg j d yf","u lrvmdt ykmox","4 nivgc qo z i","uhb rfrwt qzx r","ys0 splqqxoflgx","0 tllgmf qp znc","6p tzwmh ige mc","ns 566543603829","ha6 1 938 376 5","3yx 97 666 56 5","d 84 34353 2249","s 1088746413789","ah4 4209164350","rap 7729 8 125","apx 814023338 8"}
        );
    }
}
