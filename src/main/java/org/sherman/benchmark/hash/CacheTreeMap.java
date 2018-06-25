package org.sherman.benchmark.hash;

import java.util.Map;
import java.util.TreeMap;

public class CacheTreeMap extends Cache {
    private final Map<Long, Elt> elts;

    public CacheTreeMap(int ignored) {
        elts = new TreeMap<>();
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
