package com.leetcode.assorted_2022;

import com.google.common.base.Preconditions;
import java.util.ArrayDeque;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DecodeString {
    private static final Logger logger = LoggerFactory.getLogger(DecodeString.class);

    public String decodeString(String s) {
        var stack = new java.util.Stack<String>();

        var n = new StringBuilder();
        for (var i = 0; i < s.length(); i++) {
            var cc = s.charAt(i);
            if (cc >= '0' && cc <= '9') {
                n.append(cc);
            } else {
                if (!n.isEmpty()) {
                    stack.push(n.toString());
                    n = new StringBuilder();
                    continue;
                }
            }

            if (!n.isEmpty()) {
                continue;
            }

            if (cc != ']') {
                stack.push(String.valueOf(cc));
            } else {
                int times = -1;
                var sub = new ArrayDeque<String>();
                while (true) {
                    var current = stack.pop();
                    if (isNumber(current)) {
                        times = Integer.parseInt(current);
                        break;
                    } else {
                        if (!current.equals("[")) {
                            sub.add(current);
                        }
                    }
                }

                Preconditions.checkArgument(times > 0, "Positive int is required!");

                var sb = new StringBuilder();
                while (!sub.isEmpty()) {
                    sb.append(sub.removeLast());
                }

                //logger.info("[{}] [{}]", times, sb.toString());
                stack.push(repeat(sb.toString(), times));
            }
        }

        var result = new ArrayDeque<String>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        var sb = new StringBuilder();
        while (!result.isEmpty()) {
            sb.append(result.removeLast());
        }
        return sb.toString();
    }

    private String repeat(String s, int k) {
        var sb = new StringBuilder();
        for (var i = 0; i < k; i++) {
            sb.append(s);
        }
        return sb.toString();
    }

    private boolean isNumber(String s) {
        var c = s.toCharArray()[0];
        return c >= '0' && c <= '9';
    }

    @Test
    public void test() {
        Assert.assertEquals(decodeString("3[z]2[2[y]pq4[2[jk]e1[f]]]ef"), "zzzyypqjkjkefjkjkefjkjkefjkjkefyypqjkjkefjkjkefjkjkefjkjkefef");
        Assert.assertEquals(decodeString("100[leetcode]"), "leetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcode");
        Assert.assertEquals(decodeString("3[a2[c]]"), "accaccacc");
        Assert.assertEquals(decodeString("3[a]2[bc]"), "aaabcbc");
    }
}
