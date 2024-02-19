package com.leetcode.assorted_2024;

import static org.testng.Assert.assertEquals;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class Permutations {
    private static final Logger logger = LoggerFactory.getLogger(Permutations.class);

    private List<String> getPermutations(String number) {
        var chars = number.chars().mapToObj(c -> (char) c).toList();
        return recursion(chars);
    }

    private static List<String> recursion(List<Character> chars) {
        // base case
        if (chars.size() == 1) {
            var builder = new StringBuilder();
            for (var c : chars) {
                builder.append(c);
            }
            return List.of(builder.toString());
        }

        var head = chars.get(0);
        var tail = chars.subList(1, chars.size());
        // for a tail make a recursion call
        var tailPermutations = recursion(tail);
        var permutations = new ArrayList<String>();
        for (var permutation : tailPermutations) {
            logger.info("=============: [{}]", permutation);
            for (var i = 0; i < permutation.length() + 1; i++) {
                var perm = new StringBuilder();
                perm.append(permutation, 0, i);
                perm.append(head);
                perm.append(permutation.substring(i));
                logger.info("[{}]", perm);
                permutations.add(perm.toString());
            }
        }
        return permutations;
    }

    @Test
    public void test1() {
        assertEquals(getPermutations("123"), ImmutableList.of("123", "213", "231", "132", "312", "321"));
    }
}
