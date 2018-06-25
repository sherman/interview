package org.sherman.benchmark.hash;

public abstract class Cache {
    public abstract void set(Elt elt);
    public abstract Elt get(long id);
}
