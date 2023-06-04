package com.leetcode.assorted_2023;

import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GenerateParenthesis {
    public static List<String> generateParenthesis(int n) {
        var prefix = "";
        var result = new ArrayList<String>();
        generateParenthesis(prefix, n, n, result);
        return result;
    }

    private static void generateParenthesis(String prefix, int opens, int closes, List<String> result) {
        if (opens == 0 && closes == 0) {
            result.add(prefix);
        } else {
            if (opens > 0 && opens <= closes) {
                generateParenthesis(prefix + "(", opens - 1, closes, result);
            }
            if (closes > 0) {
                generateParenthesis(prefix + ")", opens, closes - 1, result);
            }
        }
    }

    @Test
    public void test() {
        Assert.assertEquals(GenerateParenthesis.generateParenthesis(3), List.of("((()))", "(()())", "(())()", "()(())", "()()()"));
        Assert.assertEquals(GenerateParenthesis.generateParenthesis(1), List.of("() "));
    }
}
