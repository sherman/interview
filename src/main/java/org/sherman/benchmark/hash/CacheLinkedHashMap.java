package org.sherman.benchmark.hash;

import java.util.LinkedHashMap;

public class CacheLinkedHashMap extends Cache {
    private final LinkedHashMap<Long, Elt> elts;

    public CacheLinkedHashMap(int capacity) {
        this.elts = new LinkedHashMap<>(capacity);
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
