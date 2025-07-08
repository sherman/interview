package org.sherman.java;

import java.util.HashMap;
import java.util.Random;

public class TreeCollisionsApp {
    public static void main(String[] args) {
        var random = new Random();
        HashMap<Key, String> test = new HashMap<Key, String>();
        for (var i = 0; i < 1024; i++) {
            test.put(new Key(i), String.valueOf(random.nextLong(1L, 5L)));
        }

        test.size();
        test.get(new Key(100));
    }

    private record Key(int i) {
        @Override
        public int hashCode() {
            return 42;
        }
    }
}
