package io.algoexpert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PhoneNumberMnemonics {
    private static final Map<Character, List<Character>> letters = new HashMap<>();

    public ArrayList<String> phoneNumberMnemonics(String phoneNumber) {
        letters.put('0', List.of('0'));
        letters.put('1', List.of('1'));
        letters.put('2', List.of('a', 'b', 'c'));
        letters.put('3', List.of('d', 'e', 'f'));
        letters.put('4', List.of('g', 'h', 'i'));
        letters.put('5', List.of('j', 'k', 'l'));
        letters.put('6', List.of('m', 'n', 'o'));
        letters.put('7', List.of('p', 'q', 'r', 's'));
        letters.put('8', List.of('t', 'u', 'v'));
        letters.put('9', List.of('w', 'x', 'y', 'z'));

        var res = new ArrayList<String>();

        var chars = phoneNumber.toCharArray();
        char[] current = new char[chars.length];
        Arrays.fill(current, '0');

        rec(chars, current, 0, res);

        System.out.println(res);
        // Write your code here.
        return res;
    }

    private void rec(char[] data, char[] currentData, int current, List<String> result) {
        if (current == data.length) {
            var builder = new StringBuilder();
            for (var k = 0; k < currentData.length; k++) {
                builder.append(currentData[k]);
            }
            result.add(builder.toString());
        } else {
            var resolvedChars = resolve(data[current]);
            for (var resolved : resolvedChars) {
                currentData[current] = resolved;
                System.out.println(current);
                rec(data, currentData, current + 1, result);
            }
        }
    }

    List<Character> resolve(char c) {
        return letters.get(c);
    }

    @Test
    public void test() {
        Assert.assertEquals(
            List.of("1w0j", "1w0k", "1w0l", "1x0j", "1x0k", "1x0l", "1y0j", "1y0k", "1y0l", "1z0j", "1z0k", "1z0l"),
            phoneNumberMnemonics("1905")
        );
    }
}
