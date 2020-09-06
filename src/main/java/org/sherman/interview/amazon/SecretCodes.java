package org.sherman.interview.amazon;

import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SecretCodes {
    private static final Logger logger = LoggerFactory.getLogger(SecretCodes.class);

    // TODO: support GAP between groups?
    public boolean isWinner(List<List<String>> groups, List<String> items) {
        for (int i = 0; i < items.size(); i++) {
            StateMachine stateMachine = new StateMachine(groups, items, i);
            if (stateMachine.matched()) {
                return true;
            }
        }

        return false;
    }

    @Test
    public void isWinnerTest() {
        Assert.assertFalse(isWinner(ImmutableList.of(), ImmutableList.of("banana")));
        Assert.assertTrue(isWinner(ImmutableList.of(ImmutableList.of("banana")), ImmutableList.of("banana")));

        Assert.assertTrue(isWinner(
                ImmutableList.of(
                        ImmutableList.of("apple", "apple"),
                        ImmutableList.of("banana", "anything", "banana")
                ),
                ImmutableList.of("orange", "apple", "apple", "banana", "orange", "banana"))
        );

        Assert.assertFalse(isWinner(
                ImmutableList.of(
                        ImmutableList.of("apple", "apple"),
                        ImmutableList.of("banana", "anything", "banana")
                ),
                ImmutableList.of("banana", "orange", "banana", "apple", "apple"))
        );

        Assert.assertFalse(isWinner(
                ImmutableList.of(
                        ImmutableList.of("apple", "apple"),
                        ImmutableList.of("banana", "anything", "banana")
                ),
                ImmutableList.of("apple", "banana", "apple", "banana", "orange", "banana"))
        );

        Assert.assertFalse(isWinner(
                ImmutableList.of(
                        ImmutableList.of("apple", "apple"),
                        ImmutableList.of("apple", "apple", "banana")
                ),
                ImmutableList.of("apple", "apple", "apple", "banana"))
        );
    }

    private static class StateMachine {
        private static final String WILDCARD = "anything";

        private final List<List<String>> groups;
        private final List<String> items;
        private final int startIndex;

        // state
        private int currentIndex;
        private int currentGroup;
        private int currentGroupIndex;
        private State current;
        private boolean matched;

        public StateMachine(List<List<String>> groups, List<String> items, int startIndex) {
            this.current = State.INIT;
            this.groups = groups;
            this.items = items;
            this.startIndex = startIndex;
            this.currentGroup = 0;
            this.currentGroupIndex = 0;
            this.currentIndex = startIndex;
        }

        public boolean matched() {
            while (current != State.EXIT) {
                handleWord();
            }

            return matched;
        }

        public void handleWord() {
            switch (current) {
                case INIT -> {
                    if (groups.isEmpty()) {
                        current = State.FAIL;
                        return;
                    }

                    current = State.START_GROUP;
                    currentIndex = startIndex;
                }

                case START_GROUP -> {
                    logger.info("Start group [{}]", currentGroup);

                    /*if (currentGroupIndex >= groups.get(currentGroup).size()) {
                        current = State.FAIL;
                        return;
                    }*/

                    current = State.MATCH;
                }

                case END_GROUP -> {
                    logger.info("End group [{}]", currentGroup);
                    if (currentGroup == groups.size() - 1) {
                        current = State.FOUND;
                        return;
                    }

                    Preconditions.checkArgument(currentGroup < groups.size(), "Group check!");

                    // next group
                    ++currentGroup;
                    currentGroupIndex = 0;
                    current = State.START_GROUP;
                }

                case MATCH -> {
                    if (currentIndex >= items.size()) {
                        current = State.FAIL;
                        return;
                    }

                    if (!isExpected()) {
                        current = State.FAIL;
                    } else {
                        ++currentIndex;
                        // end of group
                        if (currentGroupIndex == groups.get(currentGroup).size() - 1) {
                            current = State.END_GROUP;
                            return;
                        }

                        Preconditions.checkArgument(currentGroupIndex < groups.get(currentGroup).size(), "Group index check!");
                        ++currentGroupIndex;
                    }
                }

                case FOUND -> {
                    logger.info("Found");
                    this.matched = true;
                    current = State.EXIT;
                }

                case FAIL -> {
                    logger.info("Fail");
                    this.matched = false;
                    current = State.EXIT;
                }

                default -> {
                    throw new IllegalArgumentException("Unknown state:" + current);
                }
            }
        }

        private boolean isExpected() {
            String current = items.get(currentIndex);
            logger.info("g: [{}], i: [{}], item: [{}]", currentGroup, currentGroupIndex, current);

            String expected = groups
                    .get(currentGroup)
                    .get(currentGroupIndex);

            if (expected.equals(WILDCARD)) {
                return true;
            }

            return items.get(currentIndex).equals(expected);
        }
    }

    private enum State {
        INIT,
        START_GROUP,
        END_GROUP,
        MATCH,
        GAP,
        FAIL,
        FOUND,
        EXIT
    }
}


