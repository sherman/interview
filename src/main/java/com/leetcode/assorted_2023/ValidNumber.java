package com.leetcode.assorted_2023;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidNumber {
    public boolean isNumber(String s) {
        var stateMachine = new StateMachine(s);
        stateMachine.handle();
        return stateMachine.isValid();
    }

    private static class StateMachine {
        private State state = State.FIRST_CHAR;
        private final char[] number;
        private char current;
        private int i;

        private boolean valid = true;

        private boolean dotFound = false;
        private boolean exponentFound = false;

        private boolean hasDigits = false;

        private StateMachine(String number) {
            this.number = number.toCharArray();
        }

        public void handle() {
            for (i = 0; i < number.length; i++) {
                current = number[i];
                switch (state) {
                    case FIRST_CHAR -> onFirstChar();
                    case SIGN -> onSign();
                    case INVALID -> onInvalid();
                    case NEXT_CHAR -> onNextChar();
                    case EXPONENT_START -> onExponentStart();
                    case EXPONENT_SIGN -> onExponentSign();
                    case EXPONENT_COMPLETED -> onExponentCompleted();
                    case DOT -> onDot();
                }

                // exit when invalid
                if (!valid) {
                    break;
                }
            }

            if (
                state == State.INVALID
                    || state == State.EXPONENT_START
                    || state == State.EXPONENT_SIGN
                    || !hasDigits
            ) {
                onInvalid();
            }
        }

        private void onDot() {
            if (Character.isDigit(current)) {
                hasDigits = true;
                state = State.NEXT_CHAR;
            } else {
                state = State.INVALID;
            }
        }

        private void onSign() {
            if (Character.isDigit(current) || current == '.') {
                if (Character.isDigit(current)) {
                    hasDigits = true;
                }
                state = State.NEXT_CHAR;
            } else {
                state = State.INVALID;
            }
        }

        private void onExponentSign() {
            if (Character.isDigit(current)) {
                hasDigits = true;
                state = State.EXPONENT_COMPLETED;
            } else {
                state = State.INVALID;
            }
        }

        private void onExponentCompleted() {
            exponentFound = true;
            if (i > 0) {
                i--;
            }
            state = State.NEXT_CHAR;
        }

        private void onExponentStart() {
            if (Character.isDigit(current)) {
                state = State.EXPONENT_COMPLETED;
            } else if (current == '-' || current == '+') {
                state = State.EXPONENT_SIGN;
            } else {
                state = State.INVALID;
            }
        }

        private void onNextChar() {
            if (Character.isDigit(current)) {
                if (Character.isDigit(current)) {
                    hasDigits = true;
                }
                state = State.NEXT_CHAR;
            } else if (current == '.') {
                if (dotFound || exponentFound) {
                    state = State.INVALID;
                } else {
                    dotFound = true;
                    state = State.NEXT_CHAR;
                }
            } else if (current == 'e' || current == 'E') {
                if (exponentFound) {
                    state = State.INVALID;
                } else {
                    state = State.EXPONENT_START;
                }
            } else {
                state = State.INVALID;
            }
        }

        public boolean isValid() {
            return valid;
        }

        private void onInvalid() {
            valid = false;
        }

        private void onFirstChar() {
            if (Character.isDigit(current)) {
                if (Character.isDigit(current)) {
                    hasDigits = true;
                }
                state = State.NEXT_CHAR;
            } else if (current == '.') {
                // only once
                dotFound = true;
                state = State.DOT;
            } else if (current == '-' || current == '+') {
                state = State.SIGN;
            } else {
                state = State.INVALID;
            }
        }
    }

    private enum State {
        FIRST_CHAR,
        INVALID,
        EXPONENT_START,
        EXPONENT_SIGN,
        EXPONENT_COMPLETED,
        SIGN,
        DOT,
        NEXT_CHAR
    }


    @Test
    public void test() {
        Assert.assertFalse(isNumber(".e132"));
        Assert.assertFalse(isNumber("-e58"));
        Assert.assertFalse(isNumber("."));
        Assert.assertFalse(isNumber("4e+"));

        Assert.assertTrue(isNumber("2"));
        Assert.assertTrue(isNumber("0089"));
        Assert.assertTrue(isNumber("-0.1"));
        Assert.assertTrue(isNumber("+3.14"));
        Assert.assertTrue(isNumber("4."));
        Assert.assertTrue(isNumber("-.9"));
        Assert.assertTrue(isNumber("2e10"));
        Assert.assertTrue(isNumber("-90E3"));
        Assert.assertTrue(isNumber("3e+7"));
        Assert.assertTrue(isNumber("+6e-1"));
        Assert.assertTrue(isNumber("53.5e93"));
        Assert.assertTrue(isNumber("-123.456e789"));

        Assert.assertFalse(isNumber("abc"));
        Assert.assertFalse(isNumber("1a"));
        Assert.assertFalse(isNumber("1e"));
        Assert.assertFalse(isNumber("e3"));
        Assert.assertFalse(isNumber("99e2.5"));
        Assert.assertFalse(isNumber("--6"));
        Assert.assertFalse(isNumber("-+3"));
    }
}
