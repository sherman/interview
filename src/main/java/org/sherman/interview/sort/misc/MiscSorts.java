package org.sherman.interview.sort.misc;

import com.google.common.base.MoreObjects;
import org.jetbrains.annotations.NotNull;
import org.sherman.interview.sort.merge.MergeSort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Denis M. Gabaydulin
 * @since 25.01.18
 */
public class MiscSorts {
    private static final Logger log = LoggerFactory.getLogger(MergeSort.class);

    private MiscSorts() {
    }

    @NotNull
    public static User[] sort(@NotNull User[] users) {
        NavigableMap<Integer, Long> weeksToUsers = Arrays.stream(users)
            .collect(Collectors.groupingBy(user -> user.week, TreeMap::new, Collectors.counting()));

        Map<Integer, Integer> weeksToIndexes = new HashMap<>();

        int globalIndex = 0;
        for (Integer week : weeksToUsers.navigableKeySet()) {
            weeksToIndexes.put(week, globalIndex);
            globalIndex += weeksToUsers.get(week);
        }

        log.info("Indexes: {}", weeksToIndexes);

        Map<Integer, AtomicInteger> weeksToOffsets = new HashMap<>();
        for (Integer week : weeksToUsers.navigableKeySet()) {
            weeksToOffsets.put(week, new AtomicInteger());
        }

        int i = 0;
        while (i < users.length) {
            User current = users[i];

            log.info("W: {}", current.week);

            if (
                isIndexInRange(
                    i,
                    weeksToIndexes.get(current.week),
                    (int) (weeksToIndexes.get(current.week) + weeksToUsers.get(current.week)))) {

                i++;
            } else {
                swap(i, weeksToIndexes.get(current.week) + weeksToOffsets.get(current.week).get(), users);
                weeksToOffsets.get(current.week).incrementAndGet();
            }
        }

        return users;
    }

    private static boolean isIndexInRange(int index, int from, int to) {
        log.info("{} {} {}", index, from, to);
        return index >= from && index < from + to;
    }

    private static void swap(int from, int to, User[] users) {
        User temp = users[to];
        users[to] = users[from];
        users[from] = temp;

    }

    public static User[] generate(int n, int perBucket) {
        AtomicInteger generator = new AtomicInteger();

        List<User> users = IntStream.range(0, n)
            .boxed()
            .map(
                v -> {
                    List<User> candidates = new ArrayList<>(perBucket);

                    for (int i = 0; i < perBucket; i++) {
                        User user = new User();
                        user.week = v;
                        user.id = generator.incrementAndGet();

                        candidates.add(user);
                    }

                    return candidates;
                }
            )
            .flatMap(List::stream)
            .collect(Collectors.toList());

        Collections.shuffle(users);

        return users
            .toArray(new User[users.size()]);
    }

    static class User {
        int week;
        int id;

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                .add("week", week)
                .add("id", id)
                .toString();
        }
    }
}
