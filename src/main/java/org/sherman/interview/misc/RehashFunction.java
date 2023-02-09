package org.sherman.interview.misc;

import com.google.common.hash.Hashing;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class RehashFunction {
    private static final Logger logger = LoggerFactory.getLogger(RehashFunction.class);

    @Test
    public void ringHash() {
        var random = new Random();
        var docs = new ArrayList<Long>();
        var n = 100_000L;
        var initialShards = 4;
        var reshardedShards = initialShards * 2;

        var splits = new HashSet<Pair<Long, Long>>();
        var bound = Long.MAX_VALUE / (initialShards / 2);


        var init = Long.MIN_VALUE;

        for (var i = 0; i < initialShards; i++) {
            var item = Pair.of(init, init + bound);
            splits.add(item);
            init = init + bound + 1;
        }

        logger.info("[{}]", splits);

        var perShardsDocs = new HashMap<Integer, List<Long>>();
        var perShardsRehashedDocs = new HashMap<Integer, List<Long>>();

        var hash = Hashing.murmur3_128();

        for (var i = 1L; i <= n; i++) {
            docs.add(i);
            var shard = getShard(hash.hashLong(i).asLong(), splits);
            var docsPerShard = perShardsDocs.computeIfAbsent(shard, ignored -> new ArrayList<>());
            docsPerShard.add(i);
        }

        for (var i = 0; i < initialShards; i++) {
            logger.info("[{}]", perShardsDocs.get(i).size());
        }

        // rehash
        bound = Long.MAX_VALUE / (reshardedShards / 2);
        splits.clear();
        for (var i = 0; i < reshardedShards; i++) {
            var item = Pair.of(init, init + bound);
            splits.add(item);
            init = init + bound + 1;
        }

        for (var i = 0; i < initialShards; i++) {
            var shards = new HashSet<>();
            for (var doc : perShardsDocs.get(i)) {
                var shard = getShard(hash.hashLong(doc).asLong(), splits);
                perShardsRehashedDocs.computeIfAbsent(shard, ignored -> new ArrayList<>());
                shards.add(shard);
            }
            logger.info("Original shard: [{}], new shards: [{}]", i, shards);
        }
    }

    private int getShard(long value, Set<Pair<Long, Long>> ranges) {
        var list = List.copyOf(ranges);
        for (var i = 0; i < ranges.size(); i++) {
            var range = list.get(i);
            if (range.getLeft() <= value && value <= range.getRight()) {
                return i;
            }
        }
        throw new RuntimeException("unreached!");
    }
}
