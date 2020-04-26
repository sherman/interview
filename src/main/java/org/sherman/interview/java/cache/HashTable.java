package org.sherman.interview.java.cache;

public interface HashTable<K, V> {
    void put(K key, V value);

    V get(K key);

    V remove(K key);

    void close();
}
