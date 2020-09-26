package com.leetcode.fb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountsMerge {
    private static final Logger logger = LoggerFactory.getLogger(AccountsMerge.class);

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // 1). Create mapping email to name
        Map<String, String> emailsToNames = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            String name = accounts.get(i).get(0);
            for (int j = 1; j < accounts.get(i).size(); j++) {
                emailsToNames.put(accounts.get(i).get(j), name);
            }
        }

        // 2). Build undirected graph
        Map<String, List<String>> graph = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            // skip name
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String firstEmail = accounts.get(i).get(1);
                if (j == 1) {
                    graph.computeIfAbsent(firstEmail, ignored -> new ArrayList<>());
                } else {
                    String email = accounts.get(i).get(j);
                    graph.computeIfAbsent(firstEmail, ignored -> new ArrayList<>()).add(email);
                    graph.computeIfAbsent(email, ignored -> new ArrayList<>()).add(firstEmail);
                }
            }
        }

        // 3). Find connected components
        Map<String, Set<String>> components = new HashMap<>();
        Set<String> globalVisited = new TreeSet<>();
        for (String email : graph.keySet()) {
            if (!globalVisited.contains(email)) {
                Set<String> component = new TreeSet<>();

                Stack<String> stack = new Stack<>();
                stack.push(email);
                while (!stack.isEmpty()) {
                    String v = stack.pop();
                    component.add(v);
                    globalVisited.add(v);
                    for (String connected : graph.get(v)) {
                        if (!globalVisited.contains(connected)) {
                            stack.push(connected);
                        }
                    }
                }

                components.put(email, component);
            }
        }

        // 4). Prepare result
        List<List<String>> result = new ArrayList<>();
        for (String email : components.keySet()) {
            List<String> data = new ArrayList<>();
            data.add(emailsToNames.get(email));
            data.addAll(components.get(email));
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
