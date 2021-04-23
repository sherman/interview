package org.sherman.interview.java;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

public class CopyOnWriteArrayList<T> {
    private volatile List<T> data = new ArrayList<>();
    private final Object lock = new Object();

    public void add(T elt) {
        synchronized (lock) {
            List<T> newData = new ArrayList<>(data.size() + 1);
            newData.addAll(data);
            newData.add(elt);
            data = newData;
        }
    }

    public T getElt(int index) {
        Preconditions.checkArgument(index >= 0 && index < data.size(), "Invalid index!");
        return data.get(index);
    }
}
