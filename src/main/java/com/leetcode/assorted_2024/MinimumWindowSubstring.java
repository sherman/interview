package com.leetcode.assorted_2024;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        var currentState = new HashMap<Character, Integer>();
        var requiredChars = new HashMap<Character, Integer>();

        // create unique character list from t
        for (var i = 0; i < t.length(); i++) {
            var c = t.charAt(i);
            var current = requiredChars.computeIfAbsent(c, ignored -> 0);
            requiredChars.put(c, current + 1);
        }

        var queue = new ArrayDeque<Character>();
        var best = "";

        for (var i = 0; i < s.length(); i++) {
            var c = s.charAt(i);
            if (!stateIfFull(requiredChars, currentState)) {
                // add current char to window
                queue.offer(c);
                // update current state
                if (requiredChars.containsKey(c)) {
                    var current = currentState.computeIfAbsent(c, ignored -> 0);
                    currentState.put(c, current + 1);
                }
            } else {
                // update best solution
                if (queue.size() < best.length() || best.isEmpty()) {
                    while (!queue.isEmpty()) {
                        var head = queue.peek();
                        if (requiredChars.containsKey(head)) {
                            break;
                        } else {
                            queue.poll();
                        }
                    }

                    var builder = new StringBuilder();
                    for (var element : queue) {
                        builder.append(element);
                    }
                    best = builder.toString();
                }

                // pop head
                var removed = queue.pop();
                while (!queue.isEmpty()) {
                    var head = queue.peek();
                    if (requiredChars.containsKey(head)) {
                        // update current state
                        var current = currentState.get(removed);
                        if (current != null) {
                            if (current == 1) {
                                currentState.remove(removed);
                            } else {
                                currentState.put(removed, current - 1);
                            }
                        }
                        // we found another required char in the window
                        break;
                    } else {
                        queue.poll();
                    }
                }
                // add current char to the window
                queue.offer(c);
                if (requiredChars.containsKey(c)) {
                    var current = currentState.computeIfAbsent(c, ignored -> 0);
                    currentState.put(c, current + 1);
                }
            }
        }

        if (stateIfFull(requiredChars, currentState)) {
            while (!queue.isEmpty()) {
                var head = queue.peek();
                if (requiredChars.containsKey(head)) {
                    break;
                } else {
                    queue.poll();
                }
            }
            if (best.isEmpty() || (queue.size() > 0 && queue.size() < best.length())) {
                var builder = new StringBuilder();
                while (!queue.isEmpty()) {
                    builder.append(queue.pop());
                }
                best = builder.toString();
            }
        }
        return best;
    }

    private static boolean stateIfFull(Map<Character, Integer> expected, Map<Character, Integer> actual) {
        if (expected.size() != actual.size()) {
            return false;
        }

        for (var entry : expected.entrySet()) {
            if (actual.get(entry.getKey()) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void cases() {
        Assert.assertEquals( minWindow("BBA", "AB"), "BA");
        Assert.assertEquals( minWindow("ABC", "B"), "B");
        Assert.assertEquals( minWindow("AB", "A"), "A");
        Assert.assertEquals( minWindow("AB", "B"), "B");
        Assert.assertEquals( minWindow("A", "AA"), "");
        Assert.assertEquals( minWindow("ADOBECODEBANC", "ABC"), "BANC");
        Assert.assertEquals( minWindow("A", "A"), "A");
    }
}
