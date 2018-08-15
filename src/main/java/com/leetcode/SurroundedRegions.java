package com.leetcode;

import org.apache.commons.math3.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Denis M. Gabaydulin
 * @since 15.08.18
 */
public class SurroundedRegions {
    private static final Logger log = LoggerFactory.getLogger(SurroundedRegions.class);

    private static final char TARGET = 'O';

    public static int surroundedRegions(char[][] grid) {
        if (grid.length == 0) {
            return 0;
        }

        int n = grid.length * grid[0].length;
        WeightedUnionFind unionFind = new WeightedUnionFind(n);
        Map<Integer, Pair<Integer, Integer>> index = new HashMap<>();

        boolean[] isTarget = new boolean[n];

        int counter = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == TARGET) {
                    if (j - 1 >= 0 && grid[i][j - 1] == TARGET) {
                        if (!unionFind.isConnected(counter, counter - 1)) {
                            unionFind.union(counter, counter - 1);
                        }
                    }

                    if (j + 1 < grid[i].length && grid[i][j + 1] == TARGET) {
                        if (!unionFind.isConnected(counter, counter + 1)) {
                            unionFind.union(counter, counter + 1);
                        }
                    }

                    if (i - 1 >= 0 && grid[i - 1][j] == TARGET) {
                        if (!unionFind.isConnected(counter, counter - grid[0].length)) {
                            unionFind.union(counter, counter - grid[0].length);
                        }
                    }

                    if (i + 1 < grid.length && grid[i + 1][j] == TARGET) {
                        if (!unionFind.isConnected(counter, counter + grid[0].length)) {
                            unionFind.union(counter, counter + grid[0].length);
                        }
                    }
                    isTarget[counter] = true;
                }
                index.put(counter, new Pair<>(i, j));
                counter++;
            }
        }

        Map<Integer, Set<Integer>> groups = unionFind.getConnectedComponents(grid.length, grid[0].length);

        log.info("{}", groups);

        for (int component : groups.keySet()) {
            if (isTarget[component]) {
                log.info("Component: {}", component);
                boolean outside = false;
                // check that all connected vertices are O and there's no outside vertices in the set
                for (int vertex : groups.get(component)) {
                    Pair<Integer, Integer> vIndex = index.get(vertex);
                    if (
                        vIndex.getKey() == 0 || vIndex.getKey() == grid.length - 1
                            || vIndex.getValue() == 0 || vIndex.getValue() == grid[0].length - 1
                            ) {
                        log.info("found bad {}", vertex);
                        outside = true;
                        break;
                    }
                }

                if (!outside) {
                    for (int vertex : groups.get(component)) {
                        log.info("{}", vertex);
                        grid[index.get(vertex).getKey()][index.get(vertex).getValue()] = 'X';
                    }
                }
            }
        }

        log.info("{}", grid[1]);

        return 0;
    }

    public static class WeightedUnionFind {
        private final int[] weight;
        final int[] ids;

        public WeightedUnionFind(int n) {
            this.ids = new int[n];

            for (int i = 0; i < n; i++) {
                ids[i] = i;
            }

            weight = new int[n];
            for (int i = 0; i < weight.length; i++) {
                weight[i] = 1;
            }
        }

        public void union(int p, int q) {
            int i = getRoot(p);
            int j = getRoot(q);

            //log.info("{} {}", ids, weight);

            if (weight[i] >= weight[j]) {
                ids[j] = i;
                weight[i] = weight[i] + weight[j];
            } else {
                ids[i] = j;
                weight[j] = weight[j] + weight[i];
            }
        }

        public boolean isConnected(int p, int q) {
            return getRoot(p) == getRoot(q);
        }

        public Map<Integer, Set<Integer>> getConnectedComponents(int length, int height) {
            log.info("{}", ids);

            return IntStream.range(0, ids.length)
                .boxed()
                .map(index -> new Pair<>(ids[index], index))
                .collect(
                    Collectors.groupingBy(
                        Pair::getFirst,
                        Collectors.toSet()
                    )
                ).entrySet().stream()
                .collect(
                    Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream().map(Pair::getValue)
                            .collect(Collectors.toSet())
                    )
                );
        }

        private int getRoot(int id) {
            while (ids[id] != id) {
                id = ids[id];
            }
            return id;
        }
    }
}
