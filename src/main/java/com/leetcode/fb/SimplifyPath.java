package com.leetcode.fb;

import java.util.Stack;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SimplifyPath {
    public String simplifyPath(String path) {
        // 1). Replace n-/ by single /.
        path = path.replaceAll("[/]{2,}", "/");


        // 2). According to ../ remove part using stack
        Stack<String> stack = new Stack<>();

        // 3). Filter useful actual with stack
        String[] parts = path.split("[/]");
        for (String part : parts) {
            if (part == null || "".equals(part) || ".".equals(part)) {
                continue;
            }

            if (part.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(part);
            }
        }

        StringBuilder builder = new StringBuilder();
        for (String part : stack) {
            builder.append('/');
            builder.append(part);
        }

        String res = builder
                .toString();

        if (res.equals("")) {
            return "/";
        } else {
            return res;
        }
    }

    @Test
    public void test() {
        Assert.assertEquals(simplifyPath("/a//b////c/d//././/.."), "/a/b/c");
        Assert.assertEquals(simplifyPath("/home/"), "/home");
        Assert.assertEquals(simplifyPath("/a/./b/../../c/"), "/c");
        Assert.assertEquals(simplifyPath("/../"), "/");
        Assert.assertEquals(simplifyPath("/../"), "/");
        Assert.assertEquals(simplifyPath("/home//foo/"), "/home/foo");
    }
}
