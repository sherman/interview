package org.sherman.benchmark.java;

import org.sherman.interview.java.cache.HashTableLongLong;

public class HashTableProfilingApp {
    public static void main(String[] args) {
        boolean exit = false;
        HashTableLongLong ht = new HashTableLongLong(1024 * 1024);
        long i = 1;
        while (true) {
            if (exit) {
                break;
            }

            if (i == 1024 * 1024) {
                i = 1;
            }

            ht.put(i, i);

            i++;
        }

        ht.close();
    }
}
