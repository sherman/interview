package com.leetcode.fb_2022;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountsMerge {
    private static final Logger logger = LoggerFactory.getLogger(AccountsMerge.class);

    /**
     * Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
     * <p>
     * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
     * <p>
     * After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        long counter = 0;
        var emailsToUsers = new HashMap<String, User>();
        for (var acc : accounts) {
            Preconditions.checkArgument(acc.size() > 1);
            User user = null;
            // check all emails by acc and find a user
            for (int i = 1; i < acc.size(); i++) {
                var existUser = emailsToUsers.get(acc.get(i));
                if (existUser != null) {
                    user = existUser;
                    break;
                }
            }

            // create a new user if was not found any other
            if (user == null) {
                user = new User(++counter, acc.get(0));
            }

            // fill emails with the user
            for (int i = 1; i < acc.size(); i++) {
                emailsToUsers.putIfAbsent(acc.get(i), user);
            }
        }

        var result = new HashMap<User, TreeSet<String>>();

        for (var userAndEmail : emailsToUsers.entrySet()) {
            logger.info("[{}] [{}]", userAndEmail.getKey(), userAndEmail.getValue());
            var emails = result.computeIfAbsent(userAndEmail.getValue(), k -> new TreeSet<>());
            emails.add(userAndEmail.getKey());
        }

        logger.info("[{}]", result);

        var finalResult = new ArrayList<List<String>>();
        for (var entry : result.entrySet()) {
            var list = new ArrayList<String>();
            list.add(entry.getKey().name());
            list.addAll(entry.getValue());
            finalResult.add(list);
        }

        return finalResult;
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
                ImmutableList.of("John", "john00@mail.com", "john_newyork@mail.com", "johnsmith@mail.com"),
                ImmutableList.of("John", "johnnybravo@mail.com"),
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

        Assert.assertEquals(
            accountsMerge(
                ImmutableList.of(
                    ImmutableList.of("David", "David0@m.co", "David1@m.co"),
                    ImmutableList.of("David", "David3@m.co", "David4@m.co"),
                    ImmutableList.of("David", "David4@m.co", "David5@m.co"),
                    ImmutableList.of("David", "David2@m.co", "David3@m.co"),
                    ImmutableList.of("David", "David1@m.co", "David2@m.co")
                )
            ),
            ImmutableList.of(
                ImmutableList.of("David", "David0@m.co", "David1@m.co", "David2@m.co", "David3@m.co", "David4@m.co", "David5@m.co")
            )
        );
    }

    private record User(long id, String name) {
    }
}
