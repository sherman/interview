package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.beust.jcommander.internal.Lists;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SubdomainVisitCount {
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> index = new HashMap<>();

        for (String domain : cpdomains) {
            String[] parts = domain.split("[ ]");
            int count = Integer.parseInt(parts[0]);
            String[] domains = parts[1].split("[.]");

            List<String> domainList = getSubdomains(domains);

            for (String subdomain : domainList) {
                int counter = index.computeIfAbsent(subdomain, ignored -> 0);
                index.put(subdomain, counter + count);
            }
        }

        return index.entrySet().stream()
                .map(e -> e.getValue() + " " + e.getKey())
                .collect(Collectors.toList());
    }

    private List<String> getSubdomains(String[] domains) {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < domains.length; i++) {
            StringBuilder domain = new StringBuilder();
            for (int j = i; j < domains.length; j++) {
                domain.append(domains[j]);
                domain.append('.');
            }
            String subdomain = domain.toString();
            data.add(subdomain.substring(0, subdomain.length() - 1));
        }
        return data;
    }

    @Test
    public void test() {
        Assert.assertEquals(
                ImmutableSet.copyOf(subdomainVisits(new String[]{"9001 discuss.leetcode.com"})),
                ImmutableSet.copyOf(ImmutableList.of(
                        "9001 discuss.leetcode.com",
                        "9001 leetcode.com",
                        "9001 com"
                ))
        );
    }
}
