package org.sherman.interview.misc;

import org.jetbrains.annotations.NotNull;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * @author Denis Gabaydulin
 * @since 17/02/2016
 */
public class BracketChecker {
    private static final char OPEN_BRACKET = '{';
    private static final char CLOSE_BRACKET = '}';

    private BracketChecker() {
    }

    public static boolean isCorrect(@NotNull String sequence) {
        Stack<Integer> depth = new Stack<>();

        try {
            sequence.chars().forEach(
                    chr -> {
                        if (chr == CLOSE_BRACKET) {
                            depth.pop();
                        }

                        if (chr == OPEN_BRACKET) {
                            depth.push(42);
                        }
                    }
            );
        } catch (EmptyStackException e) {
            return false;
        }

        return depth.isEmpty();
    }
}
