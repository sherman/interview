package com.leetcode.amazon;

import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoodWords {
    public int countCharacters(String[] words, String chars) {
        var index = makeIndex(chars);
        var sum = 0;

        for (var word : words) {
            var wordChars = word.toCharArray();

            var failed = false;
            var copy = new HashMap<>(index);
            for (var i = 0; i < wordChars.length; i++) {
                var charCount = copy.get(wordChars[i]);

                if (charCount == null || charCount == 0) {
                    failed = true;
                    break;
                } else {
                    copy.put(wordChars[i], charCount - 1);
                }
            }
            if (!failed) {
                sum += word.length();
            }
        }

        return sum;
    }

    public static Map<Character, Integer> makeIndex(String allChars) {
        var index = new HashMap<Character, Integer>();
        var chars = allChars.toCharArray();
        for (var i = 0; i < chars.length; i++) {
            var value = index.getOrDefault(chars[i], 0);
            index.put(chars[i], value + 1);
        }
        return index;
    }

    @Test
    public void test() {
        Assert.assertEquals(countCharacters(new String[] {"dyiclysmffuhibgfvapygkorkqllqlvokosagyelotobicwcmebnpznjbirzrzsrtzjxhsfpiwyfhzyonmuabtlwin","ndqeyhhcquplmznwslewjzuyfgklssvkqxmqjpwhrshycmvrb","ulrrbpspyudncdlbkxkrqpivfftrggemkpyjl","boygirdlggnh","xmqohbyqwagkjzpyawsydmdaattthmuvjbzwpyopyafphx","nulvimegcsiwvhwuiyednoxpugfeimnnyeoczuzxgxbqjvegcxeqnjbwnbvowastqhojepisusvsidhqmszbrnynkyop","hiefuovybkpgzygprmndrkyspoiyapdwkxebgsmodhzpx","juldqdzeskpffaoqcyyxiqqowsalqumddcufhouhrskozhlmobiwzxnhdkidr","lnnvsdcrvzfmrvurucrzlfyigcycffpiuoo","oxgaskztzroxuntiwlfyufddl","tfspedteabxatkaypitjfkhkkigdwdkctqbczcugripkgcyfezpuklfqfcsccboarbfbjfrkxp","qnagrpfzlyrouolqquytwnwnsqnmuzphne","eeilfdaookieawrrbvtnqfzcricvhpiv","sisvsjzyrbdsjcwwygdnxcjhzhsxhpceqz","yhouqhjevqxtecomahbwoptzlkyvjexhzcbccusbjjdgcfzlkoqwiwue","hwxxighzvceaplsycajkhynkhzkwkouszwaiuzqcleyflqrxgjsvlegvupzqijbornbfwpefhxekgpuvgiyeudhncv","cpwcjwgbcquirnsazumgjjcltitmeyfaudbnbqhflvecjsupjmgwfbjo","teyygdmmyadppuopvqdodaczob","qaeowuwqsqffvibrtxnjnzvzuuonrkwpysyxvkijemmpdmtnqxwekbpfzs","qqxpxpmemkldghbmbyxpkwgkaykaerhmwwjonrhcsubchs"}, "usdruypficfbpfbivlrhutcgvyjenlxzeovdyjtgvvfdjzcmikjraspdfp"), 0);
        Assert.assertEquals(countCharacters(new String[] {"cat", "bt", "hat", "tree"}, "atach"), 6);
        Assert.assertEquals(countCharacters(new String[] {"hello", "world", "leetcode"}, "welldonehoneyr"), 10);
    }
}
