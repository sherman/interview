package com.leetcode.fb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountsMerge {
    private static final Logger logger = LoggerFactory.getLogger(AccountsMerge.class);

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<Integer, String> names = new HashMap<>();
        // Build an index which: email points to an index in the original array.
        Map<String, Set<Integer>> index = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            names.put(i, accounts.get(i).get(0));
            for (int j = 1; j < accounts.get(i).size(); j++) {
                Set<Integer> accountIndex = index.getOrDefault(accounts.get(i).get(j), new HashSet<>());
                accountIndex.add(i);
                index.put(accounts.get(i).get(j), accountIndex);
            }
        }

        //logger.info("{}", index);

        // Then remap indexes, starts from 0. Replace all indexes in a single row with the same value.
        // Replace all equals values by a single value.
        Map<Integer, Pair<Integer, String>> remap = new HashMap<>();
        int id = 0;
        for (String email : index.keySet()) {
            for (int i : index.get(email)) {
                if (remap.get(i) != null) {
                    remap.put(i, remap.get(i));
                } else {
                    remap.put(i, Pair.of(id, names.get(i)));
                }
            }
            id++;
        }

        //logger.info("{}", remap);

        // Remap email index to new keys
        Map<String, Pair<Integer, String>> remapped = new HashMap<>();
        for (String email : index.keySet()) {
            for (int i : index.get(email)) {
                Pair<Integer, String> key = remap.get(i);
                remapped.put(email, key);
                break;
            }
        }

        // Group by key
        Map<Pair<Integer, String>, TreeSet<String>> mergedAccounts = new HashMap<>();
        for (String email : remapped.keySet()) {
            TreeSet<String> emails = mergedAccounts.getOrDefault(remapped.get(email), new TreeSet<>());
            emails.add(email);
            mergedAccounts.put(remapped.get(email), emails);
        }

        List<List<String>> result = new ArrayList<>();
        for (Pair<Integer, String> pair : mergedAccounts.keySet()) {
            List<String> data = new ArrayList<>();
            data.add(pair.getValue());
            data.addAll(mergedAccounts.get(pair));
            result.add(data);
        }

        return result;
    }

    @Test
    public void test() {
        Assert.assertEquals(
                accountsMerge(
                        ImmutableList.of(
                                ImmutableList.of("John", "johnsmith@mail.com", "john00@mail.com"),
                                ImmutableList.of("John", "johnnybravo@mail.com"),
                                ImmutableList.of("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                                ImmutableList.of("Mary", "mary@mail.com"),
                                ImmutableList.of("Mary", "mary@mail.com")
                        )
                ),
                ImmutableList.of(
                        ImmutableList.of("John", "johnnybravo@mail.com"),
                        ImmutableList.of("John", "john00@mail.com", "john_newyork@mail.com", "johnsmith@mail.com"),
                        ImmutableList.of("Mary", "mary@mail.com")
                )
        );

        Assert.assertEquals(
                accountsMerge(
                        ImmutableList.of(
                        )
                ),
                ImmutableList.of(
                )
        );
    }
}
