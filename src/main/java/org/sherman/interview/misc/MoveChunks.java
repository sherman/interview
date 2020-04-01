package org.sherman.interview.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Range;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MoveChunks {
    public static List<Integer> isValidQueries(
            Map<Integer, Integer> chunksToServers,
            List<Query> queries
    ) {
        List<Integer> result = new ArrayList<>();

        for (Query query : queries) {
            boolean fail = false;
            int srcServer = query.from;
            for (int chunk = query.chunks.lowerEndpoint(); chunk <= query.chunks.upperEndpoint(); chunk++) {
                if (!chunkExists(chunk, srcServer, chunksToServers)) {
                    result.add(0);
                    fail = true;
                    break;
                }
            }

            if (!fail) {
                result.add(1);
            }
        }

        return result;
    }

    private static boolean chunkExists(int chunk, int server, Map<Integer, Integer> chunksToServers) {
        return Optional.ofNullable(chunksToServers.get(chunk))
                .map(s -> s.equals(server))
                .orElse(false);
    }

    @Test
    public void isValidQueries() {
        Assert.assertEquals(
                ImmutableList.of(
                        1, 0, 1, 0, 0, 1
                ),
                isValidQueries(
                        ImmutableMap.of(1, 1, 2, 2, 3, 3, 4, 4, 5, 5),
                        ImmutableList.of(
                                new Query(1, 2, Range.closed(1, 1)),
                                new Query(2, 3, Range.closed(1, 3)),
                                new Query(4, 2, Range.closed(4, 4)),
                                new Query(2, 5, Range.closed(1, 4)),
                                new Query(3, 2, Range.closed(2, 3)),
                                new Query(3, 2, Range.closed(3, 3))
                        )
                )
        );
    }

    public static class Query {
        private final int from;
        private final int to;
        private final Range<Integer> chunks;

        public Query(int from, int to, Range<Integer> chunks) {
            this.from = from;
            this.to = to;
            this.chunks = chunks;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Query query = (Query) o;
            return from == query.from &&
                    to == query.to &&
                    Objects.equal(chunks, query.chunks);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(from, to, chunks);
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("from", from)
                    .add("to", to)
                    .add("chunks", chunks)
                    .toString();
        }
    }
}
