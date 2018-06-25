package org.sherman.benchmark.hash;

import java.util.HashMap;
import java.util.Map;

public class CacheHashMap extends Cache {
    private final Map<Long, Elt> elts;

    public CacheHashMap(int capacity) {
        elts = new HashMap<>(capacity);
    }

    @Override
    public void set(Elt elt) {
        elts.put(elt.id, elt);
    }

    @Override
    public Elt get(long id) {
        return elts.get(id);
    }
}
