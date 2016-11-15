package org.sherman.interview.misc;

import com.google.common.collect.ImmutableList;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * @author Denis Gabaydulin
 * @since 15/11/2016
 */
public class EnumerateRotes {
    private EnumerateRotes() {
    }

    public static int enumerateRoutesBfs(@NotNull Map<Integer, List<Integer>> graph, int from, int to) {
        if (from == to) {
            return 1;
        } else {
            int count = 0;

            return Optional.ofNullable(graph.get(from)).orElse(ImmutableList.of()).stream()
                    .map(v -> enumerateRoutesBfs(graph, v, to))
                    .reduce(count, (a, b) -> a + b);
        }
    }
}
