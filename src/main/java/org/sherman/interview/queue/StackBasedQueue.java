package org.sherman.interview.queue;

import org.jetbrains.annotations.NotNull;
import org.sherman.interview.stack.Stack;

/**
 * @author Denis Gabaydulin
 * @since 04.12.16
 */
public class StackBasedQueue<T> {
    private Stack<T> one = new Stack<T>(128);
    private Stack<T> two = new Stack<T>(128);

    public void push(@NotNull T elt) {
        if (one.isEmpty() && two.isEmpty()) {
            one.push(elt);
        } else if (one.isEmpty()) {
            copy(two, one);
            two.push(elt);
            copy(one, two);
        } else {
            copy(one, two);
            one.push(elt);
            copy(two, one);
        }
    }

    @NotNull
    public T pop() {
        if (one.isEmpty() && two.isEmpty()) {
            throw new IllegalStateException("Queue is empty!");
        }

        if (one.isEmpty()) {
            return two.pop();
        } else {
            return one.pop();
        }
    }

    private void copy(Stack<T> from, Stack<T> to) {
        while (!from.isEmpty()) {
            to.push(from.pop());
        }
    }
}
