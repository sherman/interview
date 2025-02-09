package com.leetcode.assorted_2025;

import java.util.LinkedHashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HitCounter {
    private static final Logger logger = LoggerFactory.getLogger(HitCounter.class);

    private final LinkedHashMap<Integer, Integer> data = new LRUCache(300);

    public HitCounter() {
    }

    public void hit(int timestamp) {
        var current = data.getOrDefault(timestamp, 0);
        data.put(timestamp, current + 1);
    }

    public int getHits(int timestamp) {
        var minTs = timestamp > 300 ? timestamp - 300 : 0;

        var hits = 0;
        var fromLastToFirst = data.reversed();
        for (var item : fromLastToFirst.entrySet()) {
            if (item.getKey() > minTs) {
                hits += item.getValue();
            } else {
                break;
            }
        }
        return hits;
    }

    private static class LRUCache extends LinkedHashMap<Integer, Integer> {
        private final int maxSize;

        private LRUCache(int maxSize) {
            this.maxSize = maxSize;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            if (size() > maxSize) {
                this.remove(eldest.getKey());
                logger.warn("Removed eldest entry: {}", eldest);
                return true;
            }

            return false;
        }
    }

    @Test
    public void test() {
        var hitCounter = new HitCounter();
        hitCounter.hit(1);
        hitCounter.hit(2);
        hitCounter.hit(3);
        //Assert.assertEquals(hitCounter.getHits(4), 3);
        hitCounter.hit(300);
        //Assert.assertEquals(hitCounter.getHits(300), 4);
        Assert.assertEquals(hitCounter.getHits(301), 3);
    }
}
