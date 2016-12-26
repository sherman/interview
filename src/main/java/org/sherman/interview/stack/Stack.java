package org.sherman.interview.stack;

import com.google.common.base.Joiner;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @author Denis Gabaydulin
 * @since 04.12.16
 */
public class Stack<T> {
    private Object[] elts;
    private int size;

    public Stack(int size) {
        elts = new Object[size];
    }

    public void push(@NotNull T elt) {
        if (elts.length == size) {
            resize();
        }

        elts[size++] = elt;
    }

    @NotNull
    public T pop() {
        if (size == 0) {
            throw new IllegalStateException("Stack is empty!");
        }
        return (T) elts[--size];
    }

    public T peek() {
        if (size == 0) {
            return null;
        }
        return (T) elts[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        return "[" + (size > 0 ? Joiner.on(";").join(getNonNullElts()) : "") + "]";
    }

    private List<Object> getNonNullElts() {
        return Arrays.stream(elts)
                .filter(Objects::nonNull)
                .collect(toList());
    }

    private void resize() {
        Object[] newElts = new Object[size * 2];
        System.arraycopy(elts, 0, newElts, 0, size);
    }
}
