package com.leetcode.fb;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MinimumRemoveToMakeValidParentheses {
    private final Stack<IndexedSymbol> stack = new Stack<>();

    public String minRemoveToMakeValid(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(' || chars[i] == ')') {
                IndexedSymbol is = new IndexedSymbol();
                is.c = chars[i];
                is.position = i;

                if (stack.isEmpty()) {
                    stack.push(is);
                } else {
                    IndexedSymbol last = stack.peek();
                    if (last.c == '(' && is.c == ')') {
                        stack.pop();
                    } else {
                        stack.push(is);
                    }
                }
            }
        }

        Set<Integer> skip = new HashSet();
        while (!stack.isEmpty()) {
            IndexedSymbol is = stack.pop();
            skip.add(is.position);
        }


        StringBuilder b = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (!skip.contains(i)) {
                b.append(chars[i]);
            }
        }
        return b.toString();
    }

    private static class IndexedSymbol {
        char c;
        int position;
    }

    @Test
    public void test() {
        Assert.assertEquals(minRemoveToMakeValid("lee(t(c)o)de)"), "lee(t(c)o)de");
        Assert.assertEquals(minRemoveToMakeValid("a)b(c)d"), "ab(c)d");
        Assert.assertEquals(minRemoveToMakeValid("))(("), "");
    }
}
