package org.sherman.interview.stack;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * @author Denis Gabaydulin
 * @since 04.12.16
 */
public class StackTest {
    @Test
    public static void isEmpty() {
        Stack<Integer> stack = new Stack<>(42);
        assertTrue(stack.isEmpty());
        stack.push(42);
        assertFalse(stack.isEmpty());
    }

    @Test
    public static void push() {
        Stack<Integer> stack = new Stack<>(42);
        assertTrue(stack.isEmpty());
        stack.push(42);
        assertFalse(stack.isEmpty());
        assertEquals(stack.pop(), Integer.valueOf(42));
        assertTrue(stack.isEmpty());
    }

    @Test
    public static void pop() {
        Stack<Integer> stack = new Stack<>(42);
        assertTrue(stack.isEmpty());
        stack.push(42);
        stack.push(43);
        assertFalse(stack.isEmpty());
        assertEquals(stack.pop(), Integer.valueOf(43));
        assertEquals(stack.pop(), Integer.valueOf(42));
        assertTrue(stack.isEmpty());
    }
}
