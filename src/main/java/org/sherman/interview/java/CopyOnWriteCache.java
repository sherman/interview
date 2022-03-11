package org.sherman.interview.java;

import java.util.Arrays;
import java.util.Map;

public class CopyOnWriteCache {
    private volatile long[] data;

    public CopyOnWriteCache(int size) {
        data = new long[size];
    }

    public void update(Map<Integer, Long> update) {
        var copy = Arrays.copyOf(data, data.length);
        for (var entry : update.entrySet()) {
            copy[entry.getKey()] = entry.getValue();
        }
        data = copy;
    }

    public long[] getData(int[] indexes) {
        var snapshot = data;
        var result = new long[indexes.length];
        for (int i = 0; i < indexes.length; i++) {
            result[i] = snapshot[indexes[i]];
        }
        return result;
    }
}
