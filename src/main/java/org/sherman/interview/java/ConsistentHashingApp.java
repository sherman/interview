package org.sherman.interview.java;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsistentHashingApp {
    private static final Logger logger = LoggerFactory.getLogger(ConsistentHashingApp.class);

    /**
     * synchronized (nodes) {
     * int removedIndex = nodes.indexOf(node);
     * nodes.set(removedIndex, nodes.get(nodes.size() - 1));
     * nodes.remove(nodes.get(nodes.size() - 1));
     * }
     *
     * @param args
     */

    public static void main(String[] args) throws InterruptedException {
        var buckets = new ArrayList<Integer>();
        for (var i = 0; i < 512; i++) {
            buckets.add(i, i);
        }
        Collections.shuffle(buckets);
        var random = new Random();
        var keys = new ArrayList<String>();
        for (var i = 0; i < 1000; i++) {
            var key = UUID.randomUUID().toString();
            keys.add(key);
        }
        var nodes = new ArrayList<>();
        nodes.add(0);
        nodes.add(1);
        nodes.add(2);
        nodes.add(3);

        var nodesToBuckets = new HashMap<Integer, Set<Integer>>();
        nodesToBuckets.put(0, new HashSet<>());
        nodesToBuckets.put(1, new HashSet<>());
        nodesToBuckets.put(2, new HashSet<>());
        nodesToBuckets.put(3, new HashSet<>());

        for (var i = 0; i < buckets.size(); i++) {
            if (i < 128) {
                nodesToBuckets.get(0).add(buckets.get(i));
            } else if (i < 256) {
                nodesToBuckets.get(1).add(buckets.get(i));
            } else if (i < 384) {
                nodesToBuckets.get(2).add(buckets.get(i));
            } else {
                nodesToBuckets.get(3).add(buckets.get(i));
            }
        }

        var stat = new HashMap<Integer, Integer>();

        for (var i = 0; i < keys.size(); i++) {
            var keyHash = Hashing.md5().hashString(keys.get(i), StandardCharsets.UTF_8).asLong();
            var bucket = Hashing.consistentHash(keyHash, buckets.size());

            for (var entry : nodesToBuckets.entrySet()) {
                if (entry.getValue().contains(bucket)) {
                    var cnt = stat.computeIfAbsent(entry.getKey(), ignored -> 0);
                    stat.put(entry.getKey(), cnt + 1);
                    break;
                }
            }
        }
        logger.info("Distribution: [{}]", stat);
    }
}
