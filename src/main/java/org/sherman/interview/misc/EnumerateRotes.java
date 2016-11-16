package org.sherman.interview.misc;

import com.google.common.collect.ImmutableList;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author Denis Gabaydulin
 * @since 15/11/2016
 */
public class EnumerateRotes {
    private static final Logger log = LoggerFactory.getLogger(EnumerateRotes.class);

    private static Map<Integer, Integer> verticesToCounts = new HashMap<>();

    private EnumerateRotes() {
    }

    public static int countRoutesBfs(@NotNull Map<Integer, List<Integer>> graph, int from, int to) {
        if (from == to) {
            return 1;
        } else {
            int count = 0;

            return Optional.ofNullable(graph.get(from)).orElse(ImmutableList.of()).stream()
                    .map(v -> countRoutesBfs(graph, v, to))
                    .reduce(count, (a, b) -> a + b);
        }
    }

    public static int countRoutes(@NotNull boolean[][] graph, int from, int to) {
        verticesToCounts.put(from, 1);
        return countRoutes(graph, to);
    }

    private static int countRoutes(@NotNull boolean[][] graph, int v) {
        log.info("{}", v);

        Integer count = verticesToCounts.get(v);
        if (count != null) {
            return count;
        } else {
            count = 0;
            for (int i = 1; i < graph.length; i++) {
                // has edge i -> v
                if (graph[i][v]) {
                    count += countRoutes(graph, i);
                }
            }
            verticesToCounts.put(v, count);
            return count;
        }
    }
}
