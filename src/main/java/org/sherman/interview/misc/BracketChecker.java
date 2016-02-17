package org.sherman.interview.misc;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static com.beust.jcommander.internal.Lists.newArrayList;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

/**
 * @author Denis Gabaydulin
 * @since 17/02/2016
 */
public class BracketChecker {
    private static final Logger log = LoggerFactory.getLogger(BracketChecker.class);

    private static final Set<Character> OPEN_BRACKETS = new HashSet<>(newArrayList('{', '('));
    private static final Set<Character> CLOSE_BRACKETS = new HashSet<>(newArrayList('}', ')'));

    private BracketChecker() {
    }

    public static boolean isCorrect(@NotNull String sequence) {
        Stack<Integer> depth = new Stack<>();

        try {
            sequence.chars().forEach(
                    chr -> {
                        if (CLOSE_BRACKETS.contains((char) chr)) {
                            int popped = depth.pop();
                            if (Type.fromChar((char) popped) != Type.fromChar((char) chr)) {
                                throw new IllegalStateException();
                            }
                        }

                        if (OPEN_BRACKETS.contains((char) chr)) {
                            depth.push(chr);
                        }
                    }
            );
        } catch (EmptyStackException | IllegalStateException e) {
            return false;
        }

        return depth.isEmpty();
    }

    private enum Type {
        BRACE(new HashSet<>(newArrayList('{', '}'))),
        PARENTHESIS(new HashSet<>(newArrayList('(', ')')));

        private final Set<Character> symbols;

        Type(Set<Character> symbols) {
            this.symbols = symbols;
        }

        static Type fromChar(char chr) {
            return symbolsMap.get(chr);
        }

        Set<Character> getSymbols() {
            return symbols;
        }

        private static final Map<Character, Type> symbolsMap = Arrays.stream(values())
                .map(value -> value.getSymbols().stream()
                        .map(symbol -> new AbstractMap.SimpleImmutableEntry<>(symbol, value))
                ).flatMap(identity())
                .collect(
                        toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue
                        )
                );

    }
}
